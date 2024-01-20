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

	public Enemy(int x, int y, int speed, String ImagePath) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = new ImageIcon(ImagePath).getImage();
	}
	
	public void move() {
		// 자동적으로 일정 패턴으로 움직여야함
		if (x <= 200)
			x += speed;
		else 
			x -= speed;
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
}
