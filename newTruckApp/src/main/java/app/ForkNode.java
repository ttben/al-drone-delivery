package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 18/01/2016.
 */
public class ForkNode {
	private List<Node> nodes = new ArrayList<>();

	public ForkNode(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void execute() {
		for(Node n : nodes) {
			n.start();
		}
	}
}
