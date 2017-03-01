package millebornes.card;

public class MovementCard extends Card {
	private static final long serialVersionUID = -6526821481289137674L;
	private int distance;
	public MovementCard(String name, int distance) {
		super(name);
	}
	public int getDistance() {
		return distance;
	}
}
