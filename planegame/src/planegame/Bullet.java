package planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Bullet {
	private Image bullet_Level_1 = new ImageIcon("image/bullet.png").getImage();
	private int bulletX, bulletY, damage;
	private int speed = 7;
	
	public Bullet(int x, int y, int damage) {
		this.bulletX = x;
		this.bulletY = y;
		this.damage = damage;
//		bulletTime bt = new bulletTime();
//		Thread bulletThread = new Thread(bt);
//		bulletThread.start();
	}
	// 총알이 실시간으로 움직일 수 있도록 스레드로 구성
	public void move() {
		bulletY -= speed;
	}
	
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(getImage(), getX(), getY(), observer);
	}
	
	public class bulletTime implements Runnable {
		
		@Override
		public void run() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
