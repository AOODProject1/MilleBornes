package millebornes.ai;

import millebornes.card.Card;
import millebornes.card.SafetyCard;
import millebornes.card.SpeedCard;

public interface AI {
	/**
	 * 
	 * @return {card's index, where to play (as defined in Constants)}
	 */
	public int[] getBestCard(Card[] hand,
			Card compBattle, SafetyCard[] compSafeties, int compDistance, SpeedCard compSpeed,
			Card playerBattle, SafetyCard[] playerSafeties, int playerDistance, SpeedCard playerSpeed);
}
