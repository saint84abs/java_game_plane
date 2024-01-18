package planegame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	// double buffering용 변수
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	// 화면에 출력되는 이미지용 변수
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	private Image EnemyPlane_BOSS = new ImageIcon("image/EnemyPlane_BOSS.png").getImage();
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	// 키 이벤트용 변수
	private List<Bullet> bullets = new ArrayList<>();
	private List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
	// 객체 위치 전용 변수
	private int planeX = 200, planeY = 720;
	private int EnemyPlaneX = 50, EnemyPlaneY = 100;
	private int bulletX, bulletY, damage;
	private boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;
	// 스레드용 변수
	private boolean inGame = true;
	private Timer timer;
	private int FPS = 75;
	private int fireDelay = 100;
	
	public GamePanel() {
		initGamePanel();
	}
	
	private void initGamePanel() {
		setFocusable(true);
		addKeyListener(new MyKeyListner());
		setVisible(true);
		
		JPanel gamePanel = new JPanel();
		GameLoop gameLoop = new GameLoop(this, 1000 / FPS);
		Thread loopThread = new Thread(gameLoop);
		
		gamePanel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		add(gamePanel);
		loopThread.start();		
		
		backBuffer = new BufferedImage(background.getIconWidth(), background.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = backBuffer.createGraphics();
		g2d.drawImage(backImage, 0, 0, this);
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d.drawImage(backImage, 0, 0, this);
		DrawPlane(g);
		DrawEnemyBossPlane(g);
		DrawBullet(g);
		g.drawImage(backBuffer, 0, 0, this);
	}
	
	private void DrawPlane(Graphics g) {
		g2d.drawImage(planeImage, planeX, planeY, this);
	}
	
	private void DrawEnemyBossPlane(Graphics g) {
		g2d.drawImage(EnemyPlane_BOSS, EnemyPlaneX, EnemyPlaneY, this);
	}
	
	private void DrawBullet(Graphics g) {
		for (Bullet bullet : bullets) {
			if (bullet.getY() > 0) {
				g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
			}
		}
	}
	
	public class GameLoop implements Runnable, ActionListener {
		private JPanel gamePanel;
		private int delay;
		KeyEvent e;
		private void initVariables(int fps) {
			timer = new Timer(1000 / fps, this);
			timer.start();
		}
		
		@Override 
		public void actionPerformed(ActionEvent e) {
			gamePanel.repaint();
		}
		
		public GameLoop(JPanel gamePanel, int delay) {
			this.gamePanel = gamePanel;
			this.delay = delay;
			initVariables(FPS);
			gamePanel.addKeyListener(new MyKeyListner());
			gamePanel.setFocusable(true);
		}
		
		@Override
		public void run() {
			try {
				while (inGame) {
					if (fireDelay <= 0) {
						bullets.add(new Bullet(planeX, planeY, damage));
						fireDelay = 100;
					}
					else 
						fireDelay -= 2;
					
					for (Bullet bullet : bullets) {
						if (bullet.getY() > 0) {
							bullet.move();
						}
						else {
							bulletsToRemove.add(bullet);
						}
					}
					bullets.removeAll(bulletsToRemove);
					gamePanel.repaint();
					Thread.sleep(delay);
					Move(e);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void Move(KeyEvent e) {
		if (isLEFT) {
			if (planeX <= 0) 
				planeX = 0;
			else 
				planeX -= 2;
		}
		if (isRIGHT) {
			if (planeX < background.getIconWidth() - planeImage.getWidth(this))
				planeX += 2;
			else
				planeX = background.getIconWidth() - planeImage.getWidth(this);
		}
		if (isUP) {
			if (planeY > 450)
				planeY -= 1;
			else 
				planeY = 450;
		}
		if (isDOWN) {
			if (planeY >= background.getIconHeight() - planeImage.getHeight(this))
				planeY = background.getIconHeight() - planeImage.getHeight(this);
			else 
				planeY += 1;
		}
	}

	class MyKeyListner implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_S:
				if (inGame == true)
					inGame = true;
				else 
					inGame = false;
			}
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
}

/*
 * @Override
		public void run() {
//			try {
//				while (inGame) {
//					Move();
//					
//					if (fireDelay <= 0) {
//						bullets.add(new Bullet(planeX, planeY, damage));
//						fireDelay = 100;
//					}
//					else 
//						fireDelay -= 2;
//					
//					for (Bullet bullet : bullets) {
//						if (bullet.getY() > 0) {
//							bullet.move();
//						}
//						else {
//							bulletsToRemove.add(bullet);
//						}
//					}
//					bullets.removeAll(bulletsToRemove);
//					gamePanel.repaint();
//					Thread.sleep(delay);
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
		}
	}
 * 
 * */
