package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Bullet {
	private int bulletX, bulletY, speed = 10, damage = 5;
	private Player player;
	private Enemy enemy;
	private GameController Controller;
	
	public Bullet(Player p) {
		this.player = p;
		bulletX = player.getX();
		bulletY = player.getY();
	}
	
	public Bullet(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public void drawBullet(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, bulletX, bulletY, IO);
	}

	public boolean isOutOfScreen() {
		return bulletY < 0;
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
	public Rectangle getBounds(Image image) {
		return new Rectangle(bulletX, bulletY, image.getWidth(null), image.getHeight(null));
	}
}
