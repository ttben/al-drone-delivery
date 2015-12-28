package fr.unice.polytech.si5.al.projet.algorithm.sequencing;

import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;

import java.util.ArrayList;

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

}
