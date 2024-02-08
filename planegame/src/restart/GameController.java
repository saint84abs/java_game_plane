package restart;

import java.awt.event.KeyEvent;

import planegame.GameLoop;
import planegame.GameView;

public class GameController {
    private GameView gameView;
    private GameModel gameModel;
    private GameLoop gameLoop;
    private Player player;
    
	private MyPropertyListener myPl;
    
    public GameController(Player player, GameModel gameModel) {
    	this.player = player;
    	this.gameModel = gameModel;
    	this.gameModel.addPropertyChangeListener(myPl);
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
