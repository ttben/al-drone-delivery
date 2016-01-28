package app;

import app.action.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class NodeTest {
	private Node aNode;

	@Mock
	private Action anInternalAction;

	@Before
	public void setUp() {
		this.aNode = new Node(anInternalAction);
	}

	@Test
	public void test() {
		assertTrue(true);
	}

}
