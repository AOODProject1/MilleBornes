package millebornes.MainScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import millebornes.card.Card;

public class Screen1 {
	static JFrame f;
	private static  Card[]player  = new Card[6];
	private static  Card[]comp  = new Card[6];
	private static  Card[]playerSafeties  = new Card[4];
	private static  Card[]compSafeties  = new Card[4];
	private static Card hazardPlayer;
	private static Card limitPlayer;
	private static Card milagePlayer;
	private static Card hazardComp;
	private static Card limitComp;
	private static Card milageComp;
	private static  ArrayList<Card>deck;
	private static  ArrayList<Card>discard;
	public static void main (String[] args){
		show("Default");
	}
	public static void show(String player){
		f = new JFrame("Mille Bornes");
		f.setExtendedState(f.MAXIMIZED_BOTH);
		JMenuBar bar = new JMenuBar();
		JMenuItem help = new JMenuItem("Help");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save As");
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem newGame = new JMenuItem("New Game");
		bar.add(newGame);
		bar.add(save);
		bar.add(load);
		bar.add(help);
		bar.add(quit);
		quit.addActionListener(new QuitListener());
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser getSaveFile = new JFileChooser();
				if (getSaveFile.showSaveDialog(f) == JFileChooser.APPROVE_OPTION) {
					File saveLoc;
					if (getSaveFile.getSelectedFile().getAbsolutePath().endsWith(".mb")) {
						saveLoc = getSaveFile.getSelectedFile();
					} else {
						saveLoc = new File(getSaveFile.getSelectedFile().getAbsolutePath() +".mb");
					}
					saveLoc.getParentFile().mkdirs();
					try (ObjectOutputStream p = new ObjectOutputStream(new FileOutputStream(saveLoc))){
						saveLoc.createNewFile();
						p.writeObject(player);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				//bring up a menu to select file
				//save file at selected place
			}
		});
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser getLoadFile = new JFileChooser();
				if (getLoadFile.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
					File saveLoc = new File(getLoadFile.getSelectedFile().getAbsolutePath());
					saveLoc.getParentFile().mkdirs();
					try (ObjectInputStream p = new ObjectInputStream(new FileInputStream(saveLoc))){
						saveLoc.createNewFile();
						toDos = ((ArrayList<ActionItem>)(p.readObject()));
						updateList();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				//set to-dos as a file chosen by user
			}
		});
		f.setJMenuBar(bar);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private static class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			close();
			return;
		}
	}
	
	private static void close() {
		int confirm = JOptionPane.showConfirmDialog(f, "Are you sure you want to quit?", "Confirm Quit",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) // person doesn't want to leave
			System.exit(0);
	}
	
	
}
