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
import java.io.File;

import static java.awt.event.KeyEvent.*;
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

public class GamePanel extends JPanel{

	private final JPanel textPanel;
	private final BackgroundPanel leftBGPanel;
	private final BackgroundPanel rightBGPanel;
	private final JPanel choicesPanel;
	
	private Timer timer;
	private boolean gameOver = false;
	private int counter;
	private Color bgColor;
	boolean warten;
	String entscheidung;
	

	// TODO adjust images
	
	
	public static final String IMAGE_DIR = "images/";
	private final String[] backgroundImages= new String [] {"waldrand" // 0
														   ,"seetal","wurzelzwerge" // 1 , 2
														   ,"fluss","wald","windheim" // 3 , 4 , 5
														   ,"steinheim","felstal" // 6 , 7
														   ,"spitzberg"}; // 8
	private ImageIcon backgroundImage;//= new ImageIcon(getClass().getResource(IMAGE_DIR + backgroundImages[0]));
	
	
	Player player;
	
	private JButton leftButton = new JButton("");
	private JButton middleButton = new JButton("");	
	private JButton rightButton = new JButton("");
	
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
        
        leftBGPanel = new BackgroundPanel(/*backgroundImage.getImage()*/);
		leftBGPanel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_LeftBGPanel = new GridBagConstraints();
		gbc_LeftBGPanel.gridwidth = 4;
		gbc_LeftBGPanel.gridheight = 17;
		gbc_LeftBGPanel.fill = GridBagConstraints.BOTH;
		gbc_LeftBGPanel.gridx = 0;
		gbc_LeftBGPanel.gridy = 0;
		add(leftBGPanel, gbc_LeftBGPanel);
		
		rightBGPanel = new BackgroundPanel(/*backgroundImage.getImage()*/);
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
		GridBagConstraints gbc_textPanel = new GridBagConstraints();
		gbc_textPanel.gridwidth = 21;
		gbc_textPanel.gridheight = 12;
		gbc_textPanel.fill = GridBagConstraints.BOTH;
		gbc_textPanel.gridx = 4;
		gbc_textPanel.gridy = 0;
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
		
		
		
		label_choice1.setVisible(false);
		label_choice2.setVisible(false);
		
		leftButton.setActionCommand("left");
		middleButton.setActionCommand("middle");
		rightButton.setActionCommand("right");		
		
		leftButton.setVisible(false);
		middleButton.setVisible(false);
		rightButton.setVisible(false);
		repaint();
		registerButtonListener();
		repaint();
		
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
		// TODO erstelle StartLayout für Panels
		setBackgroundImage(0);
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
		counter = 0;
		setGameOver(false);
		createGameStart();
		startGame();
	}
	public void setBackgroundImage(int imageNumber) {
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
		
	
		
	public void selectDifficulty() {

		warten = true;
		
		choicesPanel.setBackground(Color.DARK_GRAY);
		label_choice1.setForeground(Color.WHITE);
		label_choice2.setForeground(Color.WHITE);
		
		label_choice1.setText("Wähle die Schwierigkeit!");
		label_choice2.setText("Einfach: 5 Leben - Mittel: 3 Leben - Schwer: 1 Leben");
		
		leftButton.setText("Einfach");
		middleButton.setText("Mittel");
		rightButton.setText("Schwer");
		
		label_choice1.setVisible(true);
		label_choice2.setVisible(true);
		leftButton.setVisible(true);
		middleButton.setVisible(true);
		rightButton.setVisible(true);
//		while(warten) {
//		}
//		switch(entscheidung) {
//		case "left":
//			player.setLife(5);
//			break;
//		case "middle":
//			player.setLife(3);
//			break;
//		case "right":
//			player.setLife(1);
//			break;
//		}
	}
	
	private void registerButtonListener() {        
        leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				entscheidung = leftButton.getActionCommand();
				warten = false;
			}
		});
		middleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent mb) {
				entscheidung = middleButton.getActionCommand();
				warten = false;
			}
		});
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent rb) {
				entscheidung = rightButton.getActionCommand();
				warten = false;
			}
		});
    }
}
