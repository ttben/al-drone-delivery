package fr.unice.polytech.si5.al.projet.demo;

import fr.unice.polytech.si5.al.projet.algorithm.BasicWaypoint;
import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PointGenerator {

	public static List<Vector2D> generateVector2D(int pointsToGenerate) {
		List<Vector2D> points = new ArrayList<>(pointsToGenerate);

		double minX = 0,
				maxX = 500,
				minY = 0,
				maxY = 500;

		for (int iPoint = 0; iPoint < pointsToGenerate; iPoint++) {
			points.add(new Vector2D(Math.random() * maxX, Math.random() * maxY));
		}

		return points;
	}

	public static List<BasicWaypoint> generateBasicWaypoints(int pointsToGenerate) {
		List<Vector2D> vector2Ds = PointGenerator.generateVector2D(pointsToGenerate);
		List<BasicWaypoint> result = new ArrayList<>(vector2Ds.size());
		// Convert the vectors to basic waypoints
		for (Vector2D v : vector2Ds) {
			result.add(new BasicWaypoint(v));
		}
		return result;
	}

}
