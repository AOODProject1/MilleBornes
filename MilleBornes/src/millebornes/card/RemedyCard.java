package millebornes.card;
/**
 * Card which counters a HazardCard of type Counter
 *
 */
public class RemedyCard extends SavingCard {
	private static final long serialVersionUID = 2708224905239756537L;

	public RemedyCard(String name,Card counter) {
		super(name,counter);
	}

}
