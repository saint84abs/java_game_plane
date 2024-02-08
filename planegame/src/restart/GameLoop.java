package restart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class GameLoop implements Runnable, ActionListener{
	private GameModel Model;
	private GameView View;
	private GameController Controller;
	
	private Player player;
	
	private Timer timer;
	private int delay;
	private int FPS = 75;
	
	private JPanel Panel;
	
	
	private void initVariables(int fps) {
		timer = new Timer(1000 / fps, this);
		timer.start();
	}
	

	
	
	public GameLoop(Player player, GameModel Model, GameController con, JPanel Panel, int delay) {
		this.player = player;
		this.Model = Model;
		this.Controller = con;
		this.Panel = Panel;
		this.delay = delay;
		initVariables(FPS);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		Panel.repaint();
	}
	
	@Override
	public void run() {

		
		
	}
}
