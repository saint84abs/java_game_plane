package planegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Player implements Movable, ImageObserver {
	// 객체 위치 전용 변수
	private int planeX = 200, planeY = 700;
	private Controller con = new Controller();
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	
	public Player() {
		
	}
	
	public Player(Controller con) {
		this.con = con;
	}

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // ImageObserver 인터페이스를 구현하는 코드
        return false;
    }

	public void drawPlane(Graphics2D g2d, ImageObserver IO) {
		g2d.drawImage(planeImage, planeX, planeY, IO);
	}
	
	@Override
	public void move() {
		System.out.println("move method called");
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
	
	
	public int getX() {
		return planeX;
	}
	public int getY() {
		return planeY;
	}
	public Image getImage() {
		return planeImage;
	}
	
}
