package millebornes.MainScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import millebornes.ai.AI;
import millebornes.ai.DistanceAI;
import millebornes.card.Card;
import millebornes.card.DefaultCard;
import millebornes.card.DefaultSpeedCard;
import millebornes.card.HazardCard;
import millebornes.card.MovementCard;
import millebornes.card.RemedyCard;
import millebornes.card.RoadsideAssistanceCard;
import millebornes.card.SafetyCard;
import millebornes.card.SpeedCard;
import millebornes.util.CardName;
import millebornes.util.Constants;
import millebornes.util.ImageGrab;

public class Screen1 {
	static JFrame f;
	static Random r = new Random();

	private static AI compPlayer;
	static JPanel playerCards; //Player's Hand
	static JPanel compCards; //Computer's Hand
	static JPanel deckCards; //Deck & Discard
	static JPanel playerRunCards; //Battle/Limit/Mileage
	static JPanel compRunCards; //"
	static CardLabel playerCardGraphics[] = new CardLabel[7];
	static CardLabel compCardGraphics[] = new CardLabel[7];
	private static JLabel systemText;
	private static String sT;
	private static JLabel winText;
	private static String wT;
	private static JLabel playerTotalDistance;
	private static String pTD;
	private static JLabel compTotalDistance;
	private static String cTD;
	private static CardLabel playerBattle;
	private static CardLabel playerSpeed;
	private static CardLabel playerMileage;
	private static CardLabel compBattle;
	private static CardLabel compSpeed;
	private static CardLabel compMileage;
	private static CardLabel playerSafety1;
	private static CardLabel playerSafety2;
	private static CardLabel playerSafety3;
	private static CardLabel playerSafety4;
	private static CardLabel compSafety1;
	private static CardLabel compSafety2;
	private static CardLabel compSafety3;
	private static CardLabel compSafety4;
	static JPanel paneNonSafeties;//large panel with all cards but safeties

