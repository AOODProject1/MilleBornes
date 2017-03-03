package millebornes.card;

import millebornes.util.CardName;
/**
 * Cards that increase the distance you've traveled
 *
 */
public class MovementCard extends Card implements Comparable<MovementCard> {
	private static final long serialVersionUID = -6526821481289137674L;
	private int distance;
	public MovementCard(CardName name) {
		super(name);
		switch (name) {
		case MILE_25:distance=25;break;
		case MILE_50:distance=50;break;
		case MILE_75:distance=75;break;
		case MILE_100:distance=100;break;
		case MILE_200:distance=200;break;
		default:distance=0;break;
		}
	}
	public int getDistance() {
		return distance;
	}
	public int compareTo(MovementCard c) {
		return distance - c.distance;
	}
}
