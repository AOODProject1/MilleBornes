package millebornes.MainScreen;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import millebornes.card.Card;
import millebornes.util.ImageGrab;

public class DeckLabel extends JLabel {
	ArrayList<Card> deck = new ArrayList<>();
	public DeckLabel(ArrayList<Card> x){
		super(new ImageIcon(ImageGrab.getCardBack()));
		deck = x;
	}
	
}
