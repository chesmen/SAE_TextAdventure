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
import static java.awt.event.KeyEvent.*;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;

public class GamePanel extends JPanel{

	private final JPanel textPanel;
	private final JPanel leftBGPanel;
	private final JPanel rightBGPanel;
	private final JPanel choicesPanel;
	
	private final ButtonGroup buttonGroupSchwierigkeit = new ButtonGroup();
	
	private Timer timer;
	private boolean gameOver = false;
	private int counter;
	private Color bgColor;
	
	public GamePanel() {        
        setFocusable(true);
        
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
		// TODO Auto-generated method stub
		
	}
	private void doOnTick() {
		
	}
	public void startGame() {
		timer.start();
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

}
