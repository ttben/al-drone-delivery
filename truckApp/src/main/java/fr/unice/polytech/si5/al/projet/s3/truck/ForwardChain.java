package fr.unice.polytech.si5.al.projet.s3.truck;

public abstract class ForwardChain<T> {
	private T next;
	protected State state;

	public T next() {
		return next;
	}

	public boolean hasNext() {
		return next != null;
	}

	public void setNext(T next) {
		this.next = next;
	}

	public boolean isValidated(){
		return this.state.isValidated();
	}

	public boolean isDone() {
		return this.state.isDone();
	}

	public State getState() {
		return this.state;
	}

}