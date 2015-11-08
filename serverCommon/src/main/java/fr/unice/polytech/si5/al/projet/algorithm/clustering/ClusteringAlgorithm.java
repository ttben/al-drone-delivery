package fr.unice.polytech.si5.al.projet.algorithm.clustering;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.List;

public abstract class ClusteringAlgorithm {

	public abstract List<List<Vector2D>> process(List<Vector2D> vectors, int clusters);

}
