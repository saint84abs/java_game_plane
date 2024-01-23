package planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Bullet {
	private Image bullet_Level_1 = new ImageIcon("image/bullet.png").getImage();
	private int bulletX, bulletY, damage = 2;
	private int speed = 8;
	
	public Bullet(int x, int y, int damage) {
		this.bulletX = x;
		this.bulletY = y;
		this.damage = damage;
	}

	public void move() {
		bulletY -= speed;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(bulletX, bulletY, bullet_Level_1.getWidth(null), bullet_Level_1.getHeight(null));
	}
	
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(getImage(), getX(), getY(), observer);
	}
	
	public int getX() {
		return bulletX;
	}
	public int getY() {
		return bulletY;
	}
	public int getDamage() {
		return damage;
	}
	public Image getImage() {
		return bullet_Level_1;
	}
	
}
