package restart;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController {
    private GameView View;
    private GameModel Model;
    private Player player;
    
	private MyPropertyListener myPl;
	
	private KeyEvent e;
	private boolean isPressed;
	
    private boolean[] keyStates = new boolean[256];
    
    public GameController(Player player, GameModel Model, GameView View) {
    	this.player = player;
    	this.Model = Model;
    	this.View = View;
    	this.Model.addPropertyChangeListener(myPl);
    }

	public void handleKeyInput(boolean keyStates[]) {
//		int key = View.getKeyStates()[]
        if (keyStates[KeyEvent.VK_UP]) {
        	player.moveUp();
        }
        if (keyStates[KeyEvent.VK_DOWN]) 
            player.moveDown();
        if (keyStates[KeyEvent.VK_LEFT])
        	player.moveLeft();
        if (keyStates[KeyEvent.VK_RIGHT])
        	player.moveRight();
	}

	public KeyEvent getKeyEvent() {
		return e;
	}
	public boolean getPressed() {
		return isPressed;
	}
    
}
