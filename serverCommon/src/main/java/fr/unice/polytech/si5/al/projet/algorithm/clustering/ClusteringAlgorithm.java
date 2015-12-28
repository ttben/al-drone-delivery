package fr.unice.polytech.si5.al.projet.algorithm.clustering;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.List;

public abstract class ClusteringAlgorithm {

	public abstract List<Cluster> process(List<Vector2D> points, double maxClusterRadius,
												 int maxPointPerCluster);

}
