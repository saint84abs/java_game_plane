package restart;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class GameView extends JPanel implements KeyListener {
	private GameModel gameModel = new GameModel();
	private Player player;
	private Graphics2D g2d;
	private BufferedImage backBuffer;
	private GameController gameController;
	
	private volatile boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;
	
	private MyPropertyListener myPl;
	
	public GameView(GameController controller) {
		player = new Player();
		gameModel.addPropertyChangeListener(myPl);
		this.gameController = controller;
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				gameController.handleKeyInput(e);
			}
		});
	}
	
	// 화면 출력
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.drawImage(gameModel.getBackGroundImage(), 0, 0, this);
		player.drawPlane(gameModel.getPlayerImage(), g2d, this);
		g.drawImage(backBuffer, 0, 0, this);
		
	}
	
	// 키 입력
    @Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
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
	public void keyReleased(KeyEvent e) {
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
