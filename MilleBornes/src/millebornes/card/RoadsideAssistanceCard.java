package millebornes.card;
/**
 * Card which counters all hazards 
 * @author Morgan
 *
 */
public class RoadsideAssistanceCard extends SavingCard {
	private static final long serialVersionUID = -4737711052446490926L;

	public RoadsideAssistanceCard() {
		super("Roadside Assistance", new HazardCard("Any"));
	}
	@Override
	public boolean counters(HazardCard c) {
		return true;
	}
}
