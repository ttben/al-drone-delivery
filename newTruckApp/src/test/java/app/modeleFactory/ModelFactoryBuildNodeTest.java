package app.modeleFactory;

import app.Node;
import app.action.GoToDropPoint;
import app.shipper.CompositeShipper;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ModelFactoryBuildNodeTest {
	public static final String SHIPPER_FILE_LOCATION ="shippers.json";
	public static final String GRAPH_FILE_LOCAITON = "template_main.json";

	@Test
	public void aFactory_WhenBuildNodes_ShouldHaveProperRootNode() throws Exception {
		Node root = ModelFactory.buildAll(SHIPPER_FILE_LOCATION, GRAPH_FILE_LOCAITON);
		boolean rootEmbedAGotoAction = root.getAction() instanceof GoToDropPoint;
		assertTrue(rootEmbedAGotoAction);
		System.out.println(Arrays.asList(root.getAction().getParams()));
	}

	@Test
	public void aFactory_WhenBuildNodes_ShouldHaveProperRootNodeTarget() throws Exception {
		Node root = ModelFactory.buildAll(SHIPPER_FILE_LOCATION, GRAPH_FILE_LOCAITON);
		boolean rootTargetTruck = root.getAction().getTarget() instanceof CompositeShipper;
		assertTrue(rootTargetTruck);
	}


}
