package restart;

import java.awt.event.KeyEvent;

import planegame.GameLoop;
import planegame.GameView;

public class GameController implements Movable {
    private GameView gameView;
    private GameLoop gameLoop;
    private Player player;
    
    public GameController() {
    	this.player = new Player();
    }
	
	@Override
	public void moveUp() {
    	
    };
    @Override
    public void moveDown() {
    	
    };
    @Override
    public void moveLeft() {
    	
    };
    @Override
    public void moveRight() {
    	
    }

	public void handleKeyInput(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.move(0, -1);
			break;
		case KeyEvent.VK_DOWN:
			player.move(0, 1);
			break;
		case KeyEvent.VK_LEFT:
			player.move(-2, 0);
			break;
		case KeyEvent.VK_RIGHT:
			player.move(2, 0);
			break;
		}
	};
    
}
