package millebornes.card;

public abstract class SavingCard extends Card {
	private static final long serialVersionUID = 2248184231528568309L;
	protected Card counter;
	public SavingCard(String name,Card counter) {
		super(name);
		this.counter=counter;
	}
	public Card getCounter() {
		return counter;
	}

}
