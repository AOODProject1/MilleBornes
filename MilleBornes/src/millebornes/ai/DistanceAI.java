package millebornes.ai;

import millebornes.MainScreen.Screen1;
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
		int typeToLookFor = 0;
		CardName ttlfExtra = CardName.DEFAULT;
		int whereToGo=0;
		if (compBattle instanceof HazardCard) {
			typeToLookFor = Screen1.REMEDY;
			ttlfExtra = new Countercard((HazardCard)compBattle).getRemedy().getName(); 
			whereToGo = Constants.OWNBATTLE;
		} else if (compBattle instanceof RemedyCard) {
			if (((RemedyCard)compBattle).equals(new RemedyCard(CardName.ROLL))) {
				typeToLookFor = Screen1.DISTANCE;
				whereToGo = Constants.OWNDIST;
			} else {
				typeToLookFor = Screen1.ROLL;
				whereToGo = Constants.OWNBATTLE;
			}
		} else { //no cards on battlepile
			typeToLookFor = Screen1.ROLL;
			whereToGo = Constants.OWNDIST;
		}
		int bestIndex=0;
		for (int i=0;i<hand.length;i++) {//find correct card based to typetolookfor
			if (Screen1.getCardType(hand[i].getName()) == typeToLookFor){//hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
					bestIndex=i;
					break;
			}
		}
		return new int[] {bestIndex,whereToGo};
	}
	
}
