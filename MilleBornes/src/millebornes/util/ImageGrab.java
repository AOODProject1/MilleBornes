package millebornes.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Loads the images of cards and the back of the card, then returns the card when requested
 * @author Morgan
 *
 */
public class ImageGrab {
	private static HashMap<CardName, Image> cards = new HashMap<>();
	private static Image cardBack;
	/**
	 * Loads the cards for each CardName into a HashMap for faster processing and less calls to the card files themselves.
	 * <br><b>THIS MUST BE CALLED BEFORE USING OTHER ImageGrab METHODS!!!!! (Preferably at startup)</b>
	 */
	public static void loadCards() {
		for (CardName c : CardName.values()){
			try {
				cards.put(c, ImageIO.read(new File("mbcards/"+c.toString()+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			cardBack = ImageIO.read(new File("mbcards/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gets the pre-loaded graphic for the card in question.<br>
	 * Pre: ImageGrab.loadCards() has been called to initialize the card-array
	 * @param c The CardName of the graphic
	 * @return the Image corresponding with <b>c</b>. (This still would need to be cast as an Icon to use with JLabel)
	 */
	public static Image getCardGraphic(CardName c) {
		if (cards==null) return null;
		return cards.get(c);
	}
	
	/**
	 * Returns the back of the card.<br>
	 * Pre: ImageGrab.loadCards() has been called to initialize the cardback-image
	 */
	public static Image getCardBack() {
		if (cardBack==null) return null;
		return cardBack;
	}
}
