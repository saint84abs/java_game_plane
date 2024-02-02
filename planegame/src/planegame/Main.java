package planegame;

import java.awt.Image;

import javax.swing.*;

public class Main extends JFrame {	
	private static ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	
	public static void main(String args[]) {
		GamePanel g = new GamePanel("1944");
//		g.startgame();
	}
}


/* 참고 자료
 * https://binghedev.tistory.com/56
 * http://cris.joongbu.ac.kr/course/java/api/java/awt/Graphics.html
 * 
 */
