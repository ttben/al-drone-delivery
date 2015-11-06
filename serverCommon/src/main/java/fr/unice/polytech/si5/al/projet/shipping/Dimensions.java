package fr.unice.polytech.si5.al.projet.shipping;

public class Dimensions {

	public double width;
	public double height;
	public double depth;

	public Dimensions(double width, double height, double depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getDepth() {
		return depth;
	}
}