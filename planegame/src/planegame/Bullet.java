package planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Bullet {
	private Image bullet_Level_1 = new ImageIcon("image/bullet.png").getImage();
	private int bulletX, bulletY, damage;
	private int speed = 8;
	private int FireDelay = 50;
	
	public Bullet(int x, int y, int damage) {
		this.bulletX = x;
		this.bulletY = y;
		this.damage = damage;
	}

	public void move() {
		bulletY -= speed;
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
