package planegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {	
	private boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;

	public Controller() {
		isUP = false;
		isDOWN = false;
		isLEFT = false;
		isRIGHT = false;
		isSPACE = false;
	}
	
	class MyKeyListner implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {

		}
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isLEFT = true;
				break;
			case KeyEvent.VK_RIGHT:
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
	
	public boolean getIsLeft() {
		return isLEFT;
	}
	public boolean getIsRight() {
		return isRIGHT;
	}
	public boolean getIsUp() {
		return isUP;
	}
	public boolean getIsDown() {
		return isDOWN;
	}
}
