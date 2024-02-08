package planegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import restart.GameView;


public class GameController implements KeyListener {
    private GameView gameView;
    private GameLoop gameLoop;
    private Thread loopThread;
	private volatile boolean isUP, isDOWN, isLEFT = true, isRIGHT, isSPACE;
	
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
// 값 변경되고 있음? -> isLEFT를 true로 설정하니 키 입력값에 따라 변경안됨
// 키가 눌렸다 떼지면 false가 되어야 하지만 계속 true
// player에서 한 번 입력받은 값이 고정되거나, controller 객체가 생성되고 변수가 초기화되지 않을 가능성
//
// 키 입력받음 -> AWT스레드에서 isLEFT true로 바뀜 -> AWT스레드에서 다시 false로 바뀜
// 이후에 메인 스레드에 전달?(디버그 순서상 메인스레드) -> 결국 전달받는 값은 false값(혹은 변동없음 / 전달받지도 못함)
// 
/*
 * 키 입력받음 -> keypressed에서 isLEFT가 true로 바뀜(AWT thread) -> 
 * 이후 코드가 진행되다 keyRelease에서 isLEFT가 false로 바뀜(AWT thread) ->
 * 다음에 getIsLeft메소드에서 isLEFT값 호출, 전달받는것은 false값
 * 따라서 player객체엔 false값 전달...
 * 하지만 왜 키를 꾹 누르고 있음에도 isLEFT가 false값으로 전달되는지는 의문
 * 아마도 synchronized문제는 아닌 것 같은데, 
 * 
 * 다만, isLEFT가 true로 설정되어 있는 것을 고려했을 때, 이대로 실행한다면 player객체에
 * true값만이 전달됨. 다시말해, 키 리스너로 수정되는 isLEFT값이 반영되지 않는다는거
 * 
 * setFocusable이 gamepanel에 설정되어 있어서 그랬던듯?
 */
    
	
	
	public boolean getIsLeft() {
	    System.out.println("getter method left is called, " + isLEFT);
	    return isLEFT;
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


    @Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public synchronized void keyPressed(KeyEvent e) {
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
	public synchronized void keyReleased(KeyEvent e) {
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
	
	public void start() {
		Thread thread = new Thread();
		// TODO Auto-generated method stub
		thread.start();
	}
}
