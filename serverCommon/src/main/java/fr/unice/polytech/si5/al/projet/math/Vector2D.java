package fr.unice.polytech.si5.al.projet.math;

import org.json.JSONObject;

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
		return Math.sqrt(
				Math.pow(this.x - point.x, 2)
				+ Math.pow(this.y - point.y, 2)
		);
	}

	@Override
	public String toString() {
		return toJSON().toString();
		/*return "Vector2D{" +
				"x=" + x +
				", y=" + y +
				'}';*/
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("x", this.x);
		json.put("y", this.y);
		return json;
	}
}
