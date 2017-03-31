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
	public int[] getBestCard(Card[] hand, CardName compBattle, SafetyCard[] compSafeties, int compDistance,
			CardName compSpeed, CardName playerBattle, SafetyCard[] playerSafeties, int playerDistance,
			CardName playerSpeed) {
		int typeToLookFor = -1;
		CardName ttlfExtra = CardName.DEFAULT;
		int whereToGo=0;
		if (Screen1.getCardType(compBattle) == Screen1.HAZARD) {
			typeToLookFor = Screen1.REMEDY;
			ttlfExtra = new Countercard(new HazardCard(compBattle)).getRemedy().getName(); 
			whereToGo = Constants.OWNBATTLE;
		} else if (Screen1.getCardType(compBattle) == Screen1.REMEDY) {
			typeToLookFor = Screen1.ROLL;
			whereToGo = Constants.OWNBATTLE;
		} else if (Screen1.getCardType(compBattle) == Screen1.ROLL) {
			if (compSpeed == CardName.SPEED_LIMIT)
				ttlfExtra = CardName.MILE_50;
			typeToLookFor = Screen1.DISTANCE;
			whereToGo = Constants.OWNDIST;
		} else { //no cards on battlepile
			if (compSpeed == CardName.SPEED_LIMIT) {
				ttlfExtra = CardName.END_SPEED_LIMIT;
				whereToGo = Constants.OWNLIMIT;
			} else {
				typeToLookFor = Screen1.ROLL;
				whereToGo = Constants.OWNBATTLE;
			}
		}
		int bestIndex=-1;
		if (ttlfExtra == CardName.DEFAULT) {
			for (int i=0;i<hand.length;i++) {//find correct card based to typetolookfor
				if (Screen1.getCardType(hand[i].getName()) == typeToLookFor){//hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
					bestIndex=i;
					break;
				}
			}
		} else if (ttlfExtra == CardName.MILE_25){
			for (int i=0;i<hand.length;i++) {//find correct card based to typetolookfor
				if (hand[i].getName() == CardName.MILE_25 || hand[i].getName() == CardName.MILE_50){//hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
					bestIndex=i;
					break;
				}
			}
		} else if (ttlfExtra == CardName.END_SPEED_LIMIT) {
			for (int i=0;i<hand.length;i++) {
				if (hand[i].getName() == ttlfExtra) {
					bestIndex=i;
					break;
				}
			}
			if (bestIndex==-1) {
				for (int i=0;i<hand.length;i++) {
					if (hand[i].getName() == CardName.MILE_25 || hand[i].getName() == CardName.MILE_50){//hand[i].getClass().getSimpleName().equals(typeToLookFor.getClass().getSimpleName())) {
						bestIndex=i;
						break;
					}
				}
			}
		} else {
			for (int i=0;i<hand.length;i++) {
				if (hand[i].getName() == ttlfExtra) {
					bestIndex=i;
					break;
				}
			}
		}
		if (bestIndex == -1) {
			
			return new int[] {0,Constants.DISCARD};
		}
		return new int[] {bestIndex,whereToGo};
	}
	
}
