package planegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameController implements KeyListener {
    private GameView gameView;
    private GameLoop gameLoop;
    private Thread loopThread;

    public GameController(GameView gameView, GameLoop gameLoop) {
        this.gameView = gameView;
        this.gameLoop = gameLoop;

        loopThread = new Thread(gameLoop);
        loopThread.start();
    }
    
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    // KeyListener methods...

	public void start() {
		Thread thread = new Thread();
		// TODO Auto-generated method stub
		thread.start();
	}
}
