package adventure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.html.StyleSheet;
import static java.awt.event.KeyEvent.*;

import java.awt.BorderLayout;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GamePanel extends JPanel {

	private final JPanel textPanel;
	private final BackgroundPanel leftBGPanel;
	private final BackgroundPanel rightBGPanel;
	private final JPanel choicesPanel;
	
	private Timer timer;
	private boolean gameOver;
	private int counterBackground;
	private int counterStory; // mambo jambo Code, storyCounter gets increased then used..... //FIXME IF workaround found check all instances
	private Color bgColor;
	boolean warten;
	String choice;
	
	public static final String IMAGE_DIR = "images/"; 
	private final String[] backgroundImages= new String []  {"home" //
															,"waldrand" //
															,"seetal","wurzelzwerge" // 
															,"fluss","wald","windheim" // 
															,"steinheim","felstal" // 
															,"spitzberg"}; // 
	private ImageIcon backgroundImage;
	
	public static final String STORY_DIR = "story/";  //FIXME set style of text via <html></html>
	private final int[] storyChapters= new int [] {3,2,2,3,6,1,3};	//Count of "Storysteps" (visible story in the textpanel) per chapter, Steps start at 1!!
													//TODO finish story create and update steps, !!current numbers just a test!!
	
	Player player;
	
	private JButton leftButton = new JButton("");
	private JButton middleButton = new JButton("");	
	private JButton rightButton = new JButton("");
	
	JLabel label_Text;
	
	JLabel label_choice1 = new JLabel("");
	JLabel label_choice2 = new JLabel("");
	
	public GamePanel() {        
        setFocusable(true);
        setBackground(Color.GREEN);
                
        GridBagLayout gbl_MainPanel = new GridBagLayout();
										
		gbl_MainPanel.columnWidths = new int[]{40, 40, 40, 40, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 0};
		gbl_MainPanel.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 30, 30, 30, 30, 30, 0};
		gbl_MainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_MainPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_MainPanel);
        
        leftBGPanel = new BackgroundPanel();
		leftBGPanel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_LeftBGPanel = new GridBagConstraints();
		gbc_LeftBGPanel.gridwidth = 4;
		gbc_LeftBGPanel.gridheight = 17;
		gbc_LeftBGPanel.fill = GridBagConstraints.BOTH;
		gbc_LeftBGPanel.gridx = 0;
		gbc_LeftBGPanel.gridy = 0;
		add(leftBGPanel, gbc_LeftBGPanel);
		
		rightBGPanel = new BackgroundPanel();
		rightBGPanel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_RightBGPanel = new GridBagConstraints();
		gbc_RightBGPanel.gridheight = 17;
		gbc_RightBGPanel.gridwidth = 4;
		gbc_RightBGPanel.fill = GridBagConstraints.BOTH;
		gbc_RightBGPanel.gridx = 25;
		gbc_RightBGPanel.gridy = 0;
		add(rightBGPanel, gbc_RightBGPanel);
		
		textPanel = new JPanel();
		textPanel.setBackground(Color.RED);
		textPanel.setLayout(new BorderLayout());
		GridBagConstraints gbc_textPanel = new GridBagConstraints();
		gbc_textPanel.gridwidth = 21;
		gbc_textPanel.gridheight = 12;
		gbc_textPanel.fill = GridBagConstraints.BOTH;
		gbc_textPanel.gridx = 4;
		gbc_textPanel.gridy = 0;
		label_Text = new JLabel();
		textPanel.add(label_Text, BorderLayout.CENTER);
		add(textPanel, gbc_textPanel);
		
				
		choicesPanel = new JPanel();
		choicesPanel.setBackground(Color.BLUE);
		GridBagConstraints gbc_ChoicesPanel = new GridBagConstraints();
		gbc_ChoicesPanel.gridheight = 5;
		gbc_ChoicesPanel.gridwidth = 21;
		gbc_ChoicesPanel.fill = GridBagConstraints.BOTH;
		gbc_ChoicesPanel.gridx = 4;
		gbc_ChoicesPanel.gridy = 12;
		add(choicesPanel, gbc_ChoicesPanel);
        
		label_choice1.setHorizontalAlignment(SwingConstants.CENTER);
		label_choice1.setVerticalAlignment(SwingConstants.CENTER);
		
		label_choice2.setHorizontalAlignment(SwingConstants.CENTER);
		label_choice2.setVerticalAlignment(SwingConstants.CENTER);
		
		GroupLayout gl_choicesPanel = new GroupLayout(choicesPanel);
		gl_choicesPanel.setHorizontalGroup(
			gl_choicesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_choicesPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_choice1, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_choicesPanel.createSequentialGroup()
					.addGap(178)
					.addComponent(leftButton, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(middleButton, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rightButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(218))
				.addGroup(Alignment.TRAILING, gl_choicesPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_choice2, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_choicesPanel.setVerticalGroup(
			gl_choicesPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_choicesPanel.createSequentialGroup()
					.addComponent(label_choice1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_choice2)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_choicesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(leftButton)
						.addComponent(middleButton)
						.addComponent(rightButton))
					.addGap(60))
		);
		choicesPanel.setLayout(gl_choicesPanel);		
		
		setALLOptionsVisible(false);
		
		registerButtonListener();
				
        initGame();         
        startGame();
    }
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public void checkGameOver() {
		if(player.life<1) {
			gameOver = true;
		}
	}

	private void initGame () {
		gameOver = false;
		counterBackground = 0;
		counterStory = -1; // mambo jambo Code, storyCounter gets increased then used..... //FIXME IF workaround found check all instances
		timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doOnTick();     
            }
        });
		player = new Player();
		createGameStart();
	}
	private void createGameStart() {
		textPanel.setBackground(Color.BLACK);
		//TODO create Gameinfo at Start
		label_Text.setText("<html><font color='white'>GAME START!</font>");
		//FIXME set style of text via <html></html>
		//label_Text.setForeground(Color.WHITE);
		label_Text.setHorizontalAlignment(SwingConstants.CENTER);
		label_Text.setVerticalAlignment(SwingConstants.CENTER);
		
		choicesPanel.setBackground(Color.DARK_GRAY);
		setBackgroundImage(counterBackground);
	}
	private void doOnTick() {
	
		textPanel.revalidate();
		leftBGPanel.revalidate();
		rightBGPanel.revalidate();
		choicesPanel.revalidate();
		
		textPanel.repaint();
		leftBGPanel.repaint();
		rightBGPanel.repaint();
		choicesPanel.repaint();
		
		repaint();
	}
	public void startGame() {
		timer.start();
		selectDifficulty();
	}
	
	public void pauseGame() {
		timer.stop();
	}
	
	public void continueGame() {
		if (!isGameOver()) {
			timer.start();
		}
	}
	public void restartGame() {
		setALLOptionsVisible(false);
		counterBackground = 0;
		counterStory = -1;
		setGameOver(false);
		createGameStart();
		startGame();
	}
	public void setBackgroundImage(int imageNumber) {
		// prevent OutOfBounds
		if(imageNumber>=0 && imageNumber<backgroundImages.length) {
			//Linkes Hintergrundbild
			String imagePath = IMAGE_DIR + backgroundImages[imageNumber] + "_left.jpg";
			URL imageURL = getClass().getResource(imagePath);        
			backgroundImage = new ImageIcon(imageURL);
			leftBGPanel.setImage(backgroundImage.getImage());
			//rechtes Hintergrundbild
			imagePath = IMAGE_DIR + backgroundImages[imageNumber] + "_right.jpg";
			imageURL = getClass().getResource(imagePath);        
			backgroundImage = new ImageIcon(imageURL);
			rightBGPanel.setImage(backgroundImage.getImage());
		}
	}
			
	public void selectDifficulty() {

		warten = true;
		
		label_choice1.setForeground(Color.WHITE);
		label_choice2.setForeground(Color.WHITE);
		
		label_choice1.setText("Wähle die Schwierigkeit!");
		label_choice2.setText("Einfach: 5 Leben - Mittel: 3 Leben - Schwer: 1 Leben");
		
		leftButton.setText("Einfach");
		middleButton.setText("Mittel");
		rightButton.setText("Schwer");
		
		leftButton.setActionCommand("Einfach");
		middleButton.setActionCommand("Mittel");
		rightButton.setActionCommand("Schwer");
		
		setALLOptionsVisible(true);
	}
	
	public void nextStory() {
		counterStory++;
		runStory();
	}
	
	public void runStory() {
		String story = getStory();
		label_Text.setText(story);
		label_Text.setVisible(true);
	}
	
	public void doChoice() {
		switch(choice) {
			case "Einfach":
				player.setLife(5);
				nextStory();
				break;
			case "Mittel":
				player.setLife(3);
				nextStory();
				break;
			case "Schwer":
				player.setLife(1);
				nextStory();
				break;
		}
	}
	private String getStory() {
		String storyPath = STORY_DIR + counterStory + ".txt"; // relative File-Path
				
		FileReader in;
		BufferedReader br;
		String line = "";
		String chapter = "";
		
		try {
			in = new FileReader(this.getClass().getResource(storyPath).getPath()); // gets the absolute Path from the relative Path
			br = new BufferedReader(in);
			
		    while ((line = br.readLine()) != null) {
		    	chapter += line+"<br>";	        
		    }
		    br.close();
		    in.close();
		} catch (IOException e) {
			chapter = "ERROR: FILE NOT FOUND! Can't display text";
			e.printStackTrace();
		}
		return "<html><font color='white'>"+chapter+"</font>";
	}
	
	private void setALLOptionsVisible(boolean option) {
		
		label_Text.setVisible(option);
		
		label_choice1.setVisible(option);
		label_choice2.setVisible(option);
		
		leftButton.setVisible(option);
		middleButton.setVisible(option);
		rightButton.setVisible(option);
	}
	
	private void registerButtonListener() {        
        leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = leftButton.getActionCommand();
				setALLOptionsVisible(false);
				doChoice();
			}
		});
		middleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent mb) {
				choice = middleButton.getActionCommand();
				setALLOptionsVisible(false);
				doChoice();
			}
		});
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent rb) {
				choice = rightButton.getActionCommand();
				setALLOptionsVisible(false);
				doChoice();
			}
		});
    }
}
