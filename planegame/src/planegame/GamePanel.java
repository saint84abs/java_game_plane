package planegame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel {
	// double buffering용 변수
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	// 객체 위치 전용 변수
	private int planeX = 200, planeY = 720;
	private int EnemyPlaneX = -12, EnemyPlaneY = 10;
	private int bulletX, bulletY, damage = 5;
	private int movingCase = 1;
	// 배경 및 플레이어 캐릭터 이미지 변수
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	// 적 객체용 이미지 변수
	private Enemy EnemyPlane_BOSS = new Enemy(EnemyPlaneX, EnemyPlaneY, 2, "image/EnemyPlane_BOSS.png", 30, 5);
	private Enemy EnemyPlane_Normal = new Enemy(EnemyPlaneX, EnemyPlaneY, 1, "image/EnemyPlane_Normal.png", 20, 0);
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
	
	private Controller MyCon = new Controller();
	private Player player = new Player(MyCon);
	
	public GamePanel() {
		initGamePanel();
	}
	
	private void initGamePanel() {
		setFocusable(true);
		addKeyListener(MyCon);
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
	    DrawBullet(g);
	    DrawEnemy(g);
	    player.DrawPlane(g2d, this);
	    g.drawImage(backBuffer, 0, 0, this);
	}

//	public void DrawPlane(Graphics2D g2d, ImageObserver IO) {
//		g2d.drawImage(planeImage, planeX, planeY, IO);
//	}
	
	private void DrawBullet(Graphics g) {
		for (Bullet bullet : bullets) {
			if (bullet.getY() > 0) {
				g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
			}
		}
	}
	private void DrawEnemy(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
        	Enemy enemy = enemies.get(i);
        	g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        	enemy.move(movingCase);
        	if (enemy.isDead()) {
        		enemies.remove(i);
        		i--;
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
			gamePanel.addKeyListener(MyCon);
			gamePanel.setFocusable(true);
		}
		
		@Override
		public void run() {
		    try {
		        while (inGame) {
		            if (fireDelay <= 0) {
		                bullets.add(new Bullet(planeX, planeY, damage));
		                fireDelay = 50;
		            }
		            else 
		                fireDelay -= 2;

		            if (enemies.isEmpty()) {
		            	enemies.add(new Enemy(EnemyPlaneX, EnemyPlaneY, 1, "image/EnemyPlane_Normal.png", 20, 0));
		            }
		            Iterator<Enemy> itEnemy = enemies.iterator();

		            
		            Iterator<Bullet> itBullet = bullets.iterator();
		            while (itBullet.hasNext()) {
		                Bullet bullet = itBullet.next();

		                if (bullet.getY() > 0) {
		                    bullet.move();
		                    itEnemy = enemies.iterator();
		                    while (itEnemy.hasNext()) {
		                        Enemy enemy = itEnemy.next();
		                        if (isPixelPerfectCollision(toBufferedImage(enemy.getImage()), enemy.getX(), enemy.getY(), toBufferedImage(bullet.getImage()), bullet.getX(), bullet.getY())) {
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
		            player.move();
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}

	}
	
	public boolean isCollision(Enemy enemy, Bullet bullet) {
		return enemy.getBounds().intersects(bullet.getBounds());
	}
	
	public boolean isPixelPerfectCollision(BufferedImage image1, int x1, int y1, BufferedImage image2, int x2, int y2) {
	    // 이미지의 너비와 높이
	    int width1 = image1.getWidth(null);
	    int height1 = image1.getHeight(null);
	    int width2 = image2.getWidth(null);
	    int height2 = image2.getHeight(null);

	    // 이미지의 픽셀 단위로 충돌 검출
	    for (int i = 0; i < width1; i++) {
	        for (int j = 0; j < height1; j++) {
	            // 첫 번째 이미지의 픽셀이 투명한지 확인
	            int pixel1 = image1.getRGB(i, j);
	            if ((pixel1 >> 24) == 0x00) {
	                continue; // 투명한 픽셀은 무시
	            }

	            // 두 번째 이미지의 해당 위치에 픽셀이 있는지 확인
	            int i2 = i + x1 - x2;
	            int j2 = j + y1 - y2;
	            if (i2 < 0 || i2 >= width2 || j2 < 0 || j2 >= height2) {
	                continue; // 첫 번째 이미지의 픽셀이 두 번째 이미지의 범위를 벗어나는 경우 무시
	            }

	            // 두 번째 이미지의 픽셀이 투명한지 확인
	            int pixel2 = image2.getRGB(i2, j2);
	            if ((pixel2 >> 24) != 0x00) {
	                return true; // 두 이미지의 픽셀이 모두 투명하지 않으면 충돌 발생
	            }
	        }
	    }
	    // 충돌이 없음
	    return false;
	}

	public void Move() {
		if (MyCon.getIsLeft()) {
			if (planeX <= 0) 
				planeX = 0;
			else 
				planeX -= 2;
		}
		if (MyCon.getIsRight()) {
			if (planeX < background.getIconWidth() - planeImage.getWidth(this))
				planeX += 2;
			else
				planeX = background.getIconWidth() - planeImage.getWidth(this);
		}
		if (MyCon.getIsUp()) {
			if (planeY > 450)
				planeY -= 1;
			else 
				planeY = 450;
		}
		if (MyCon.getIsDown()) {
			if (planeY >= background.getIconHeight() - planeImage.getHeight(this))
				planeY = background.getIconHeight() - planeImage.getHeight(this);
			else 
				planeY += 1;
		}
	}

//	class MyKeyListner implements KeyListener {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			switch (e.getKeyCode()) {
//			case KeyEvent.VK_S:
//				if (inGame == true)
//					inGame = true;
//				else 
//					inGame = false;
//			}
//		}
//		@Override
//		public void keyPressed(KeyEvent e) {
//			switch (e.getKeyCode()) {
//			case KeyEvent.VK_LEFT:
//				isLEFT = true;
//				break;
//			case KeyEvent.VK_RIGHT:
//				isRIGHT = true;
//				break;
//			case KeyEvent.VK_UP:
//				isUP = true;
//				break;
//			case KeyEvent.VK_DOWN:
//				isDOWN = true;
//				break;
//			case KeyEvent.VK_SPACE:
//				isSPACE = true;
//				break;
//			}
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//			switch (e.getKeyCode()) {
//			case KeyEvent.VK_LEFT:
//				isLEFT = false;
//				break;
//			case KeyEvent.VK_RIGHT:
//				isRIGHT = false;
//				break;
//			case KeyEvent.VK_UP:
//				isUP = false;
//				break;
//			case KeyEvent.VK_DOWN:
//				isDOWN = false;
//				break;
//			case KeyEvent.VK_SPACE:
//				isSPACE = false;
//				break;
//			}
//		}
//	}
	
	public BufferedImage toBufferedImage(Image img) {
	    if (img instanceof BufferedImage) {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	
	public ImageIcon getBackImageIcon() {
		return background;
		
	}
}

/*
 * 
 * */
