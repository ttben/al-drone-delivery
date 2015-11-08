package fr.unice.polytech.si5.al.projet.algorithm.clustering;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StubClustering extends ClusteringAlgorithm {

	@Override
	public List<List<Vector2D>> process(List<Vector2D> points, int clusterCount) {
		int clusterSize = (points.size() + 1) / clusterCount;
		List<List<Vector2D>> clusters = new ArrayList<>(clusterCount);

		// Initializes the result list
		for (int iCluster = 0; iCluster < clusterCount; iCluster++) {
			clusters.add(new LinkedList<>());
		}

		// Fill sequentially all the clusters
		for (int iPoint = 0; iPoint < points.size(); iPoint++) {
			clusters.get(iPoint / clusterSize).add(points.get(iPoint));
		}

		return clusters;
	}
}
