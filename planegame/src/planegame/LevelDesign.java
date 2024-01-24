package planegame;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class LevelDesign {
	// 적의 위치, 이동 속도, 이미지 등
	private int x, y;
	private int speed;
	private Image image;
	// 랜덤을 위한 Random객체
	private static final Random rand = new Random();
	// 이미지를 위한 객체
	GamePanel gp = new GamePanel();
	public LevelDesign() {
		
	}
	
	public void EnemyIncounter(Enemy enemy, String ImagePath) {
		image = new ImageIcon(ImagePath).getImage();
		
		x = rand.nextInt(gp.getBackImageIcon().getIconWidth() - image.getWidth(null));
		y = 0;
		
		speed = rand.nextInt(3) + 1;
	}
}
