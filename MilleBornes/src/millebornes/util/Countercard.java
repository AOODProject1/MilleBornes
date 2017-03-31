package millebornes.util;

import millebornes.card.HazardCard;
import millebornes.card.RemedyCard;
import millebornes.card.SafetyCard;

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
	public SafetyCard getSafety() {
		switch(hazard.getName()) {
		case ACCIDENT:return new SafetyCard(CardName.DRIVING_ACE);
		case OUT_OF_GAS:return new SafetyCard(CardName.EXTRA_TANK);
		case FLAT_TIRE:return new SafetyCard(CardName.PUNCTURE_PROOF);
		case STOP:return new SafetyCard(CardName.RIGHT_OF_WAY);
		default:return null;
		}
	}
}
