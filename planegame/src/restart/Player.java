package restart;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import javax.swing.*;

public class Player implements Movable {
	private int planeX = 200, planeY = 700, speed = 1;
	private GameController Controller;
	
	public Player() {
		
	}
	
	public Player(GameController con) {
		this.Controller = con;
	}
	
	public void drawPlane(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, planeX, planeY, IO);
	}
	
	// Movable 오버라이딩, 비행기의 좌표 처리
	@Override
	public void moveUp() {
		if (planeY > 450)
			planeY -= speed;
		else 
			planeY = 450;
    };
    @Override
    public void moveDown() {
		if (planeY >= 800)
			planeY = 800;
		else 
			planeY += speed;
    };
    @Override
    public void moveLeft() {
		if (planeX <= 0) 
			planeX = 0;
		else 
			planeX -= 2 * speed;
    };
    @Override
    public void moveRight() {
		if (planeX < 380)
			planeX += 2 * speed;
		else
			planeX = 380;
    };
	
	public int getX() {
		return planeX;
	}
	public int getY() {
		return planeY;
	}
}
