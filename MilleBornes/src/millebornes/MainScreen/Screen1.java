package millebornes.MainScreen;

import javax.swing.JFrame;

public class Screen1 {
	static JFrame f;
	public static void main (String[] args){
		show("Default");
	}
	public static void show(String player){
		f = new JFrame("Mille Bornes");
		f.setSize(500, 500);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
