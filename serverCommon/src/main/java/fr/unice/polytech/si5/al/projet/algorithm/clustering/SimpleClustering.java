package fr.unice.polytech.si5.al.projet.algorithm.clustering;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleClustering extends ClusteringAlgorithm {

	@Override
	public List<List<Vector2D>> process(List<Vector2D> points, double maxClusterRadius, int maxPointPerCluster) {
		List<List<Vector2D>> result = new LinkedList<>();
		List<Vector2D> localPoints = new LinkedList<>(points);
		List<Vector2D> currentCluster;

		// On each iteration, we will create a cluster until all the point are assigned to a cluster
		while (localPoints.size() > 0) {
			currentCluster = new LinkedList<>();
			Vector2D rootPoint = localPoints.remove(0);
			// The point can be added to the cluster
			currentCluster.add(rootPoint);

			for (int iPoint = 0; iPoint < localPoints.size(); iPoint++) {
				Vector2D currentPoint = localPoints.get(iPoint);

				// Skip points that are too far from the root
				if (currentPoint.distanceTo(rootPoint) > maxClusterRadius) {
					System.out.println("Skipping because of cluster radius");
					continue;
				}

				// The point can be added to the cluster
				currentCluster.add(currentPoint);
				localPoints.remove(iPoint);
				--iPoint;

				// If the cluster has reached its maximum size
				if (currentCluster.size() >= maxClusterRadius) {
					break;
				}
			}
			result.add(currentCluster);
			System.out.println("Remaining points: "+ localPoints.size());
		}

		return result;
	}
}
