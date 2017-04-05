package millebornes.card;

import java.io.Serializable;

import millebornes.util.CardName;

public abstract class Card implements Serializable {
	private static final long serialVersionUID = -3158433674904661857L;
	protected CardName name;
	public Card(CardName name) {
		this.name = name;
	}
	public CardName getName() {
		return name;
	}
	public boolean equals(Card c) {
		return c.name==name;
	}
	public String toString() {
		return name.toString();
	}
	public static Card getCardFromName(CardName c) {
		switch (c) {
		case ACCIDENT:
		case FLAT_TIRE:
		case OUT_OF_GAS:
		case STOP:
			return new HazardCard(c);
			
		case DRIVING_ACE:
		case PUNCTURE_PROOF:
		case RIGHT_OF_WAY:
		case EXTRA_TANK:
		case ROLL:
			return new SafetyCard(c);
			
		case GAS:
		case REPAIRS:
		case SPARE_TIRE:
			return new RemedyCard(c);
		case ROADSIDE_ASSISTANCE:
			return new RoadsideAssistanceCard();
			
		case MILE_100:
		case MILE_200:
		case MILE_25:
		case MILE_50:
		case MILE_75:
			return new MovementCard(c);
			
		case DEFAULT:
		default:
			return new DefaultCard();
		}
	}
	public static SpeedCard getSpeedCardFromName(CardName c) {
		switch (c) {
		case END_SPEED_LIMIT:
			return new EndSpeedLimitCard();
		case SPEED_LIMIT:
			return new SpeedHazardCard();
		}
		return new DefaultSpeedCard();
	}
}
