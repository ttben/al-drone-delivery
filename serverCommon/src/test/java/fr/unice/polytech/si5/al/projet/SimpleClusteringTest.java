package fr.unice.polytech.si5.al.projet;

import fr.unice.polytech.si5.al.projet.algorithm.clustering.ClusteringAlgorithm;
import fr.unice.polytech.si5.al.projet.algorithm.clustering.SimpleClustering;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class SimpleClusteringTest extends TestCase {

	public void testClustering() {
		ClusteringAlgorithm stub = new SimpleClustering();

		List<Vector2D> points = Arrays.asList(
				new Vector2D(0, 0),
				new Vector2D(1, 0),
				new Vector2D(0, 1),
				new Vector2D(1, 1),
				new Vector2D(2, 0),
				new Vector2D(0, 2),
				new Vector2D(2, 1),
				new Vector2D(3, 4),
				new Vector2D(4, 5)
		);

		List<List<Vector2D>> clusters = stub.process(points, 2.5, 10);
		System.out.println("Clusters: " + clusters);

		/*Assert.assertEquals(2, clusters.size());
		Assert.assertEquals(5, clusters.get(0).size());
		Assert.assertEquals(4, clusters.get(1).size());*/
	}

}
