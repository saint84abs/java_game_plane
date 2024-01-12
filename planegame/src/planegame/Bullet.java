package planegame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
	private Image bullet_Level_1 = new ImageIcon("image/bullet.png").getImage();
	private int bulletX, bulletY, damage;
	
	public Bullet() {
		
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
