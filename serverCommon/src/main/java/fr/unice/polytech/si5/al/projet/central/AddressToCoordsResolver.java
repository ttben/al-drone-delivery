package fr.unice.polytech.si5.al.projet.central;

import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.math.Vector2D;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class AddressToCoordsResolver {

	private final boolean useHardcode = true;
	private List<Vector2D> hardcodedPoints;
	private int currentHardcode;

	public AddressToCoordsResolver() {
		this.hardcodedPoints = new LinkedList<>();
		this.hardcodedPoints.add(new Vector2D(25, 25));
		this.hardcodedPoints.add(new Vector2D(175, 175));
		this.hardcodedPoints.add(new Vector2D(30, 45));
		this.hardcodedPoints.add(new Vector2D(200, 200));

		currentHardcode = 0;
	}

	public Vector2D resolveAddress(Address address) {
		if (useHardcode) {
			int index = this.currentHardcode++;
			this.currentHardcode %= this.hardcodedPoints.size();
			return this.hardcodedPoints.get(index);
		}
		double x = Math.random()*500;
		double y = Math.random()*500;
		return new Vector2D(x, y);
	}

}
