package planegame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
	private Image bullet_Level_1 = new ImageIcon("image/bullet.png").getImage();
	private int bulletX, bulletY, damage;
	private int speed = 7;
	
	public Bullet(int x, int y, int damage) {
		this.bulletX = x;
		this.bulletY = y;
		this.damage = damage;
	}
	
	public void move() {
		bulletY -= speed;
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
