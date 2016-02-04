package fr.unice.polytech.si5.al.projet.central;

import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.math.Vector2D;

/**
 *
 */
public class AddressToCoordsResolver {

	public Vector2D resolveAddress(Address address) {
		double x = Math.random()*500;
		double y = Math.random()*500;
		return new Vector2D(x, y);
	}

}
