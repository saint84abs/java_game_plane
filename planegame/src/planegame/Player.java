package planegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Player implements Movable {
	// 객체 위치 전용 변수
	private int planeX, planeY;
	private Controller con = new Controller();
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();

	
	public Player() {
		planeX = 200;
		planeY = 700;
	}
	
	public void DrawPlane(Graphics2D g, ImageObserver IO) {
		g.drawImage(planeImage, planeX, planeY, IO);
	}
	
	@Override
	public void move() {
		if (con.getIsLeft()) {
			System.out.println("left");
			if (planeX <= 0) 
				planeX = 0;
			else 
				planeX -= 2;
		}
		if (con.getIsRight()) {
			if (planeX < background.getIconWidth() - planeImage.getWidth((ImageObserver) this))
				planeX += 2;
			else
				planeX = background.getIconWidth() - planeImage.getWidth((ImageObserver) this);
		}
		if (con.getIsUp()) {
			if (planeY > 450)
				planeY -= 1;
			else 
				planeY = 450;
		}
		if (con.getIsDown()) {
			if (planeY >= background.getIconHeight() - planeImage.getHeight((ImageObserver) this))
				planeY = background.getIconHeight() - planeImage.getHeight((ImageObserver) this);
			else 
				planeY += 1;
		}
	}
}
