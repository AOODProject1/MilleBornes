package millebornes.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import millebornes.card.Card;
import millebornes.card.HazardCard;

public class ImageGrab {
	public static void main(String[] args) {
		Image i = getCardBack();//new HazardCard(CardName.ACCIDENT));
	}
	public static Image getCardGraphic(Card c) {
		try {
			return ImageIO.read(new File("/mbcards/"+c.getName().toString().toLowerCase()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Image getCardBack() {
		try {
			return ImageIO.read(new File("/mbcards/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
