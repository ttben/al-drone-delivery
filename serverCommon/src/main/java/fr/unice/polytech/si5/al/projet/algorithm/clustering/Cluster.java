package fr.unice.polytech.si5.al.projet.algorithm.clustering;

import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import org.json.JSONArray;

import java.util.LinkedList;

/**
 *
 */
public class Cluster extends LinkedList<Vector2D> implements WeightedWaypoint {

	@Override
	public Object getSourceObject() {
		return new LinkedList<>(this);
	}

	@Override
	public Vector2D computeAnchor() {
		// Let's compute the center of the cluster
		double sumX = 0,
				sumY = 0;
		for (Vector2D v : this) {
			sumX += v.getX();
			sumY += v.getY();
		}

		return new Vector2D(sumX / this.size(), sumY / this.size());
	}

	@Override
	public int computeCost() {
		return this.size();
	}

	public JSONArray toJSON() {
		JSONArray json = new JSONArray();

		for (Vector2D wp: this) {
			json.put(wp.toJSON());
		}

		return json;
	}
}
