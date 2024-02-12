package restart;

import java.awt.event.KeyEvent;

public class GameController {
    private GameView View;
    private GameModel Model;
    private Player player;
    
	private MyPropertyListener myPl;
	
	private KeyEvent e;
	private boolean isPressed;
    
    public GameController(Player player, GameModel Model) {
    	this.player = player;
    	this.Model = Model;
    	this.Model.addPropertyChangeListener(myPl);
    }

	public void handleKeyInput(KeyEvent e) {
//		if (keyStates[KeyEvent.VK_UP])
		player.move(e);
	}
	
	public void setKeyState(KeyEvent e, boolean isPressed) {
		this.e = e;
		this.isPressed = isPressed;
	}
	
	public KeyEvent getKeyEvent() {
		return e;
	}
	public boolean getPressed() {
		return isPressed;
	}
    
}
