package fr.unice.polytech.si5.al.projet.s3.truck;

/**
 * Created by Benjamin on 05/11/2015.
 */
public class BoxID {

	private String ID;
	public BoxID(String boxID) {
		this.ID = boxID;
	}

	public boolean equals(Object o) {
		boolean result = false;

		if (o instanceof BoxID) {
			BoxID otherBoxID = (BoxID) o;
			result = ID.equals(otherBoxID.ID);
		}

		return result;
	}}
