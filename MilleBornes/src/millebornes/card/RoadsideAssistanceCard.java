package millebornes.card;

import millebornes.util.CardName;

/**
 * Card which counters all hazards once
 *
 */
public class RoadsideAssistanceCard extends SavingCard {
	private static final long serialVersionUID = -4737711052446490926L;

	public RoadsideAssistanceCard() {
		super(CardName.ROADSIDE_ASSISTANCE);
	}
	@Override
	public boolean counters(HazardCard c) {
		return true;
	}
}
