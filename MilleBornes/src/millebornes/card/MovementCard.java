package millebornes.card;

public class MovementCard extends Card {
	private int distance;
	public MovementCard(String name, int distance) {
		super(name);
	}
	public int getDistance() {
		return distance;
	}
}
