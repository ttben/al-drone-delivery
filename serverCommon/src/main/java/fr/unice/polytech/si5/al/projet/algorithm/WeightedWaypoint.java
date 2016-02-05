package fr.unice.polytech.si5.al.projet.algorithm;

import fr.unice.polytech.si5.al.projet.math.Vector2D;

/**
 * An element which position can be represented by a 2D Vector
 */
public interface WeightedWaypoint {

	Object getSourceObject();

	Vector2D computeAnchor();
	int computeCost();

}
