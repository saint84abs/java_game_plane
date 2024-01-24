package planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	// 적 객체의 이미지
	private Image image;
	// 적 객체의 위치
	private int x, y;
	// 적 객체의 속도 
	private int speed = 1;
	// 적 개체의 hp, sp
	private int HP, SP;
	
	public Enemy(int x, int y, int speed, String ImagePath, int HP, int SP) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = new ImageIcon(ImagePath).getImage();
		this.HP = HP;
		this.SP = SP;
	}
	
	public void move() {
		// 자동적으로 일정 패턴으로 움직여야함
		if (x <= 200)
			x += speed;
		else 
			x -= speed;
	}
	
	public void hit(Bullet bullet) {
		this.HP -= bullet.getDamage();
		if (this.HP == 0) {
			System.out.println("this object is done!");
		}
	}
	
	public boolean isDead() {
		return this.HP <= 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHP() {
		return HP;
	}
	public Image getImage() {
		return image;
	}
}
