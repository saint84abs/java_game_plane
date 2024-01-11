package planegame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GamePanel extends JPanel {
	// double buffering용 변수
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	// 화면에 출력되는 이미지용 변수
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	
	// 키 이벤트용 변수
	private int x = 200, y = 720;
	private boolean isUP, isDOWN, isLEFT, isRIGHT;
	// 스레드용 변수
	private Thread thread_plane = new Thread(new MyRunnable());
	
	public GamePanel() {
		setFocusable(true);
		setVisible(true);
		addKeyListener(new MyKeyListner());
		thread_plane.start();
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    backBuffer = new BufferedImage(background.getIconWidth(), background.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	    g2d = backBuffer.createGraphics();
	    g2d.drawImage(backImage, 0, 0, this);
	    g2d.drawImage(planeImage, x, y, this);
	    g.drawImage(backBuffer, 0, 0, this);
	}

	public void Move() {
		while(isUP == true || isDOWN == true || isLEFT == true || isRIGHT == true) {
			if (isUP == true) {
				if (y > 450)
					y -= 1;
				else 
					y = 450;
			}
			if (isDOWN == true) {
				if (y >= background.getIconHeight() - planeImage.getHeight(this))
					y = background.getIconHeight() - planeImage.getHeight(this);
				else 
					y += 1;
			}
			if (isLEFT == true) {
				if (x <= 0) 
					x = 0;
				else 
					x -= 2;
			}
			if (isRIGHT == true) {
				if (x < background.getIconWidth() - planeImage.getWidth(this))
					x += 2;
				else
					x = background.getIconWidth() - planeImage.getWidth(this);
			}
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SwingUtilities.invokeLater(() -> repaint());
		}
	}

	class MyRunnable implements Runnable {
		private volatile boolean isRunning = true;
		
		@Override
		public void run() {
			while(isRunning) {
				try {
					Move();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class MyKeyListner implements KeyListener {
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
