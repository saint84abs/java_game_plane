package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Bullet {
	private int bulletX, bulletY;
	private Player player;
	private GameController Controller;
	
	public Bullet(Player p) {
		this.player = p;
		bulletX = player.getX();
		bulletY = player.getY();
	}
	
	public void drawBullet(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, bulletX, bulletY, IO);
	}
	
	
}
