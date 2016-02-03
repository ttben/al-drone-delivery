package app.action;

import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;

public abstract class CompositeShipperAction extends Action {
	private CompositeShipper target;
	protected BasicShipper element;


	public CompositeShipperAction(CompositeShipper target, BasicShipper element, Object ...params) {
		super(params);
		this.target = target;
		this.element = element;
	}

	public BasicShipper getElement() {
		return element;
	}

	@Override
	public CompositeShipper getTarget() {
		return this.target;
	}
}