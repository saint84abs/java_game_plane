package planegame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameLoop implements Runnable, ActionListener {
	// 스레드용 변수
	private boolean inGame = true;
	private Timer timer;
	private int FPS = 75;
	private int fireDelay = 100;
	private int EnemyPlaneX = -12, EnemyPlaneY = 10;
	private int damage = 5;
	
	GameView gameView = new GameView();
	
	private Controller MyCon = new Controller();
	private Player player = new Player(MyCon);
	private Bullet bullet = new Bullet();
	private Enemy enemy = new Enemy();
	
	private List<Enemy> enemies = new ArrayList<>();
	private List<Bullet> bullets = new ArrayList<>();
	
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
//	            if (fireDelay <= 0) {
//	                bullets.add(new Bullet(player.getX(), player.getY(), damage));
//	                fireDelay = 50;
//	            }
//	            else 
//	                fireDelay -= 2;
	        	gameView.Fire(fireDelay);
	        	
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
}