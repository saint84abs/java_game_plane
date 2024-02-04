package planegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameController implements KeyListener {
    private GameView gameView;
    private GameLoop gameLoop;
    private Thread loopThread;
	private volatile boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;
	
	public GameController() {
		System.out.println("controller is called");

	}
	
    public GameController(GameView gameView, GameLoop gameLoop) {
//        this.gameView = gameView;
//        this.gameLoop = gameLoop;
//
//        loopThread = new Thread(gameLoop);
//        loopThread.start();
    }

    // 키 입력받고 있음
    // 값 변경되고 있음
    // 키 입력받음 -> AWT스레드에서 isLEFT true로 바뀜 -> AWT스레드에서 다시 false로 바뀜
    // 이후에 메인 스레드에 전달 -> 결국 전달받는 값은 false값.
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			isLEFT = true;
			System.out.println("left" + isLEFT);
			break;
		case KeyEvent.VK_RIGHT:
//			System.out.println("right");
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
			System.out.println("left" + isLEFT);
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
	
	
	public boolean getIsLeft() {
	    boolean result;
	    synchronized(this) {
	        result = isLEFT;
//	        isLEFT = false;
	    }
	    System.out.println("getter method left is called " + result);
	    return result;
	}
	public boolean getIsRight() {
		System.out.println("getter method right is called " + isRIGHT);
		return isRIGHT;
	}
	public boolean getIsUp() {
		System.out.println("getter method up is called " + isUP);
		return isUP;
	}
	public boolean getIsDown() {
		System.out.println("getter method down is called " + isDOWN);
		return isDOWN;
	}

	public void start() {
		Thread thread = new Thread();
		// TODO Auto-generated method stub
		thread.start();
	}
}
