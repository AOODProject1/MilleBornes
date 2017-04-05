package millebornes.MainScreen;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import millebornes.card.Card;
import millebornes.util.ImageGrab;

public class DeckLabel extends JLabel {
	private static final long serialVersionUID = -1859773449203982765L;
	ArrayList<Card> deck = new ArrayList<>();
	public DeckLabel() {
		super(new ImageIcon(ImageGrab.getCardBack()));
		deck=null;
	}
	public DeckLabel(ArrayList<Card> x){
		super(new ImageIcon(ImageGrab.getCardBack()));
		deck = x;
	}
	public Card getTopCard() {
		return deck.remove(0);
	}
	public void setDeck(ArrayList<Card> d) {
		deck=d;
	}
	public int getDeckSize() {
		return deck.size();
	}
}
