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

import static java.awt.event.KeyEvent.*;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
	private final JPanel leftBGPanel;
	private final JPanel rightBGPanel;
	private final JPanel choicesPanel;
	
	private Timer timer;
	private boolean gameOver = false;
	private int counter;
	private Color bgColor;
	boolean warten;
	String entscheidung;
	
	private JButton leftButton = new JButton("");
	private JButton middleButton = new JButton("");	
	private JButton rightButton = new JButton("");
	
	JLabel label1 = new JLabel("");
	JLabel label2 = new JLabel("");
	
	public GamePanel() {        
        setFocusable(true);
        setBackground(Color.GREEN);
        GridBagLayout gbl_MainPanel = new GridBagLayout();
										
		gbl_MainPanel.columnWidths = new int[]{40, 40, 40, 40, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 0};
		gbl_MainPanel.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 30, 30, 30, 30, 30, 0};
		gbl_MainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_MainPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_MainPanel);
        
        leftBGPanel = new JPanel();
		leftBGPanel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_LeftBGPanel = new GridBagConstraints();
		gbc_LeftBGPanel.gridwidth = 4;
		gbc_LeftBGPanel.gridheight = 17;
		gbc_LeftBGPanel.insets = new Insets(0, 0, 0, 5);
		gbc_LeftBGPanel.fill = GridBagConstraints.BOTH;
		gbc_LeftBGPanel.gridx = 0;
		gbc_LeftBGPanel.gridy = 0;
		add(leftBGPanel, gbc_LeftBGPanel);
		
		rightBGPanel = new JPanel();
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
		gbc_textPanel.insets = new Insets(0, 0, 5, 5);
		gbc_textPanel.fill = GridBagConstraints.BOTH;
		gbc_textPanel.gridx = 4;
		gbc_textPanel.gridy = 0;
		add(textPanel, gbc_textPanel);
		
				
		choicesPanel = new JPanel();
		choicesPanel.setBackground(Color.BLUE);
		GridBagConstraints gbc_ChoicesPanel = new GridBagConstraints();
		gbc_ChoicesPanel.insets = new Insets(0, 0, 0, 5);
		gbc_ChoicesPanel.gridheight = 5;
		gbc_ChoicesPanel.gridwidth = 21;
		gbc_ChoicesPanel.fill = GridBagConstraints.BOTH;
		gbc_ChoicesPanel.gridx = 4;
		gbc_ChoicesPanel.gridy = 12;
		add(choicesPanel, gbc_ChoicesPanel);
        
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setVerticalAlignment(SwingConstants.CENTER);
		
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setVerticalAlignment(SwingConstants.CENTER);
		
		GroupLayout gl_choicesPanel = new GroupLayout(choicesPanel);
		gl_choicesPanel.setHorizontalGroup(
			gl_choicesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_choicesPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
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
					.addComponent(label2, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_choicesPanel.setVerticalGroup(
			gl_choicesPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_choicesPanel.createSequentialGroup()
					.addComponent(label1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label2)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_choicesPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(leftButton)
						.addComponent(middleButton)
						.addComponent(rightButton))
					.addGap(60))
		);
		choicesPanel.setLayout(gl_choicesPanel);
		
		
		
		label1.setVisible(false);
		label2.setVisible(false);
		leftButton.setVisible(false);
		middleButton.setVisible(false);
		rightButton.setVisible(false);
		
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

	private void initGame () {
		timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doOnTick();     
            }
        });
	}
	private void createGameObjects() {
		// TODO Mal schauen ob das hier benötigt wird
		
	}
	private void doOnTick() {
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
		createGameObjects();
		startGame();
	}
	
	public void selectDifficulty() {

		warten = true;
		
		choicesPanel.setBackground(Color.DARK_GRAY);
		label1.setForeground(Color.WHITE);
		label2.setForeground(Color.WHITE);
		
		label1.setText("Wähle die Schwierigkeit");
		label2.setText("Einfach: 5 Leben - Mittel: 3 Leben - Schwer: 1 Leben");
		
		leftButton.setText("Einfach");
		middleButton.setText("Mittel");
		rightButton.setText("Schwer");
		
		label1.setVisible(true);
		label2.setVisible(true);
		leftButton.setVisible(true);
		middleButton.setVisible(true);
		rightButton.setVisible(true);
		repaint();
		//TODO Spieler Klasse erstellen
		while(warten) {
		}
		switch(entscheidung) {
		case "Einfach":
			
		case "Mittel":
		case "Schwer":
		}
		
	}
	
	private void registerButtonListener() {        
        leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				entscheidung = leftButton.getText();
				warten = false;
			}
		});
		middleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent mb) {
				entscheidung = middleButton.getText();
				warten = false;
			}
		});
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent rb) {
				entscheidung = rightButton.getText();
				warten = false;
			}
		});
		
    }
}
