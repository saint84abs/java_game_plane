package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Enemy {
	private int enemyX, enemyY, speed = 3;
	private Player player;
	
	
	public Enemy() {
		
	}
	
	public void drawEnemy(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, enemyX, enemyY, IO);
	}
	
	
}
