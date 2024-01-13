package planegame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel {
	// double buffering용 변수
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	// 화면에 출력되는 이미지용 변수
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	private List<Bullet> bullets = new ArrayList<>();// 키 이벤트용 변수
	private int planeX = 200, planeY = 720;
	private int bulletX, bulletY, damage;
	private boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;
	// 스레드용 변수
	private Thread thread_plane = new Thread(new MyRunnable());
	
	public GamePanel() {
		setFocusable(true);
		addKeyListener(new MyKeyListner());
		setVisible(true);
		
		JPanel gamePanel = new JPanel();
		GameLoop gameLoop = new GameLoop(gamePanel, 1000 / 60);
		Thread loopThread = new Thread(gameLoop);
		
		gamePanel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		add(gamePanel);
		loopThread.start();
		thread_plane.start();
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 배경과 비행기 그리기
		g.drawImage(backImage, 0, 0, backImage.getWidth(this), backImage.getHeight(this), this);
		backBuffer = new BufferedImage(background.getIconWidth(), background.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = backBuffer.createGraphics();
		g2d.drawImage(backImage, 0, 0, this);
		g2d.drawImage(planeImage, planeX, planeY, this);
	    g.drawImage(backBuffer, 0, 0, this);
	    
	    // 발사되는 & 발사될 총알 그리기
	    // bullets는 객체를 담는 컬렉션을 참조
	    if (isSPACE == true) {
			bullets.add(new Bullet(planeX, planeY, damage));
		    for (Bullet bullet : bullets) {
		        bullet.move();
		        if (bullet.getY() < 0) {
		            bullets.remove(bullet);
		        } else {
		            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
		        }
		    }
	    }
	}
	
	public class GameLoop implements Runnable {
		private JPanel gamePanel;
		private int delay;
		
		public GameLoop(JPanel gamePanel, int delay) {
			this.gamePanel = gamePanel;
			this.delay = delay;
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					gamePanel.repaint();
					Thread.sleep(delay);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void Move() {
		while(isUP == true || isDOWN == true || isLEFT == true || isRIGHT == true) {
			if (isUP == true) {
				if (planeY > 450)
					planeY -= 1;
				else 
					planeY = 450;
			}
			if (isDOWN == true) {
				if (planeY >= background.getIconHeight() - planeImage.getHeight(this))
					planeY = background.getIconHeight() - planeImage.getHeight(this);
				else 
					planeY += 1;
			}
			if (isLEFT == true) {
				if (planeX <= 0) 
					planeX = 0;
				else 
					planeX -= 2;
			}
			if (isRIGHT == true) {
				if (planeX < background.getIconWidth() - planeImage.getWidth(this))
					planeX += 2;
				else
					planeX = background.getIconWidth() - planeImage.getWidth(this);
			}
			if (isSPACE == true) {
				if (bulletY < 0) {
					
				}
			}
			try {
				Thread.sleep(13);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SwingUtilities.invokeLater(() -> repaint());
		}
	}
	
	public void Fire() {
		while (isSPACE == true) {
			
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
			case KeyEvent.VK_SPACE:
				isSPACE = true;
				System.out.println("boom");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	        case KeyEvent.VK_SPACE:
	            isSPACE = false;
	            bullets.add(new Bullet(planeX, planeY, damage));
	            break;
	    }
		}
	}
}
