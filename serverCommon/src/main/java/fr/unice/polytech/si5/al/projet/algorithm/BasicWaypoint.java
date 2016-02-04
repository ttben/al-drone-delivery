package fr.unice.polytech.si5.al.projet.algorithm;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

/**
 *
 */
public class BasicWaypoint extends Vector2D implements WeightedWaypoint {

	private Vector2D sourceVector2D;

	public BasicWaypoint(Vector2D v) {
		super(v.getX(), v.getY());
		this.sourceVector2D = v;
	}

	public BasicWaypoint(double x, double y) {
		super(x, y);
	}

	public BasicWaypoint(double[] coords) {
		super(coords);
	}

	@Override
	public Object getSourceObject() {
		return this.sourceVector2D;
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
