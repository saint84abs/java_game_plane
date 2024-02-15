package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;

public class GameController {
	// MVC패턴 변수
    private GameView View;
    private GameModel Model;
    private MyPropertyListener myPl;
    
    // 플레이어블 객체 변수
    private Player player;
    
	// 총알 객체 변수
    private ArrayList<Bullet> bullets;
    private int delay = 0;
    
    // 적 객체 변수
    private ArrayList<Enemy> enemies;
    private int hp = 10, speed = 1;

    public GameController(Player player, GameModel Model, GameView View) {
    	this.player = player;
    	this.Model = Model;
    	this.View = View;
    	this.Model.addPropertyChangeListener(myPl);
    	
    	bullets = new ArrayList<>();
    	enemies = new ArrayList<>();
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
	
	public void drawBullet(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Bullet bullet : bullets) {
			bullet.drawBullet(image, g2d, IO);
		}
	}
	
	public void updateBullets() {
        Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()) {
			Bullet bullet = it.next();
			if (bullet.isOutOfScreen()) {
				it.remove();
			}
			
			bullet.move();
		}
	}
	
	public void Fire() {
		if (delay <= 0) {
			addBullet(player);
			delay = 50;
		}
		else 
			delay -= 2;
	}
	
	public void addEnemy() {
		enemies.add(new Enemy(hp, speed));
	}
	
	public void drawEnemy(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Enemy enemy : enemies) {
			enemy.drawEnemy(image, g2d, IO);
		}
	}
	
	public void updateEnemy() {
		int pattern = 1;
		if (enemies.isEmpty())
			enemies.add(new Enemy(hp, speed));
		Iterator<Enemy> it = enemies.iterator();
		while (it.hasNext()) {
			Enemy enemies = it.next();
			if (enemies.getHP() <= 0) 
				it.remove();
			if (enemies.getX() >= 400 || enemies.getX() < 0)
				it.remove();
			if (enemies.getY() > 800 || enemies.getY() < 0)
				it.remove();
			enemies.move(pattern);
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
