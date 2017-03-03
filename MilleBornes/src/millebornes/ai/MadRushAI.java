package millebornes.ai;

import millebornes.card.*;
import millebornes.util.CardName;
import millebornes.util.Countercard;

public class MadRushAI implements AI {

	@Override
	public int getBestCard(Card[] hand, Card compBattle, SafetyCard[] compSafeties, int compDistance,
			SpeedCard compSpeed, Card playerBattle, SafetyCard[] playerSafeties, int playerDistance,
			SpeedCard playerSpeed) {
		Card bestCard;
		Card typeToLookFor = null;
		if (!compBattle.equals(new RemedyCard(CardName.ROLL))) { //change to compbattle isn't a roll card
			if (compBattle instanceof HazardCard) {
				typeToLookFor = new Countercard((HazardCard)compBattle).getRemedy();
			}
		} else if (compBattle instanceof RemedyCard) {
			if (((RemedyCard)compBattle).equals(new RemedyCard(CardName.ROLL))) {
				typeToLookFor = new MovementCard(CardName.MILE_200);
			}
		} else {
			typeToLookFor = new RemedyCard(CardName.ROLL);
		}
		for (int i=0;i<hand.length;i++) {
			if (hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
					//find correct card based to typetolookfor
			}
		}
		return 0;
	}

}
