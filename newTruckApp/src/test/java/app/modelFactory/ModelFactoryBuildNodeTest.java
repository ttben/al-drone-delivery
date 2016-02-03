package app.modelFactory;

import app.Node;
import app.action.Action;
import app.action.Goto;
import app.modelFactory.exceptions.NoActionDefinedException;
import app.modelFactory.exceptions.NoParamsDefinedException;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ModelFactoryBuildNodeTest {
	public static final String SHIPPER_FILE_LOCATION = "shippers.json";
	public static final String GRAPH_FILE_LOCATION = "template_main.json";
	private static final int NB_NODES_IN_SAMPLES = 10;
	private static final String NODE_KEY_IN_SAMPLES = "node";
	public static final String KEY_TARGET_IN_SAMPLES = "target";
	public static final String KEY_ACTION_IN_SAMPLES = "action";
	public static final String KEY_PARAMS_IN_SAMPLES = "params";
	public static final String AN_ACTION_NAME = "drop";
	public static final String A_SHIPPER_NAME = "DroneA";

	private ModelFactory modelFactory;

	@Mock
	private ActionFactory mockedActionFactory;

	@Mock
	private Action mockedAction;

	@Before
	public void setUp() {
		modelFactory = new ModelFactory(mockedActionFactory);
		willReturn(mockedAction).given(mockedActionFactory).buildAction(anyMap(), anyString(), any(JSONObject.class));
	}

	@Test
	public void aFactory_WhenBuildNodes_ShouldHaveProperRootNode() throws Exception {
		modelFactory = new ModelFactory();
		Node root = modelFactory.parseJson(SHIPPER_FILE_LOCATION, GRAPH_FILE_LOCATION);

		boolean rootEmbedAGotoAction = root.getAction() instanceof Goto;

		assertTrue(rootEmbedAGotoAction);
	}

	@Test
	public void aFactory_WhenBuildNodes_ShouldHaveProperRootNodeTarget() throws Exception {
		modelFactory = new ModelFactory();
		Node root = modelFactory.parseJson(SHIPPER_FILE_LOCATION, GRAPH_FILE_LOCATION);

		boolean rootTargetTruck = root.getAction().getTarget() instanceof CompositeShipper;

		assertTrue(rootTargetTruck);
	}

	@Test
	public void aFactory_WhenBuildNodesWithEmptyDescription_ShouldReturnEmptyResult() throws Exception {
		JSONObject nodes = new JSONObject();

		Map<String, Node> expectedResult = new HashMap<>();
		Map<String, Node> actualResult = modelFactory.buildNodes(null, nodes);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void aFactory_WhenBuildNodesWithIncorrectDescription_ShouldReturnEmptyResult() throws Exception {
		JSONObject nodes = new JSONObject();
		nodes.put("nodee1", 1);
		nodes.put("nodee2", 2);

		Map<String, Node> expectedResult = new HashMap<>();
		Map<String, Node> actualResult = modelFactory.buildNodes(null, nodes);

		assertEquals(expectedResult, actualResult);
	}

	@Test(expected = NoActionDefinedException.class)
	public void aFactory_WhenBuildNodesWithNoActionKeyDefined_ShouldThrowException() throws Exception {
		JSONObject nodes = buildNodesSamplesWithoutActionKey();
		modelFactory.buildNodes(null, nodes);
	}

	@Test(expected = NoParamsDefinedException.class)
	public void aFactory_WhenBuildNodesWithNoParamsKeyDefined_ShouldThrowException() throws Exception {
		JSONObject nodes = buildNodesSamplesWithoutParamsKey();
		modelFactory.buildNodes(null, nodes);
	}

	@Test
	public void aFactory_WhenBuildingNodes_ShouldCallActionFactory() throws Exception {
		Map<String, Shipper> shipperMap = new HashMap<>();

		modelFactory.buildNodes(shipperMap, buildNodesSamples());

		verify(mockedActionFactory, times(NB_NODES_IN_SAMPLES))
				.buildAction(Matchers.eq(shipperMap), Matchers.eq(AN_ACTION_NAME), any(JSONObject.class));
	}

	@Test
	public void aFactory_WhenBuildingNodes_ShouldBuildProperNumberOfNodes() throws Exception {
		Map<String, Node> result = modelFactory.buildNodes(null, buildNodesSamples());

		int expectedNumberOfNodeBuilt = NB_NODES_IN_SAMPLES;
		int actualNumberOfNodeBuilt = result.keySet().size();

		assertEquals(expectedNumberOfNodeBuilt, actualNumberOfNodeBuilt);
	}

	@Test
	public void aFactory_WhenBuildingNodes_ShouldReturnProperResult() throws Exception {
		Map<String, Node> result = modelFactory.buildNodes(null, buildNodesSamples());

		for(int i = 1 ; i <= NB_NODES_IN_SAMPLES ; i++) {
			String keyToCheck = NODE_KEY_IN_SAMPLES + i;
			boolean keyExists = result.containsKey(keyToCheck);
			assertTrue(keyExists);
		}
	}

	private JSONObject buildNodesSamplesWithoutActionKey() {
		return buildNodesWithoutSubParams(KEY_ACTION_IN_SAMPLES);
	}

	private JSONObject buildNodesSamplesWithoutParamsKey() {
		return buildNodesWithoutSubParams(KEY_PARAMS_IN_SAMPLES);
	}

	private JSONObject buildNodesWithoutSubParams(String keyToDelete) {
		JSONObject result = buildNodesSamples();

		Set keys = result.keySet();
		Iterator iterator = keys.iterator();

		while (iterator.hasNext()) {
			JSONObject currentNodeDescription = (JSONObject) result.get(iterator.next());
			currentNodeDescription.remove(keyToDelete);
		}
		return result;
	}

	private JSONObject buildNodesSamples() {
		JSONObject result = new JSONObject();

		for (int i = 1; i <= NB_NODES_IN_SAMPLES; i++) {
			JSONObject currentNode = new JSONObject();

			JSONObject params = new JSONObject();
			params.put(KEY_TARGET_IN_SAMPLES, A_SHIPPER_NAME);

			currentNode.put(KEY_ACTION_IN_SAMPLES, AN_ACTION_NAME);
			currentNode.put(KEY_PARAMS_IN_SAMPLES, params);

			String currentNodeKey = NODE_KEY_IN_SAMPLES + i;
			result.put(currentNodeKey, currentNode);
		}

		return result;
	}
}