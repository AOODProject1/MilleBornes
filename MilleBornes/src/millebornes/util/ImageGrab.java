package millebornes.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import millebornes.card.Card;

public class ImageGrab {
	public static Image getCardGraphic(Card c) {
		try {
			return ImageIO.read(new File("mbcards/"+c.getName().toString().toLowerCase()+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Image getCardBack() {
		try {
			return ImageIO.read(new File("mbcards/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
