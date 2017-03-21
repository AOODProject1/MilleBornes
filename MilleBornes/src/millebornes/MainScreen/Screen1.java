package millebornes.MainScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import millebornes.card.Card;
import millebornes.card.HazardCard;
import millebornes.card.MovementCard;
import millebornes.card.RemedyCard;
import millebornes.card.RoadsideAssistanceCard;
import millebornes.card.SafetyCard;
import millebornes.card.SpeedHazardCard;
import millebornes.util.CardName;
import millebornes.util.ImageGrab;
//http://www.codex99.com/design/images/mille/cards_us_1960_lg.jpg
public class Screen1 {
	static JFrame f;
	static Random r = new Random();

	static JPanel playerCards; //Player's Hand
	static JPanel compCards; //Computer's Hand
	static JPanel deckCards; //Deck & Discard
	static JPanel playerRunCards; //Battle/Limit/Mileage
	static JPanel compRunCards; //"
	static JLabel playerCardGraphics[] = new JLabel[7];
	static JLabel compCardGraphics[] = new JLabel[7];
	private static JLabel playerBattle;
	private static JLabel playerSpeed;
	private static JLabel playerMileage;
	private static JLabel compBattle;
	private static JLabel compSpeed;
	private static JLabel compMileage;
	static JPanel paneNonSafeties;//large panel with all cards but safeties

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
		JPanel paneSafeties = new JPanel();//large panel with all safety cards
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		paneSafeties.setBounds((int)screenSize.getWidth() - 100, 0, 100, 1136);
		paneNonSafeties = new JPanel();
		playerCards = new JPanel();
		deckCards = new JPanel();
		compCards = new JPanel();
		playerRunCards = new JPanel();
		compRunCards = new JPanel();
		f.setLayout(new BoxLayout(f.getContentPane(),BoxLayout.LINE_AXIS));
		init();
		deckCards.add(new JLabel(new ImageIcon(ImageGrab.getCardBack())));
		paneNonSafeties.setLayout(new BoxLayout(paneNonSafeties, BoxLayout.Y_AXIS));
		paneNonSafeties.add(compCards);
		paneNonSafeties.add(compRunCards);
		paneNonSafeties.add(deckCards);
		paneNonSafeties.add(playerRunCards);
		paneNonSafeties.add(playerCards);
		bar.add(newGame);
		bar.add(save);
		bar.add(load);
		bar.add(help);
		bar.add(quit);
		quit.addActionListener(new QuitListener());
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				init();
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
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(f, "Important rules: A player can not move without having a 'Go' card on the top of their battle pile. \nFirst player to 1000 miles wins the game (the player must hit exactly 1000 miles to win, you cant go over).\nSafety cards protect a the player from the hazard that corresponds to that safety (Ex: Out Of Gas : Extra Tank).\nCoup Fourre: If an opponent plays a Hazard Card and you hold the corresponding Safety Card, immediately Call 'Coup Fourre' and play the Safety Card to your Safety Area crosswise (horizontally). If you call 'Coup Fourre', you must do so before you draw a card.");
			}
		});
		f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N)
					init();
			}
		});
		keyComponent component = new keyComponent();
		f.add(component);
		f.add(paneNonSafeties);
		f.add(paneSafeties);
		//paneNonSafeties.add(new JLabel (new ImageIcon(ImageGrab.getCardGraphic(CardName.DRIVING_ACE))));
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
	private static void init() {
		player = new Card[7];
		comp = new Card[7];
		playerSafeties = new Card[4];
		compSafeties = new Card[4];
		hazardPlayer = null;
		limitPlayer = null;
		milagePlayer = null;
		hazardComp = null;
		limitComp = null;
		milageComp = null;
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		for (int i = 0; i < 110; i++){
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
			} else if (i <= 109){
				deck.add(new RoadsideAssistanceCard());
			}
		}
		Collections.shuffle(deck);
		playerCards.removeAll();
		compCards.removeAll();
		for (int c = 0; c < 7; c++){
			comp[c] = deck.remove(0);
			player[c] = deck.remove(0);
			playerCardGraphics[c] = new JLabel(new ImageIcon(ImageGrab.getCardGraphic(player[c].getName())));
			playerCardGraphics[c].setTransferHandler(new ImageTransferer());
			playerCardGraphics[c].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					JLabel source = (JLabel)(e.getSource());
					source.getTransferHandler().exportAsDrag(source, e, TransferHandler.COPY);
				}
			});
			compCardGraphics[c] = new JLabel(new ImageIcon(ImageGrab.getCardBack()));
			playerCards.add(playerCardGraphics[c]);
			compCards.add(compCardGraphics[c]);
		}
		playerCards.revalidate();
		playerCards.repaint();
		compCards.revalidate();
		compCards.repaint();
	}
	/**
	 * This class allows the cards to be dragged and dropped on other cards. To implement, make an instance of this class
	 * the TransferHandler of a JLabel with an Icon, rather than text. Then, enable DnD through a MouseAdapter each JLabel
	 * whose TransferHandler is this by calling exportAsDrag
	 * @author Morgan
	 *
	 */
	private static class ImageTransferer extends TransferHandler {
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
			JLabel onto = ((JLabel)support.getComponent());
			CardName selectedCard = getNameFromIcon(source.getIcon());
			CardName underCard = getNameFromIcon(onto.getIcon());
			if (onto == playerBattle) { //Playing onto player's battle pile
				if (getCardType(selectedCard) == REMEDY && getCardType(underCard)==HAZARD) { //Countering Hazard
					return true;
				}
				if (getCardType(selectedCard) == ROLL && getCardType(underCard) == REMEDY) { //Playing Roll after a remedy
					return true;
				}
			} else if (onto == compBattle){ //Playing onto computer's Battle Pile
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == ROLL) {
					return true;
				}
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == REMEDY) {
					return true;
				}
			} else if (onto == playerSpeed) { //Playing on own Speed Limit Pile
				if (getCardType(selectedCard) == ENDSPEEDLIM && getCardType(underCard) == SPEEDLIM) { //Ending a speed limit
					return true;
				}
			} else if (onto == compSpeed) { //Playing on Computer's speed pile
				if (getCardType(selectedCard) == SPEEDLIM && getCardType(underCard) == ENDSPEEDLIM) {
					
				}
			} else if (onto == playerMileage) { //Playing on own distance
				if (getCardType(selectedCard) == DISTANCE && hazardPlayer.getName() == CardName.ROLL) {
					if (limitPlayer.getName() == CardName.SPEED_LIMIT) {
						if (selectedCard == CardName.MILE_25 || selectedCard == CardName.MILE_50) {
							return true;
						}
						return false;
					}
					return true;
				}
			} else if (onto == compMileage) { //Playing onto computer's distance
				return false;
			}
			//enter conditions based on getCardType and where source is
			//END CONDITION INSERTION ------------
			return false;
		}
	}
	private static CardName getNameFromIcon(Icon icon) {
		for (CardName c : CardName.values()) {
			if (new ImageIcon(ImageGrab.getCardGraphic(c)).equals(icon)) return c;
		}
		return null;
	}
	private static final int HAZARD=0,DISTANCE=1,REMEDY=2,SAFETY=3,SPEEDLIM=4,ROLL=5,STOP=6,ENDSPEEDLIM=7;
	private static int getCardType(CardName a) {
		switch (a) {
		case ACCIDENT:
		case FLAT_TIRE:
		case OUT_OF_GAS:
			return HAZARD;
			
		case DRIVING_ACE:
		case PUNCTURE_PROOF:
		case RIGHT_OF_WAY:
		case EXTRA_TANK:
			return SAFETY;
			
		case GAS:
		case REPAIRS:
		case ROADSIDE_ASSISTANCE:
		case SPARE_TIRE:
			return REMEDY;
			
		case MILE_100:
		case MILE_200:
		case MILE_25:
		case MILE_50:
		case MILE_75:
			return DISTANCE;
			
		case END_SPEED_LIMIT:
			return ENDSPEEDLIM;
		case SPEED_LIMIT:
			return SPEEDLIM;
			
		case ROLL:
			return ROLL;
			
		case STOP:
			return STOP;
			
		default:
			return -1;
		}
	}
}
