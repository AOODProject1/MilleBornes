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
		case EXTRA_TANK:
		case GAS:counter = new HazardCard(CardName.OUT_OF_GAS);break;
		case DRIVING_ACE:
		case REPAIRS:counter = new HazardCard(CardName.ACCIDENT);break;
		case PUNCTURE_PROOF:
		case SPARE_TIRE:counter = new HazardCard(CardName.FLAT_TIRE);break;
		case RIGHT_OF_WAY:
		case ROLL:counter = new HazardCard(CardName.STOP);break;
		case END_SPEED_LIMIT:counter = new HazardCard(CardName.SPEED_LIMIT);break;
		default:
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
