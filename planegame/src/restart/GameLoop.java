//package restart;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//
//import javax.swing.Timer;
//import javax.swing.JPanel;
//
//public class GameLoop implements Runnable, ActionListener {
//	private GameModel Model;
//	private GameView View;
//	private GameController Controller;
//	
//	private Player player;
//	
//	private Timer timer;
//	private int delay;
//	private int FPS = 75;
//	private boolean isGame = true;
//	
//	
//	private double lastUpdateTime = System.currentTimeMillis();
//	private double delta = 0;
//	
//	
//	
//	private void initVariables(int fps) {
//		timer = new Timer(1000 / fps, this);
//		timer.start();
//	}
//	
//	public void handleKeyInput(KeyEvent e) {
//		Controller.handleKeyInput(e);
//	}
//	
//	public GameLoop(Player player, GameModel Model, GameController con, GameView View) {
//		this.player = player;
//		this.Model = Model;
//		this.Controller = con;
//		this.View = View;
////		this.delay = delay;
//		initVariables(FPS);
//	}
//	
//	
//	public void updateGame() {
//	}
//	
//	public void setIsGame(boolean isGame) {
//		this.isGame = isGame;
//	}
//
//	
//	@Override 
//	public void actionPerformed(ActionEvent e) {
//		View.repaint();
//	}
//	
//	@Override
//	public void run() {
////		System.out.println("hello");
//		while (isGame) {
//			
//		}
//	}
//}
