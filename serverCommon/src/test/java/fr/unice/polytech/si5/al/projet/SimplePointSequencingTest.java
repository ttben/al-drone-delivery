package fr.unice.polytech.si5.al.projet;

import fr.unice.polytech.si5.al.projet.algorithm.BasicWaypoint;
import fr.unice.polytech.si5.al.projet.algorithm.WeightedWaypoint;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.PointSequencingAlgorithm;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.Sequence;
import fr.unice.polytech.si5.al.projet.algorithm.sequencing.SimplePointSequencingAlgorithm;
import fr.unice.polytech.si5.al.projet.demo.PointGenerator;
import fr.unice.polytech.si5.al.projet.math.Vector2D;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class SimplePointSequencingTest extends TestCase {

	public void testSimplePointSequencing() throws Exception {
		List<WeightedWaypoint> points = Arrays.asList(
				new BasicWaypoint(0, 0),
				new BasicWaypoint(1, 0),
				new BasicWaypoint(0, 1),
				new BasicWaypoint(1, 1),
				new BasicWaypoint(2, 0),
				new BasicWaypoint(0, 2),
				new BasicWaypoint(2, 1),
				new BasicWaypoint(3, 4),
				new BasicWaypoint(4, 5),
				new BasicWaypoint(0.5, 0.5)
		);

		// System.out.println("Processing " + points.size() + " elements.");
		PointSequencingAlgorithm algorithm = new SimplePointSequencingAlgorithm();
		List<Sequence> sequences = algorithm.process(points, new Vector2D(0,0), 3, 3);
		// System.out.println("Sequencing result: " + sequences);
	}

	public void testRandomPointSequencing() {
		int pointsToGenerate = 150;

		List<? extends WeightedWaypoint> points = PointGenerator.generateBasicWaypoints(pointsToGenerate);

		// System.out.println("Processing " + points.size() + " elements.");
		PointSequencingAlgorithm algorithm = new SimplePointSequencingAlgorithm();
		List<Sequence> sequences = algorithm.process(points, new Vector2D(0, 0), 3, 6);
		// System.out.println("Sequencing result: " + sequences);
	}
}
