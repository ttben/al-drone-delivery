package fr.unice.polytech.si5.al.projet.algorithm;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

/**
 *
 */
public class BasicWaypoint extends Vector2D implements WeightedWaypoint {

	public BasicWaypoint(Vector2D v) {
		super(v.getX(), v.getY());
	}

	public BasicWaypoint(double x, double y) {
		super(x, y);
	}

	public BasicWaypoint(double[] coords) {
		super(coords);
	}

	@Override
	public Vector2D computeAnchor() {
		return this;
	}

	@Override
	public int computeCost() {
		return 1;
	}
}
