package millebornes.ai;

import millebornes.card.Card;
import millebornes.card.SafetyCard;
import millebornes.util.CardName;

public interface AI {
	/**
	 * @param hand The computer's hand
	 * @return {card's index, where to play (as defined in Constants)}
	 */
	public int[] getBestCard(Card[] hand,
			CardName compBattle, SafetyCard[] compSafeties, int compDistance, CardName compSpeed,
			CardName playerBattle, SafetyCard[] playerSafeties, int playerDistance, CardName playerSpeed);
}
