package planegame;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.tools.javac.Main;

public class game extends JFrame {
	private JButton bt_1 = new JButton();
	private JButton bt_2 = new JButton();
	
	private ImageIcon background = new ImageIcon("../planegame/image/myBackGround.jpg");
	
	private Image backImage = Toolkit.getDefaultToolkit().getImage("../planegame/image/myBackGround.jpg");
	private Image planeImage = Toolkit.getDefaultToolkit().getImage("../planegame/image/myPlane.png");
	
	private JLabel myPlaneLabel = new JLabel();
	
	private Thread thread_plane = new Thread(new MyRunnable());
	
	private int x = 200, y = 720;
	private boolean isUP, isDOWN, isLEFT, isRIGHT;
	
	//double buffering용 변수
	private Image img;
	private Graphics img_g;
	
	public game(String title) {
		super(title);
		setBounds(300, 150, background.getIconWidth(), background.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		addKeyListener(new MykeyListner());
		
		createBufferStrategy(2);
		
		thread_plane.start();
		// 스레드 시작시 keylistner에서 thread로 true, false 로 값 전달
		// 실질적인 이동은 thread에서
		// https://dreamchallenger.blogspot.com/2011/08/keylistener-thread.html

		
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

		
		g.drawImage(planeImage, x, y, this);
	}
	
	public static void main(String args[]) {
		game g = new game("1944");
		
	}
	
	public void Move() {
		//https://binghedev.tistory.com/56
		// 비행기가 계속 깜빡거리는데, 더블 버퍼링을 통해 해결 가능?
		while(isUP == true || isDOWN == true || isLEFT == true || isRIGHT == true) {
			if (isUP == true) {
				if (y > 450)
					y -= 1;
				else 
					y = 450;
				repaint();
			}
			if (isDOWN == true) {
				y += 1;
				repaint();
			}
			if (isLEFT == true) {
				if (x < 0) 
					x = 0;
				else 
					x -= 1;
				repaint();
			}
			if (isRIGHT == true) {
				x += 1;
				repaint();
			}
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class MyRunnable implements Runnable {
		private volatile boolean isRunning = true;
		
		@Override
		public void run() {
			while(isRunning) {
				try {
					Thread.sleep(30);
					Move();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class MykeyListner implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
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
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
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
			}
		}
	}
}
