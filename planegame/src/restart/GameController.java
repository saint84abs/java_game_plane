package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;

public class GameController {
	// MVC패턴 변수
    private GameView View;
    private GameModel Model;
    private MyPropertyListener myPl;
    
    // 플레이어블 객체 변수
    private Player player;
    
	// 총알 객체 변수
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private int myDelay = 0;
    private int enemyDelay = 0;
    
    // 적 객체 변수
    private ArrayList<Enemy> enemies;
    private Enemy enemy;
    private Enemy Boss;
    private int hp = 10, speed = 1;
    private Random random = new Random();
    private int pattern;
    
    // 게임 내부 정보 변수
    private int score = 0;
    private int difficulty = 1;
    BufferedImage bulletImage, enemyImage, playerImage;

    public GameController(Player player, GameModel Model, GameView View) {
    	this.player = player;
    	this.Model = Model;
    	this.View = View;
    	this.Model.addPropertyChangeListener(myPl);
    	
    	bullets = new ArrayList<>();
    	enemyBullets = new ArrayList<>();
    	enemies = new ArrayList<>();
    	
    	bulletImage = toBufferedImage(Model.getBulletImage());
    	enemyImage = toBufferedImage(Model.getEnemyNormalImage());
    	playerImage = toBufferedImage(Model.getPlayerImage());
    }

	public void handleKeyInput(boolean keyStates[]) {
        if (keyStates[KeyEvent.VK_UP]) 
        	player.moveUp();
        if (keyStates[KeyEvent.VK_DOWN]) 
            player.moveDown();
        if (keyStates[KeyEvent.VK_LEFT])
        	player.moveLeft();
        if (keyStates[KeyEvent.VK_RIGHT])
        	player.moveRight();
        if (keyStates[KeyEvent.VK_E]) 
        	Fire();
	}
	
	public void addBullet(Player p) {
		bullets.add(new Bullet(p));
	}
	public void addBullet(Enemy e) {
		enemyBullets.add(new Bullet(e));
	}
	
	public void drawBullet(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Bullet bullet : bullets) {
			bullet.drawBullet(image, g2d, IO);
		}
		for (Bullet enemyBullet : enemyBullets) {
			enemyBullet.drawBullet(image, g2d, IO);
		}
	}
	
	public void updateBullets() {
        Iterator<Bullet> it = bullets.iterator();
        Iterator<Bullet> eit = enemyBullets.iterator();
		while (it.hasNext()) {
			Bullet bullet = it.next();
			if (bullet.isOutOfScreen()) {
				it.remove();
			}
			bullet.move(10);
		}
		while (eit.hasNext()) {
			Bullet enemyBullet = eit.next();
			if (enemyBullet.isOutOfScreen()) {
				eit.remove();
			}
			enemyBullet.move(-5);
		}
	}
	
	public void Fire() {
		if (myDelay <= 0) {
			addBullet(player);
			myDelay = 50;
		}
		else 
			myDelay -= 2;
	}
	public void Fire(Enemy enemy) {
		if (enemyDelay <= 0) {
			addBullet(enemy);
			enemyDelay = 300;
		}
		else
			enemyDelay -= 2;
	}
	
	public void addEnemy() {
		enemies.add(new Enemy(hp * difficulty, speed));
		pattern = random.nextInt(3) + 1;
	}
	
	public void drawEnemy(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Enemy enemy : enemies) {
			enemy.drawEnemy(image, g2d, IO);
		}
	}
	
	public void updateEnemy() {		
		if (enemies.size() <= 3 && score < 30 * difficulty) {
			addEnemy();		
		}
		Iterator<Enemy> it = enemies.iterator();
		while (it.hasNext()) {
			Enemy enemies = it.next();
			if (enemies.getX() >= 400 || enemies.getX() < 0 || enemies.getY() > 800 || enemies.getY() < 0) 
				it.remove();
//			enemies.move(pattern);
			Fire(enemies);
	        Iterator<Bullet> bulletIterator = bullets.iterator();
	        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
	        while (bulletIterator.hasNext()) {
	            Bullet bullet = bulletIterator.next();
	            if (isPixelPerfectCollision(bulletImage, bullet.getX(), bullet.getY(), enemyImage, enemies.getX(), enemies.getY())) {
	            	enemies.setHP(bullet.getDamage());
	                bulletIterator.remove();
	                if (enemies.getHP() <= 0) {
	                	it.remove();
	                	score += enemies.dropScore(difficulty);
	                	System.out.println(score);
	                }
	                break;
	            }
	        }
	        while (enemyBulletIterator.hasNext()) {
	            Bullet enemyBullet = enemyBulletIterator.next();
	        	if (isPixelPerfectCollision(bulletImage, enemyBullet.getX(), enemyBullet.getY(), playerImage, player.getX(), player.getY())) {
	        		player.setHP(enemyBullet.getDamage() * difficulty);
	        		enemyBulletIterator.remove();
	        		if (player.getHP() <= 0) {
	        			System.out.println("game over!");
	        		}
	        	}
	        	
	        }
	        
	        if (score == 30 * difficulty) 
	        	break;
		}
//		bossEvent();
//		difficulty += 1;
	}
	
	public void bossEvent() {
		int hp_boss = 100 * difficulty;
		
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
    public boolean isSolid(int argb) {
        return (argb >> 24) != 0;  // alpha 값이 0이 아니면 solid로 판정
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
	
	public int getScore() {
		return score;
	}
//	public JLabel getScore() {
//		
//	}
}
