package restart;

import java.awt.*;
import java.awt.image.ImageObserver;
import javax.swing.*;

public class Player implements Movable {
	private int planeX = 200, planeY = 700;
	private GameController Controller;
	
	public Player() {
		
	}
	
	public Player(GameController con) {
		this.Controller = con;
	}
	
	public void move(int x, int y) {
		planeX += x;
		planeY += y;
	}
	
	public void drawPlane(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, planeX, planeY, IO);
	}
	// Movable 오버라이딩, 비행기의 좌표 처리
	@Override
	public void moveUp() {
    	planeY -= 1;
    };
    @Override
    public void moveDown() {
    	planeY += 1;
    };
    @Override
    public void moveLeft() {
    	planeX -= 2;
    };
    @Override
    public void moveRight() {
    	planeX += 2;
    };
	
//	@Override
//	public void move() {
////		System.out.println("move method called, " + planeX + ", " + planeY);
//		if (con.getIsLeft()) {
//			System.out.println("left!!!" + planeX);
//			if (planeX <= 0) 
//				planeX = 0;
//			else 
//				planeX -= 2;
//		}
//		if (con.getIsRight()) {
//			if (planeX < background.getIconWidth() - planeImage.getWidth((ImageObserver) this))
//				planeX += 2;
//			else
//				planeX = background.getIconWidth() - planeImage.getWidth((ImageObserver) this);
//		}
//		if (con.getIsUp()) {
//			System.out.println("up!" + planeY);
//			if (planeY > 450)
//				planeY -= 1;
//			else 
//				planeY = 450;
//		}
//		if (con.getIsDown()) {
//			if (planeY >= background.getIconHeight() - planeImage.getHeight((ImageObserver) this))
//				planeY = background.getIconHeight() - planeImage.getHeight((ImageObserver) this);
//			else 
//				planeY += 1;
//		}
//	}
	
	public int getX() {
		return planeX;
	}
	public int getY() {
		return planeY;
	}
}
