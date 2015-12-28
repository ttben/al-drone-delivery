package fr.unice.polytech.si5.al.projet.algorithm.sequencing;

import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Sequence extends ArrayList<WeightedWaypoint> {

	public Sequence(int initialCapacity) {
		super(initialCapacity);
	}

	public int computeCost() {
		int sum = 0;
		for (WeightedWaypoint wp : this) {
			sum += wp.computeCost();
		}
		return sum;
	}

	public String toString() {
		List<Vector2D> anchors = new ArrayList<>(this.size());
		for (WeightedWaypoint wp: this) {
			anchors.add(wp.computeAnchor());
		}
		return anchors.toString();
	}

}
