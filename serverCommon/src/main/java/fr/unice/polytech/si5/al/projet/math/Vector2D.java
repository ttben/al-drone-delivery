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

	public double distanceTo(Vector2D point) {
		return Math.sqrt(this.x * point.x + this.y * point.y);
	}

	@Override
	public String toString() {
		return "Vector2D{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
