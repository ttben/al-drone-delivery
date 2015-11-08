package fr.unice.polytech.si5.al.projet.math;

public class Vector2D {

	private double x;
	private double y;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(double[] coords) {
		this.x = coords[0];
		this.y = coords[1];
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
