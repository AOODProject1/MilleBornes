package millebornes.ai;

import millebornes.card.*;
import millebornes.util.CardName;
import millebornes.util.Countercard;

public class DefaultAI implements AI {

	@Override
	public int getBestCard(Card[] hand, Card compBattle, SafetyCard[] compSafeties, int compDistance,
			SpeedCard compSpeed, Card playerBattle, SafetyCard[] playerSafeties, int playerDistance,
			SpeedCard playerSpeed) {
		Card typeToLookFor = null;
		if (compBattle instanceof HazardCard) {
			typeToLookFor = new Countercard((HazardCard)compBattle).getRemedy();
		} else if (compBattle instanceof RemedyCard) {
			if (((RemedyCard)compBattle).equals(new RemedyCard(CardName.ROLL))) {
				typeToLookFor = new MovementCard(CardName.MILE_200);
			} else {
				typeToLookFor = new RemedyCard(CardName.ROLL);
			}
		} else {
			throw new RuntimeException("The card on top of the Battle Pile for the computer is neither a HazardCard nor a RemedyCard");
		}
		int bestIndex=0;
		for (int i=0;i<hand.length;i++) {//find correct card based to typetolookfor
			if (hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
					bestIndex=i;
					break;
			}
		}
		return bestIndex;
	}

}
