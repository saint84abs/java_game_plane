package restart;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class GameView extends JFrame {
	private GameModel Model;
	private GameView View;
	private GameLoop Loop;
	private GameController Controller;
	
	private Player player;
	
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	
    private JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g;
            g2d.drawImage(Model.getBackGroundImage(), 0, 0, this);
            player.drawPlane(Model.getPlayerImage(), g2d, this);
            g.drawImage(backBuffer, 0, 0, this);
        }
    };
	
	public GameView(String title) {
		super(title);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		player = new Player();
		Model = new GameModel(player);
		Controller = new GameController(player, Model);
		
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("Pressed key!");
				Controller.handleKeyInput(e);
			}
		});
		
		backBuffer = new BufferedImage
				(Model.getBackGroundIcon().getIconWidth(),
				Model.getBackGroundIcon().getIconHeight(), 
				BufferedImage.TYPE_INT_ARGB);
		g2d = backBuffer.createGraphics();
			
		Loop = new GameLoop(player, Model, Controller, this);
		Thread thread = new Thread(Loop);
		thread.start();
		
		add(panel);
		setBounds(300, 100,
				Model.getBackGroundIcon().getIconWidth(),
				Model.getBackGroundIcon().getIconHeight());
	}

}
