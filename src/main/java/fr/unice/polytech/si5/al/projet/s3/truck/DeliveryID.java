package fr.unice.polytech.si5.al.projet.s3.truck;

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
}
