package fr.unice.polytech.si5.al.projet.truck.domain.delivery;

public class DeliveryID {

	private String ID;

	public DeliveryID(String deliveryID) {
		this.ID = deliveryID;
	}

	public boolean equals(Object o) {
		boolean result = false;

		if (o instanceof DeliveryID) {
			DeliveryID otherDeliveryID = (DeliveryID) o;
			result = ID.equals(otherDeliveryID.ID);
		}

		return result;
	}

	public String getID() {
		return ID;
	}

	public String getValue() {
		return this.ID;
	}

	@Override
	public String toString() {
		return getValue();
	}
}
