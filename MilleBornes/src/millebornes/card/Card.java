package millebornes.card;

import java.io.Serializable;

import millebornes.util.CardName;

public abstract class Card implements Serializable{
	private static final long serialVersionUID = -3158433674904661857L;
	protected CardName name;
	public Card(CardName name) {
		this.name = name;
	}
	public CardName getName() {
		return name;
	}
}
