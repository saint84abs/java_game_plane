package restart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class GameLoop implements Runnable{
	private GameModel Model;
	private GameView View;
	private GameController Controller;
	
	private Player player;
	
	private Timer timer;
	private int delay;
	private int FPS = 75;
	private boolean isGame = true;
	
	private GamePanel Panel;
	
	private double lastUpdateTime = System.currentTimeMillis();
	private double delta = 0;
	
	
	
//	private void initVariables(int fps) {
//		timer = new Timer(1000 / fps, this);
//		timer.start();
//	}
	
	public GameLoop(Player player, GameModel Model, GameController con, GamePanel Panel) {
		this.player = player;
		this.Model = Model;
		this.Controller = con;
		this.Panel = Panel;
//		this.delay = delay;
//		initVariables(FPS);
	}
	
	public void updateGame() {
		Panel.repaint();
	}
	
	public void setIsGame(boolean isGame) {
		this.isGame = isGame;
	}
	
	
	@Override
	public void run() {
		while (isGame) {
			double now = System.currentTimeMillis();
			double updateLength = now - lastUpdateTime;
			lastUpdateTime = now;
			delta += updateLength;
			
			while (delta >= 1) {
				updateGame();
				delta--;
			}
			// renderGame();			
		}
	}
}
