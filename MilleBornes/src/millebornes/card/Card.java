package millebornes.card;

import java.io.Serializable;

public abstract class Card implements Serializable{
	private static final long serialVersionUID = -3158433674904661857L;
	protected String name;
	public Card(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
