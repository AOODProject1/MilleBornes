package millebornes.card;

import millebornes.util.CardName;
/**
 * Cards that increase the distance you've traveled
 *
 */
public class MovementCard extends Card {
	private static final long serialVersionUID = -6526821481289137674L;
	private int distance;
	public MovementCard(CardName name, int distance) {
		super(name);
	}
	public int getDistance() {
		return distance;
	}
}
