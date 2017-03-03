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
		Card typeToLookFor;
		if (compBattle == compBattle) { //change to compbattle isn't a roll card
			if (compBattle instanceof HazardCard) {
				typeToLookFor = new Countercard((HazardCard)compBattle).getRemedy();
			}
		} else if (compBattle instanceof RemedyCard) {
			if ((RemedyCard)compBattle.equals(new RemedyCard(CardName.ROLL))) {
				
			}
		} else {
			typeToLookFor = new MovementCard(CardName.MILE_200);
		}
		for (int i=0;i<hand.length;i++) {
			if (typeToLookFor.isInstance(hand[i])) {
				if (typeToLookFor.equals(RemedyCard.class)) {
					
				} else if (typeToLookFor.equals(MovementCard.class)) {
					
				}
			}
		}
		return 0;
	}

}
