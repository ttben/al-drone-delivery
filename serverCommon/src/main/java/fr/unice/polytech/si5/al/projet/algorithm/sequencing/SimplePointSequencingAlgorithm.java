package fr.unice.polytech.si5.al.projet.algorithm.sequencing;

import fr.unice.polytech.si5.al.projet.algorithm.BasicWaypoint;
import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic implementation of the point sequencing algorithm.
 */
public class SimplePointSequencingAlgorithm implements PointSequencingAlgorithm {

	@Override
	public List<Sequence> process(List<? extends WeightedWaypoint> waypoints, Vector2D startingPoint,
								  int minSequencesCount, int maxWaypointsCost) {
		// First, sort the points from the start point.
		//
		List<WeightedWaypoint> localPoints = new LinkedList<>(waypoints);
		//WeightedWaypoint startWP = localPoints.remove(0);
		//Vector2D startPoint = startWP.computeAnchor();

		// Sort the points by distance from the first one.
		localPoints.sort((o1, o2) -> (int) Math.round(
				o1.computeAnchor().distanceTo(startingPoint) - o2.computeAnchor().distanceTo(startingPoint)
		));

		//System.out.println("Sorted points: " + localPoints);

		// This is the list of the sequences that are currently being explored.
		List<Sequence> activeSequences = new ArrayList<>(minSequencesCount);
		List<Sequence> allSequences = new ArrayList<>();

		// Setup the initial active points
		//
		// Initialize all the active sequences to null
		for (int iSequence = 0; iSequence < minSequencesCount; iSequence++) {
			activeSequences.add(iSequence, null);
		}

		while (localPoints.size() > 0) {
			// Iterate over all the active sequences to add more waypoints to them
			for (int iActiveSequence = 0; iActiveSequence < activeSequences.size(); iActiveSequence++) {
				Sequence activeSequence = activeSequences.get(iActiveSequence);

				// If the sequence is empty, set it up and take the next point as the start point of the sequence
				if (activeSequence == null) {
					// Initialize and store
					activeSequence = new Sequence(maxWaypointsCost);
					activeSequence.add(new BasicWaypoint(startingPoint));
					activeSequences.set(iActiveSequence, activeSequence);
					// Store the initial point
					activeSequence.add(localPoints.remove(0));

					// TODO: handle if localPoints is empty
				}

				if (localPoints.isEmpty()) {
					System.out.println("Empty after getting first point of sequence!");
					break;
				}

				WeightedWaypoint activeSequenceLastWaypoint = activeSequence.get(activeSequence.size() - 1);
				Vector2D activeSequenceLastPoint = activeSequenceLastWaypoint.computeAnchor();

				// Extract candidates for closest points
				/*List<Vector2D> candidates = localPoints.subList(
						0,
						Math.min(localPoints.size(), 2 * activeSequences.size())
				);*/

				List<WeightedWaypoint> candidates = new LinkedList<>(localPoints);

				// TODO: this is not optimized as we only need the minimum element, not the whole list sorted
				candidates.sort((o1, o2) -> (int) Math.round(
								o1.computeAnchor().distanceTo(activeSequenceLastPoint)
										- o2.computeAnchor().distanceTo(activeSequenceLastPoint)
						)
				);
				// Store the closest point as the active one of the current sequence
				WeightedWaypoint closestPoint = candidates.get(0);
				localPoints.remove(closestPoint);    // remove from the list of points that have to be processed
				activeSequence.add(closestPoint);    // store into the active sequence

				// Flush the sequence to the result buffer if it has reached its maximum length
				// Note: as we exclude the start point, we decrement the value by 1
				if (activeSequence.computeCost() >= maxWaypointsCost) {
					System.out.println("Flushing sequence " + iActiveSequence);
					// Store to result buffer
					allSequences.add(activeSequence);
					// Flag the list as uninitialized
					activeSequences.set(iActiveSequence, null);
				}

				System.out.println("Local points remaining: " + localPoints.size());

				// Prevent further iteration over sequences if there are no more points to process.
				if (localPoints.isEmpty()) {
					break;
				}
			}
		}

		return allSequences;
	}

}
