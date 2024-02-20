package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Boss extends Enemy {
	private int bossX, bossY;
	private int hp;
	private int damage;
	
	
	private Random random = new Random();
	private int pattern;
	
	public Boss(int hp, int pattern) {
		super(hp, pattern);
		
	}
	
	public void drawBoss(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, bossX, bossY, IO);
	}	
	
	public void attack(int pattern) {
		this.pattern = pattern;
		
		switch(pattern) {
		case 1:
			
		}
	}
	
	
	public void pattern_1() {
		
	}
	public void pattern_2() {
		
	}
	public void pattern_3() {
		
	}
	
	public void setHP(int damage) {
		this.hp -= damage;
	}
}
