package adventure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

	private final GamePanel mainPanel;
	
	
	public GameWindow(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		registerWindowListener();
		createMenu();
		
	    setTitle("Text Adventure");
	    setLocation(10, 10);
	    setResizable(true);
	    
	    pack();
	    setVisible(true);
		
		boolean startEntscheidung = true;
		while(startEntscheidung) {
			JPanel preGamePanel = new JPanel();
			preGamePanel.setBackground(Color.DARK_GRAY);
			JLabel text = new JLabel("Wähle die Schwierigkeit!");
			text.setLocation(this.getWidth()/2-text.getWidth()/2, this.getHeight()/5);
			preGamePanel.add(text);
			}
		
		mainPanel = new GamePanel();
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel);
			
		
	}
	private void registerWindowListener() {        
        addWindowListener(new WindowAdapter() {            
            @Override
            public void windowClosing(WindowEvent e) { 
                System.exit(0); 
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                // hier wird das Spiel pausiert
                mainPanel.pauseGame();
            }
            @Override
            public void windowActivated(WindowEvent e) {
                // hier wird das Spiel wieder fortgesetzt
                mainPanel.continueGame();
            }            
        });        
    }
	private void createMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu dateiMenu = new JMenu("Datei");
		JMenu spielMenu = new JMenu("Spiel");
		JMenu optionenMenu = new JMenu("Optionen");
		
		menuBar.add(dateiMenu);
		menuBar.add(spielMenu);
		menuBar.add(optionenMenu);
		
		addItemsDateiMenu(dateiMenu);
		addItemsSpielMenu(spielMenu);
		addItemsOptionenMenu(optionenMenu);
	}
	
	private void addItemsDateiMenu(JMenu dateiMenu) {
		
		JMenuItem quitItem = new JMenuItem("Schließen");
		dateiMenu.add(quitItem);
		quitItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            System.exit(0);
	        }
	    }); 
	}
	
	private void addItemsSpielMenu(JMenu spielMenu) {
		
		JMenuItem pauseItem = new JMenuItem("Pause");
		JMenuItem continueItem = new JMenuItem("Fortsetzen");
		JMenuItem restartItem = new JMenuItem("Neues Spiel");
		
		spielMenu.add(pauseItem);
		spielMenu.add(continueItem);
		spielMenu.add(restartItem);
		
		pauseItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.pauseGame();
			}			
		});
		continueItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.continueGame();
			}
		});
		restartItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.restartGame();
			}
		});	
	}
	
	private void addItemsOptionenMenu(JMenu optionenMenu) {
		// TODO Auto-generated method stub
		JMenuItem changeBackgroundItem = new JMenuItem("Farben ändern..");
		
		optionenMenu.add(changeBackgroundItem);
		
		changeBackgroundItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				//TODO
			}
		});
		
	}
}
