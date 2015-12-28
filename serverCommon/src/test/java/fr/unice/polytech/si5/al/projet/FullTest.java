package fr.unice.polytech.si5.al.projet;

import fr.unice.polytech.si5.al.projet.algorithm.clustering.Cluster;
import fr.unice.polytech.si5.al.projet.algorithm.clustering.SimpleClustering;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.Sequence;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.SimplePointSequencingAlgorithm;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import junit.framework.TestCase;
import org.json.JSONArray;

import java.util.List;

/**
 *
 */
public class FullTest extends TestCase {

	public void testFullProcess() throws Exception {
		Vector2D warehousePosition = new Vector2D(250, 250);

		List<Vector2D> points = PointGenerator.generateVector2D(200);

		// First cluster the points
		List<Cluster> clusters = new SimpleClustering().process(points, 50, 50);

		// Then sequence the clusters
		List<Sequence> clustersSequences = new SimplePointSequencingAlgorithm().process(
				clusters, warehousePosition,
				3, 15
		);

		JSONArray json = new JSONArray();
		for (Sequence clusterSeq : clustersSequences) {
			json.put(clusterSeq.toJSON());
		}

		System.out.println(json.toString());

	}
}
