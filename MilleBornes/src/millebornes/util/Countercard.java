package millebornes.util;

import millebornes.card.HazardCard;
import millebornes.card.RemedyCard;

/**
 * Class tells user/computer what the appropriate safety/remedycard is for their problem
 *
 */
public class Countercard {
	private HazardCard hazard;
	public Countercard(HazardCard hazard) {
		this.hazard=hazard;
	}
	public HazardCard getHazard() {
		return hazard;
	}
	public RemedyCard getRemedy() {
		switch(hazard.getName()) {
		case ACCIDENT:return new RemedyCard(CardName.REPAIRS);
		case OUT_OF_GAS:return new RemedyCard(CardName.GAS);
		case FLAT_TIRE:return new RemedyCard(CardName.SPARE_TIRE);
		case STOP:return new RemedyCard(CardName.ROLL);
		default:return null;
		}
	}
}
