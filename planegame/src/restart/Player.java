package restart;

import java.awt.*;
import java.awt.image.ImageObserver;
import javax.swing.*;

import planegame.GameController;

public class Player {
	private int planeX = 200, planeY = 700;

	public Player() {
		
	}
	
	public Player(GameController con) {
		
	}
	
	public void drawPlane(Image image, Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(image, planeX, planeY, IO);
	}
	
	public int getX() {
		return planeX;
	}
	public int getY() {
		return planeY;
	}
}
