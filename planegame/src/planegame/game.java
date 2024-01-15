package planegame;

import javax.swing.*;

public class game extends JFrame {	
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");

	public game(String title) {
		super(title);
		GamePanel gamePanel = new GamePanel();
		setResizable(false);
		setIgnoreRepaint(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		add(gamePanel);
		setBounds(300, 100, background.getIconWidth(), background.getIconHeight());
	}
	
	public static void main(String args[]) {
		game g = new game("1944");
	}
}


/* 참고 자료
 * https://binghedev.tistory.com/56
 * http://cris.joongbu.ac.kr/course/java/api/java/awt/Graphics.html
 * 
 */
