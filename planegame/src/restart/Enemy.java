package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Enemy {
	private int enemyX, enemyY, speed = 1;
	private int hp;
	private Player player;
	
	
	public Enemy(int hp, int speed) {
		enemyX = 0;
		enemyY = 0;
		this.hp = hp;
		this.speed = speed;
	}
	
	public void drawEnemy(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, enemyX, enemyY, IO);
	}
	
	public void move(int movePattern) {
		switch(movePattern) {
		case 1:
			pattern_1();
			break;
		case 2:
			pattern_2();
			break;
		case 3:
			pattern_3();
			break;
		}
	}
	
	public void pattern_1() {
		enemyX += speed;
		enemyY += 2 * speed;
	}
	public void pattern_2() {
		enemyX -= speed;
		enemyY += 2 * speed;
	}
	public void pattern_3() {
		enemyY += 2 * speed;
	}
	
	public void setHP(int damage) {
		this.hp -= damage;
	}

	public int getHP() {
		return hp;
	}
	public int getX() {
		return enemyX;
	}
	public int getY() {
		return enemyY;
	}
	public Rectangle getBounds(Image image) {
		return new Rectangle(enemyX, enemyY, image.getWidth(null), image.getHeight(null));
	}
}