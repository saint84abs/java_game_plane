package restart;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class GameView extends JPanel {
	private GameModel Model;
	private GameController Controller;
	
	private Player player;

	
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	
	private volatile boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;
	
	private MyPropertyListener myPl;
	
	public GameView(Player player, GameModel Model, GameController con) {
		this.player = player;
		this.Model = Model;
		this.Model.addPropertyChangeListener(myPl);
		this.Controller = con;
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("Pressed key!");
				Controller.handleKeyInput(e);
			}
		});
	}
	
	// 화면 출력
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.drawImage(Model.getBackGroundImage(), 0, 0, this);
		player.drawPlane(Model.getPlayerImage(), g2d, this);
		g.drawImage(backBuffer, 0, 0, this);
		
	}
	
	
}
