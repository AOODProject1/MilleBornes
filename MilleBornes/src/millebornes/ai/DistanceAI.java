package millebornes.ai;

import millebornes.card.*;
import millebornes.util.CardName;
import millebornes.util.Constants;
import millebornes.util.Countercard;
/**
 * This AI will only try to go the distance, rather than fight
 *
 */
public class DistanceAI implements AI {

	@Override
	public int[] getBestCard(Card[] hand, Card compBattle, SafetyCard[] compSafeties, int compDistance,
			SpeedCard compSpeed, Card playerBattle, SafetyCard[] playerSafeties, int playerDistance,
			SpeedCard playerSpeed) {
		Card typeToLookFor = null;
		int whereToGo=0;
		if (compBattle instanceof HazardCard) {
			typeToLookFor = new Countercard((HazardCard)compBattle).getRemedy();
			whereToGo = Constants.OWNBATTLE;
		} else if (compBattle instanceof RemedyCard) {
			if (((RemedyCard)compBattle).equals(new RemedyCard(CardName.ROLL))) {
				typeToLookFor = new MovementCard(CardName.MILE_200);
				whereToGo = Constants.OWNDIST;
			} else {
				typeToLookFor = new RemedyCard(CardName.ROLL);
				whereToGo = Constants.OWNBATTLE;
			}
		} else { //no cards on battlepile
			typeToLookFor = new RemedyCard(CardName.ROLL);
			whereToGo = Constants.OWNDIST;
		}
		int bestIndex=0;
		for (int i=0;i<hand.length;i++) {//find correct card based to typetolookfor
			if (hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
					bestIndex=i;
					break;
			}
		}
		return new int[] {bestIndex,whereToGo};
	}

}
