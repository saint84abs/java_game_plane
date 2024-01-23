package planegame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	// double buffering용 변수
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	// 객체 위치 전용 변수
	private int planeX = 200, planeY = 720;
	private int EnemyPlaneX = -12, EnemyPlaneY = 10;
	private int bulletX, bulletY, damage = 1;
	private boolean isUP, isDOWN, isLEFT, isRIGHT, isSPACE;
	// 배경 및 플레이어 캐릭터 이미지 변수
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	// 적 객체용 이미지 변수
	private Enemy EnemyPlane_BOSS = new Enemy(EnemyPlaneX, EnemyPlaneY, 1, "image/EnemyPlane_BOSS.png", 300, 5);
	private Enemy EnemyPlane_Normal = new Enemy(EnemyPlaneX, EnemyPlaneY, 1, "image/EnemyPlane_Normal.png");
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	// 키 이벤트용 변수
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Enemy> enemiesToRemove = new ArrayList<Enemy>();
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
		
		enemies.add(EnemyPlane_BOSS);
		
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
		DrawBullet(g);
		g.drawImage(backBuffer, 0, 0, this);
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
	}
	
	private void DrawPlane(Graphics g) {
		g2d.drawImage(planeImage, planeX, planeY, this);
	}
	
	private void DrawBullet(Graphics g) {
		for (Bullet bullet : bullets) {
			if (bullet.getY() > 0) {
				g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
			}
		}
	}
	// 메인 화면 갱신 루프, 이동과 상호작용을 담당함
	public class GameLoop implements Runnable, ActionListener {
		private JPanel gamePanel;
		private int delay;
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

		            Iterator<Bullet> itBullet = bullets.iterator();
		            while (itBullet.hasNext()) {
		                Bullet bullet = itBullet.next();

		                if (bullet.getY() > 0) {
		                    bullet.move();
		                    Iterator<Enemy> itEnemy = enemies.iterator();
		                    while (itEnemy.hasNext()) {
		                        Enemy enemy = itEnemy.next();
		                        if (isCollision(enemy, bullet)) {
		                        	System.out.println(enemy.getHP());
		                            enemy.hit(bullet);
		                            if (enemy.isDead()) {
		                                itEnemy.remove();
		                            }
		                            itBullet.remove();
		                            break;
		                        }
		                    }
		                } else {
		                    itBullet.remove();
		                }
		            }
		            gamePanel.repaint();
		            Thread.sleep(delay);
		            Move();
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	}
	
	public boolean isCollision(Enemy enemy, Bullet bullet) {
		return enemy.getBounds().intersects(bullet.getBounds());
	}

	public void Move() {
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
 * 
 * */
