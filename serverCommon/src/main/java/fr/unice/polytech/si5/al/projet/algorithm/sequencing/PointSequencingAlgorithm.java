package fr.unice.polytech.si5.al.projet.algorithm.sequencing;

import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.List;

/**
 * This algorithm takes a set 2D points in input and returns multiples sequences of points that
 * compounds a cohesive path.
 */
public interface PointSequencingAlgorithm {

	/**
	 * @param points            The points that have to be sequenced.
	 * @param minSequencesCount The minimum number of path that will be generated in the output.
	 *                          (eg: for shippings, it would match the number of available trucks)
	 * @param maxWaypointsCost  The maximum number of waypoint a sequence may have, excluding start.
	 */
	List<Sequence> process(List<? extends WeightedWaypoint> points, Vector2D startingPoint,
						   int minSequencesCount, int maxWaypointsCost);

}
