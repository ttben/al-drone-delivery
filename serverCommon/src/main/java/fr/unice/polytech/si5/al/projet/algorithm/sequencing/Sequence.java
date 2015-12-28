package fr.unice.polytech.si5.al.projet.algorithm.sequencing;

import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.algorithm.clustering.Cluster;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import org.json.JSONArray;
import org.json.JSONObject;

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

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		JSONArray seqPointsJSON = new JSONArray();
		json.put("points", seqPointsJSON);
		json.put("startingPoint", this.get(0).computeAnchor().toJSON());

		for (WeightedWaypoint wp : this) {
			// Skip first element that is the starting point
			if (wp == this.get(0)) {
				continue;
			}
			JSONObject clusterJSON = new JSONObject();
			Cluster cluster = (Cluster) wp;

			clusterJSON.put("points", cluster.toJSON());
			clusterJSON.put("anchor", cluster.computeAnchor().toJSON());
			seqPointsJSON.put(clusterJSON);
		}

		return json;
	}
}
