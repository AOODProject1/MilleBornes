package millebornes.card;

import millebornes.util.CardName;
/**
 * The base card for safety and remedy cards
 *
 */
public abstract class SavingCard extends Card {
	private static final long serialVersionUID = 2248184231528568309L;
	protected HazardCard counter;
	public SavingCard(CardName name) {
		super(name);
		switch (name) {
		
		}
	}
	public Card getCounter() {
		return counter;
	}
	public boolean counters(HazardCard c) {
		if (c.name.equals(counter.name)) return true;
		return false;
	}
}
