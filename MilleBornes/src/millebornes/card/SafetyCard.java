package millebornes.card;

import millebornes.util.CardName;

/**
 * Card which stops all new HazardCards of type counter
 *
 */
public class SafetyCard extends SavingCard {
	private static final long serialVersionUID = 2390207801162042934L;

	public SafetyCard(CardName name,HazardCard counter) {
		super(name,counter);
		// TODO Auto-generated constructor stub
	}

}
