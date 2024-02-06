package restart;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import planegame.GameModel;
import planegame.Player;

public class GameView extends JPanel implements KeyListener {
	private GameModel gameModel = new GameModel();
	private Player player;
	private Graphics2D g2d;
	private BufferedImage backBuffer;
	
	private volatile boolean isUP, isDOWN, isLEFT = true, isRIGHT, isSPACE;
	
	private MyPropertyListener myPl;
	public GameView() {
		player = new Player();
		gameModel.addPropertyChangeListener(myPl);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.drawImage(gameModel.getBackGroundImage(), 0, 0, this);
		player.drawPlane(gameModel.getPlayerImage(), g2d, this);
		
	}
	
    @Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			isLEFT = true;
			System.out.println("left" + isLEFT);
			break;
		case KeyEvent.VK_RIGHT:
//			System.out.println("right");
			isRIGHT = true;
			break;
		case KeyEvent.VK_UP:
			isUP = true;
			break;
		case KeyEvent.VK_DOWN:
			isDOWN = true;
			break;
		case KeyEvent.VK_SPACE:
			isSPACE = true;
			break;
		}
	}
	@Override
	public synchronized void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			isLEFT = false;
			System.out.println("left" + isLEFT);
			break;
		case KeyEvent.VK_RIGHT:
			isRIGHT = false;
			break;
		case KeyEvent.VK_UP:
			isUP = false;
			break;
		case KeyEvent.VK_DOWN:
			isDOWN = false;
			break;
		case KeyEvent.VK_SPACE:
			isSPACE = false;
			break;
		}
	}
	
}
