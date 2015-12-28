package fr.unice.polytech.si5.al.projet;

import fr.unice.polytech.si5.al.projet.algorithm.clustering.Cluster;
import fr.unice.polytech.si5.al.projet.algorithm.clustering.SimpleClustering;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.SimplePointSequencingAlgorithm;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import junit.framework.TestCase;

import java.util.List;

/**
 *
 */
public class FullTest extends TestCase {

	public void testFullProcess() throws Exception {
		List<Vector2D> points = PointGenerator.generateVector2D(200);

		// First clusterize the points
		List<Cluster> clusters = new SimpleClustering().process(points, 10000, 15);
		new SimplePointSequencingAlgorithm().process(clusters, 3, 15);

	}
}
