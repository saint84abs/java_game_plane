package restart;

import java.awt.event.KeyEvent;
import java.lang.ModuleLayer.Controller;

public class GameController {
    private GameView View;
    private GameModel Model;
    private GameLoop Loop;
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
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("UP!!");
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
		
//		case KeyEvent.VK_E:
//			Loop.setIsGame(true);
//			break;
		}
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