	private static Card[]player  = new Card[6];
	private static Card[]comp  = new Card[6];
	private static SafetyCard[]playerSafeties  = new SafetyCard[4];
	private static SafetyCard[]compSafeties  = new SafetyCard[4];
	private static Card hazardPlayer;
	private static SpeedCard limitPlayer;
	private static Card mileagePlayer;
	private static Card hazardComp;
	private static SpeedCard limitComp;
	private static Card mileageComp;
	private static Integer playerDistance=0;
	private static Integer compDistance=0;
	private static  ArrayList<Card>deck;
	private static  ArrayList<Card>discard;
	private static DeckLabel deckLabel;
	public static void main (String[] args){
		compPlayer = new DistanceAI();
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
		JPanel playerPaneSafeties = new JPanel();//large panel with all the player safety cards
		JPanel compPaneSafeties = new JPanel();//large panel with all the comp safety cards
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		playerPaneSafeties.setBounds((int)screenSize.getWidth() - 100, 0, 100, 1136);
		compPaneSafeties.setBounds(0, 0, 100, 1136);
		systemText = new JLabel();
		winText = new JLabel();
		playerTotalDistance = new JLabel();
		compTotalDistance = new JLabel();
		paneNonSafeties = new JPanel();
		playerCards = new JPanel();
		deckCards = new JPanel();
		compCards = new JPanel();
		playerRunCards = new JPanel();
		compRunCards = new JPanel();
		playerBattle = new CardLabel(CardName.DEFAULT);
		playerSpeed  = new CardLabel(CardName.DEFAULT);
		playerMileage = new CardLabel(CardName.DEFAULT);
		compBattle = new CardLabel(CardName.DEFAULT);
		compSpeed = new CardLabel(CardName.DEFAULT);
		compMileage = new CardLabel(CardName.DEFAULT);
		playerSafety1 = new CardLabel(CardName.DEFAULT);
		playerSafety2 = new CardLabel(CardName.DEFAULT);
		playerSafety3 = new CardLabel(CardName.DEFAULT);
		playerSafety4 = new CardLabel(CardName.DEFAULT);
		compSafety1 = new CardLabel(CardName.DEFAULT);
		compSafety2 = new CardLabel(CardName.DEFAULT);
		compSafety3 = new CardLabel(CardName.DEFAULT);
		compSafety4 = new CardLabel(CardName.DEFAULT);
		CardLabel key = new CardLabel(CardName.KEY_CARD);
		deckLabel = new DeckLabel();
		JLabel compSaftiesName = new JLabel("Computer Safeties");
		JLabel playerSaftiesName = new JLabel("Player Safeties");
		playerSaftiesName.setHorizontalAlignment(SwingConstants.CENTER);
		compSaftiesName.setHorizontalAlignment(SwingConstants.CENTER);
		f.setLayout(new BoxLayout(f.getContentPane(),BoxLayout.LINE_AXIS));
		init();
		playerBattle.setTransferHandler(new ImageTransferer());
		playerSpeed.setTransferHandler(new ImageTransferer());
		playerMileage.setTransferHandler(new ImageTransferer());
		compBattle.setTransferHandler(new ImageTransferer());
		compSpeed.setTransferHandler(new ImageTransferer());
		compMileage.setTransferHandler(new ImageTransferer());
		playerSafety1.setTransferHandler(new ImageTransferer());
		playerSafety2.setTransferHandler(new ImageTransferer());
		playerSafety3.setTransferHandler(new ImageTransferer());
		playerSafety4.setTransferHandler(new ImageTransferer());
		compSafety1.setTransferHandler(new ImageTransferer());
		compSafety2.setTransferHandler(new ImageTransferer());
		compSafety3.setTransferHandler(new ImageTransferer());
		compSafety4.setTransferHandler(new ImageTransferer());
		playerRunCards.add(playerBattle);
		playerRunCards.add(playerSpeed);
		playerRunCards.add(playerMileage);
		playerRunCards.add(playerTotalDistance);
		compRunCards.add(compBattle);
		compRunCards.add(compSpeed);
		compRunCards.add(compMileage);
		compRunCards.add(compTotalDistance);
		playerPaneSafeties.add(playerSaftiesName);
		playerPaneSafeties.add(playerSafety1);
		playerPaneSafeties.add(playerSafety2);
		playerPaneSafeties.add(playerSafety3);
		playerPaneSafeties.add(playerSafety4);
		compPaneSafeties.add(compSaftiesName);
		compPaneSafeties.add(compSafety1);
		compPaneSafeties.add(compSafety2);
		compPaneSafeties.add(compSafety3);
		compPaneSafeties.add(compSafety4);
		deckCards.add(winText);
		deckCards.add(key);
		deckCards.add(deckLabel);
		deckCards.add(new CardLabel(CardName.DEFAULT));
		deckCards.add(systemText);
		playerPaneSafeties.setLayout(new GridLayout(5, 1));
		compPaneSafeties.setLayout(new GridLayout(5, 1));
		paneNonSafeties.setLayout(new BoxLayout(paneNonSafeties, BoxLayout.Y_AXIS));
		paneNonSafeties.add(compCards);
		paneNonSafeties.add(compRunCards);
		paneNonSafeties.add(deckCards);
		paneNonSafeties.add(playerRunCards);
		paneNonSafeties.add(playerCards);
		playerRunCards.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		compRunCards.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		playerPaneSafeties.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		compPaneSafeties.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		deckCards.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		f.pack();
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
						p.writeObject(mileagePlayer);
						p.writeObject(hazardComp);
						p.writeObject(limitComp);
						p.writeObject(mileageComp);
						
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
						playerSafeties = ((SafetyCard[])(p.readObject()));
						compSafeties = ((SafetyCard[])(p.readObject()));
						hazardPlayer = ((Card)(p.readObject()));
						limitPlayer = ((SpeedCard)(p.readObject()));
						mileagePlayer = ((Card)(p.readObject()));
						hazardComp = ((Card)(p.readObject()));
						limitComp = ((SpeedCard)(p.readObject()));
						mileageComp = ((Card)(p.readObject()));
						for (int i = 0; i < playerCardGraphics.length; i++){
							playerCardGraphics[i].setCardName(player[i].getName());
							playerCardGraphics[i].revalidate();
							playerCardGraphics[i].repaint();
						}
						playerBattle.setCardName(hazardPlayer.getName());
						playerSpeed.setCardName(limitPlayer.getName());
						playerMileage.setCardName(mileagePlayer.getName());
						compBattle.setCardName(hazardComp.getName());
						
						//playerCards.revalidate();
						//playerCards.repaint();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
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
		f.add(compPaneSafeties);
		f.add(paneNonSafeties);
		f.add(playerPaneSafeties);
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
		if (confirm == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	private static void init() {
		playerBattle.setCardName(CardName.DEFAULT);
		playerSpeed.setCardName(CardName.DEFAULT);
		playerMileage.setCardName(CardName.DEFAULT);
		compBattle.setCardName(CardName.DEFAULT);
		compSpeed.setCardName(CardName.DEFAULT);
		compMileage.setCardName(CardName.DEFAULT);
		playerSafety1.setCardName(CardName.DEFAULT);
		playerSafety2.setCardName(CardName.DEFAULT);
		playerSafety3.setCardName(CardName.DEFAULT);
		playerSafety4.setCardName(CardName.DEFAULT);
		compSafety1.setCardName(CardName.DEFAULT);
		compSafety2.setCardName(CardName.DEFAULT);
		compSafety3.setCardName(CardName.DEFAULT);
		compSafety4.setCardName(CardName.DEFAULT);
		player = new Card[7];
		comp = new Card[7];
		playerSafeties = new SafetyCard[4];
		compSafeties = new SafetyCard[4];
		hazardPlayer = null;
		limitPlayer = null;
		mileagePlayer = null;
		hazardComp = null;
		limitComp = null;
		mileageComp = null;
		hazardPlayer = new DefaultCard();
		limitPlayer = new DefaultSpeedCard();
		mileagePlayer = new DefaultCard();
		playerDistance = 0;
		hazardComp = new DefaultCard();
		limitComp = new DefaultSpeedCard();
		mileageComp = new DefaultCard();
		compDistance = 0;
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
			if (playerCardGraphics[c]==null) {
				playerCardGraphics[c] = new CardLabel((player[c].getName()));
				playerCardGraphics[c].setTransferHandler(new ImageTransferer());
				playerCardGraphics[c].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						CardLabel source = (CardLabel)(e.getSource());
						source.getTransferHandler().exportAsDrag(source, e, TransferHandler.COPY);
					}
				});
			} else {
				playerCardGraphics[c].setCardName(player[c].getName());
			}
			if (compCardGraphics[c]==null)
				compCardGraphics[c] = new CardLabel();
			compCardGraphics[c].setCardToBack();
			playerCards.add(playerCardGraphics[c]);
			compCards.add(compCardGraphics[c]);
		}
		deckLabel.setDeck(deck);
		playerCards.revalidate();
		playerCards.repaint();
		compCards.revalidate();
		compCards.repaint();
	}
	/**
	 * This class allows the cards to be dragged and dropped on other cards. To implement, make an instance of this class
	 * the TransferHandler of a CardLabel. Then, enable DnD through a MouseAdapter connected to each CardLabel
	 * whose TransferHandler is this by calling exportAsDrag
	 * @author Morgan
	 *
	 */
	private static class ImageTransferer extends TransferHandler {
		private static final long serialVersionUID = -19201102892920628L;
		private static CardLabel source;
		public ImageTransferer(){
			super("icon");
		}
		@Override
		public void exportAsDrag(JComponent source, InputEvent e, int action) {
			super.exportAsDrag(source, e, action);
			ImageTransferer.source = (CardLabel)source; //exportAsDrag is only called once per dnd operation
		}
		@Override
		public boolean canImport(TransferSupport support) {
			if (!super.canImport(support)) return false;
			if (!support.isDrop()) return false;
			CardLabel onto = ((CardLabel)support.getComponent()); //Where the card is being played
			CardName selectedCard = source.getCardName(); //Name of card being dragged
			CardName underCard  = onto.getCardName(); //Name of card being dragged onto
			//INSERT CONDITIONS HERE ------------
			/*if (onto.getParent() == playerCards //If in player's hand 
					&& onto.getCardName() == CardName.DEFAULT  //Player's hand's slot is empty
					&& source.getParent() == null) //Not an actual cardLabel
				return true;*/
			if (onto == playerBattle) { //Playing onto player's battle pile
				if (getCardType(selectedCard) == REMEDY && getCardType(underCard)==HAZARD) { //Countering Hazard
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == ROLL && getCardType(underCard) == REMEDY) { //Playing Roll after a remedy
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == ROLL && getCardType(underCard) == BLANK) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
			} else if (onto == compBattle){ //Playing onto computer's Battle Pile
				//Shortcutting (no hazard/stop can be played on another)
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == HAZARD) {
					sT = "Cannot place a Hazard onto another Hazard.";
					systemText.setText(sT);
					return false;
				}
				if (getCardType(selectedCard) == STOP && getCardType(underCard) == STOP) {
					sT = "Cannot place a Stop onto another Stop.";
					systemText.setText(sT);
					return false;
				}
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == STOP) {
					sT = "Cannot place a Hazard onto a Stop.";
					systemText.setText(sT);
					return false;
				}
				if (getCardType(selectedCard) == STOP && getCardType(underCard) == HAZARD) {
					sT = "Cannot place a Stop onto a Hazard.";
					systemText.setText(sT);
					return false;
				}
				
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == ROLL) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == REMEDY) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == HAZARD && getCardType(underCard) == BLANK) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == STOP && getCardType(underCard) == ROLL) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == STOP && getCardType(underCard) == REMEDY) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == STOP && getCardType(underCard) == BLANK) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
			} else if (onto == playerSpeed) { //Playing on own Speed Limit Pile
				if (getCardType(selectedCard) == ENDSPEEDLIM && getCardType(underCard) == SPEEDLIM) { //Ending a speed limit
					sT = "";
					systemText.setText(sT);
					return true;
				}
			} else if (onto == compSpeed) { //Playing on Computer's speed pile
				if (getCardType(selectedCard) == SPEEDLIM && getCardType(underCard) == ENDSPEEDLIM) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
				if (getCardType(selectedCard) == SPEEDLIM && getCardType(underCard) == BLANK) {
					sT = "";
					systemText.setText(sT);
					return true;
				}
			} else if (onto == playerMileage) { //Playing on own distance
				if (getCardType(selectedCard) == DISTANCE && hazardPlayer.getName() == CardName.ROLL) {
					if (limitPlayer.getName() == CardName.SPEED_LIMIT) {
						if (selectedCard == CardName.MILE_25 || selectedCard == CardName.MILE_50) {
							sT = "";
							systemText.setText(sT);
							return true;
						}
						sT = "Cannot place a Milage card larger than 50 with a Speed Limit card in effect.";
						systemText.setText(sT);
						return false; //Speed Limit in effect
					}
					sT = "";
					systemText.setText(sT);
					return true; //No Speed Limit
				}
			} else if (onto == compMileage) { //Playing onto computer's distance
				sT = "Cannot place a card onto the opponents distance pile.";
				systemText.setText(sT);
				return false;
			} else if (onto == playerSafety1) {
				if (selectedCard == CardName.RIGHT_OF_WAY)
					return true;
				return false;
			} else if (onto == playerSafety2) {
				if (selectedCard == CardName.DRIVING_ACE)
					return true;
				return false;
			} else if (onto == playerSafety3) {
				if (selectedCard == CardName.EXTRA_TANK)
					return true;
				return false;
			} else if (onto == playerSafety4) {
				if (selectedCard == CardName.PUNCTURE_PROOF)
					return true;
				return false;
			}
			//enter conditions based on getCardType and where source is
			//END CONDITION INSERTION ------------
			return false;
		}
		@Override
		public boolean importData(TransferSupport support) {
			CardLabel dest = (CardLabel)support.getComponent();
			boolean result = super.importData(support);
			CardName c = source.getCardName();
			//source.setCardName(CardName.DEFAULT);
			if (dest == playerBattle) {
				playerBattle.setCardName(c);
				hazardPlayer = Card.getCardFromName(c);
			} else if (dest == playerSpeed) {
				playerSpeed.setCardName(c);
				limitPlayer = Card.getSpeedCardFromName(c);
			} else if (dest == playerMileage) {
				playerMileage.setCardName(c);
				mileagePlayer = Card.getCardFromName(c);
				if (playerDistance + ((MovementCard)mileagePlayer).getDistance() <= 1000){
					if (playerDistance!= 1000){
						playerDistance += ((MovementCard)mileagePlayer).getDistance();
						pTD = playerDistance + "Miles";
						playerTotalDistance.setText(pTD);
					} else if (playerDistance == 1000){
						playerDistance += ((MovementCard)mileagePlayer).getDistance();
						pTD = playerDistance + "Miles";
						playerTotalDistance.setText(pTD);
						wT = "Player has won!";
						winText.setText(wT);
					}
				}
			} else if (dest == compBattle) {
				compBattle.setCardName(c);
				hazardComp = Card.getCardFromName(c);
			} else if (dest == compSpeed) {
				compSpeed.setCardName(c);
				limitComp = Card.getSpeedCardFromName(c);
			} else if (dest == compMileage) {
				//Shouldn't be the case, but included for completeness' sake
				compMileage.setCardName(c);
				mileageComp = Card.getCardFromName(c);
				if (compDistance + ((MovementCard)mileageComp).getDistance() <= 1000){
					if (compDistance != 1000){
						compDistance += ((MovementCard)mileageComp).getDistance();
						cTD = compDistance + "Miles";
						compTotalDistance.setText(cTD);
					} else if (compDistance == 1000){
						compDistance += ((MovementCard)mileageComp).getDistance();
						cTD = compDistance + "Miles";
						compTotalDistance.setText(cTD);
						wT = "Computer has won!";
						winText.setText(wT);
					}
				}
			}
			/*for (int i = 0; i < player.length; i++){
				if (player[i].getCardType(player[i]) == HAZARD){
					
				}
			}
			*/
			if (compCardToReplace != -1) 
				comp[compCardToReplace] = deckLabel.getTopCard();
			int[] compPlay = compPlayer.getBestCard(comp, hazardComp, compSafeties, compDistance, limitComp, hazardPlayer, playerSafeties, playerDistance, limitPlayer);
			Card toPlay = comp[compPlay[0]];
			switch (compPlay[1]) {
			case Constants.OPPOSEBATTLE:playerBattle.setCardName(toPlay.getName());break;
			case Constants.OPPOSELIMIT:playerSpeed.setCardName(toPlay.getName());break;
			case Constants.OWNBATTLE:compBattle.setCardName(toPlay.getName());break;
			case Constants.OWNDIST:compMileage.setCardName(toPlay.getName());break;
			case Constants.OWNLIMIT:compSpeed.setCardName(toPlay.getName());break;
			case Constants.OWNSAFETY:
				switch (toPlay.getName()) {
				case RIGHT_OF_WAY:compSafety1.setCardName(CardName.RIGHT_OF_WAY);break;
				case DRIVING_ACE:compSafety2.setCardName(CardName.DRIVING_ACE);break;
				case EXTRA_TANK:compSafety3.setCardName(CardName.EXTRA_TANK);break;
				case PUNCTURE_PROOF:compSafety4.setCardName(CardName.PUNCTURE_PROOF);break;

				default:
					break;
				}
			case Constants.DISCARD:
				default:
			}
			//get rid of toPlay
			compCardToReplace = compPlay[0];
			comp[compPlay[0]] = null;
			//replace player's missing card (begin player turn)
			for (CardLabel x : playerCardGraphics) {
				if (x.getCardName() == CardName.DEFAULT) {
					x.setCardName(deckLabel.getTopCard().getName());
				}
			}
			return result;
		}
	}
	private static int compCardToReplace = -1;
	public static final int HAZARD=0,REMEDY=1,DISTANCE=2,SAFETY=3,SPEEDLIM=4,ENDSPEEDLIM=5,STOP=6,ROLL=7,BLANK=8;
	public static int getCardType(CardName a) {
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
			
		case DEFAULT:
			return BLANK;
			
		default:
			return -1;
		}
	}
	/*public String win(){
		if (playerDistance == 1000){
			sT = "Player has won!";
		} else if(compDistance == 1000){
			sT = "Computer has won!";
		}
		return sT;
	}*/
}
