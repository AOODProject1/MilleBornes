package millebornes.MainScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class keyComponent extends JComponent{
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		Rectangle r = new Rectangle(0, 0, 100, 142);
		g2.fill(r);
		g2.draw(r);
		g2.setColor(Color.WHITE);
		g2.drawString("Key", 0, 28);
		g2.setColor(Color.RED);
		g2.drawString("Hazard", 0, 56);
		g2.setColor(Color.YELLOW);
		g2.drawString("Remedy", 0, 84);
		g2.setColor(Color.GREEN);
		g2.drawString("Safety", 0, 112);
		g2.setColor(Color.CYAN);
		g2.drawString("Distance", 0, 130);
	}
}
