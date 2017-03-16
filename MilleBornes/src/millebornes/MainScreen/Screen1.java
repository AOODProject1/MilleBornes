package millebornes.MainScreen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

import millebornes.card.Card;
import millebornes.card.HazardCard;
import millebornes.card.MovementCard;
import millebornes.card.RemedyCard;
import millebornes.card.SafetyCard;
import millebornes.util.CardName;
import millebornes.util.ImageGrab;
//http://www.codex99.com/design/images/mille/cards_us_1960_lg.jpg
public class Screen1 {
	static JFrame f;
	static Random r = new Random();
	static JPanel playerCards;
	static JPanel compCards;
	static JPanel deckCards;
	static JPanel playerRunCards;
	static JPanel compRunCards;
	static JLabel playerCardGraphics[] = new JLabel[7];
	static JLabel compCardGraphics[] = new JLabel[7];
	static JLabel deckCardGraphic;
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
	public static void show(String p){
		f = new JFrame("Mille Bornes");
		ImageGrab.loadCards();
		f.setExtendedState(f.MAXIMIZED_BOTH);
		JMenuBar bar = new JMenuBar();
		JMenuItem help = new JMenuItem("Help");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save As");
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem newGame = new JMenuItem("New Game");
		JPanel paneNonSafeties = new JPanel();
		JPanel paneSafeties = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		paneSafeties.setBounds((int)screenSize.getWidth() - 100, 0, 100, 1136);
		paneNonSafeties.setLayout(new BoxLayout(paneNonSafeties, BoxLayout.Y_AXIS));
		//paneNonSafeties.add(playerRunCards);
		//paneNonSafeties.add(playerCards);
		bar.add(newGame);
		bar.add(save);
		bar.add(load);
		bar.add(help);
		bar.add(quit);
		quit.addActionListener(new QuitListener());
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				player = null;
				comp = null;
				playerSafeties = null;
				compSafeties = null;
				hazardPlayer = null;
				limitPlayer = null;
				milagePlayer = null;
				hazardComp = null;
				limitComp = null;
				milageComp = null;
				deck.clear();
				discard.clear();
				for (int i = 0; i < 105; i++){
					if (i <= 10){
						deck.add(new MovementCard(CardName.MILE_25));
					} else if(i <= 20){
						deck.add(new MovementCard(CardName.MILE_50));
					} else if(i <= 30){
						deck.add(new MovementCard(CardName.MILE_75));
					} else if(i <= 42){
						deck.add(new MovementCard(CardName.MILE_100));
					} else if(i <= 46){
						deck.add(new MovementCard(CardName.MILE_200));
					} else if(i <= 49){
						deck.add(new HazardCard(CardName.OUT_OF_GAS));
					} else if(i <= 52){
						deck.add(new HazardCard(CardName.FLAT_TIRE));
					} else if(i <= 55){
						deck.add(new HazardCard(CardName.ACCIDENT));
					} else if(i <= 59){
						deck.add(new HazardCard(CardName.SPEED_LIMIT));
					} else if(i <= 64){
						deck.add(new HazardCard(CardName.STOP));
					} else if(i <= 70){
						deck.add(new RemedyCard(CardName.GAS));
					} else if(i <= 76){
						deck.add(new RemedyCard(CardName.SPARE_TIRE));
					} else if(i <= 82){
						deck.add(new RemedyCard(CardName.REPAIRS));
					} else if(i <= 88){
						deck.add(new RemedyCard(CardName.END_SPEED_LIMIT));
					} else if(i <= 102){
						deck.add(new RemedyCard(CardName.ROLL));
					} else if(i == 103){
						deck.add(new SafetyCard(CardName.EXTRA_TANK));
					} else if(i == 104){
						deck.add(new SafetyCard(CardName.PUNCTURE_PROOF));
					} else if(i == 105){
						deck.add(new SafetyCard(CardName.DRIVING_ACE));
					} else if(i == 106){
						deck.add(new SafetyCard(CardName.RIGHT_OF_WAY));
					}
					deckCardGraphic = new JLabel(new ImageIcon(ImageGrab.getCardBack()));
					deckCards.add(deckCardGraphic);
				}
				Collections.shuffle(deck);
				for (int c = 0; c < 7; c++){
					comp[c] = deck.remove(0);
					player[c] = deck.remove(0);
					playerCardGraphics[c] = new JLabel(new ImageIcon(ImageGrab.getCardGraphic(player[c].getName())));
					compCardGraphics[c] = new JLabel(new ImageIcon(ImageGrab.getCardBack()));
					playerCards.add(playerCardGraphics[c]);
					compCards.add(compCardGraphics[c]);
				}
				paneNonSafeties.add(compCards);
				paneNonSafeties.add(compRunCards);
				paneNonSafeties.add(deckCards);
			}
		});
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
						p.writeObject(deck);
						p.writeObject(discard);
						p.writeObject(player);
						p.writeObject(comp);
						p.writeObject(playerSafeties);
						p.writeObject(compSafeties);
						p.writeObject(hazardPlayer);
						p.writeObject(limitPlayer);
						p.writeObject(milagePlayer);
						p.writeObject(hazardComp);
						p.writeObject(limitComp);
						p.writeObject(milageComp);
						
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
						deck = ((ArrayList<Card>)(p.readObject()));
						discard = ((ArrayList<Card>)(p.readObject()));
						player = ((Card[])(p.readObject()));
						comp = ((Card[])(p.readObject()));
						playerSafeties = ((Card[])(p.readObject()));
						compSafeties = ((Card[])(p.readObject()));
						hazardPlayer = ((Card)(p.readObject()));
						limitPlayer = ((Card)(p.readObject()));
						milagePlayer = ((Card)(p.readObject()));
						hazardComp = ((Card)(p.readObject()));
						limitComp = ((Card)(p.readObject()));
						milageComp = ((Card)(p.readObject()));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				//set to-dos as a file chosen by user
			}
		});
		keyComponent component = new keyComponent();
		f.add(paneSafeties);
		f.add(paneNonSafeties);
		f.add(component);
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
	
	/**
	 * This class allows the cards to be dragged and dropped on other cards. To implement, make an instance of this class
	 * the TransferHandler of a JLabel with an Icon, rather than text. Then, enable DnD through a MouseAdapter each JLabel
	 * whose TransferHandler is this
	 * @author Morgan
	 *
	 */
	private class ImageTransferer extends TransferHandler {
		private static final long serialVersionUID = -19201102892920628L;
		private JLabel source;
		public ImageTransferer(){
			super("icon");
		}
		@Override
		public void exportAsDrag(JComponent source, InputEvent e, int action) {
			super.exportAsDrag(source,e,action);
			this.source = (JLabel)source;
		}
		@Override
		public boolean canImport(TransferSupport support) {
			if (!super.canImport(support)) return false;
			if (!support.isDrop()) return false;
			//INSERT CONDITIONS HERE ------------
			
			//if (source.getIcon().equals(i3) && ((JLabel)support.getComponent()).getIcon().equals(i2)) //Example of condition
					//return false;																      //Replace with easier-to read type
			
			//END CONDITION INSERTION ------------
			return true;
		}
	}
}
