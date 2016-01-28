package app;

import app.action.Action;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NodeTest {
	private Node aNode;

	@Mock
	private Action anInternalAction;

	@Before
	public void setUp() {
		this.aNode = new Node(anInternalAction);
	}


}
