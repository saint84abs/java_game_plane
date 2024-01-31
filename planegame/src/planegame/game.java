package planegame;

import java.awt.Image;

import javax.swing.*;

public class game extends JFrame {	
	private static ImageIcon background = new ImageIcon("image/myBackGround.jpg");

//	public game(String title) {
//		super(title);
//		GamePanel gamePanel = new GamePanel();
//		setResizable(false);
//		setIgnoreRepaint(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);
//		
//		add(gamePanel);
//		setBounds(300, 100, background.getIconWidth(), background.getIconHeight());
//	}
	
	public static void main(String args[]) {
        GamePanel gamePanel = new GamePanel();

        // Set up the game window
        JFrame frame = new JFrame("Game Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(null);
        
        frame.setBounds(300, 100, background.getIconWidth(), background.getIconHeight());
        // Start the game loop
        gamePanel.startGame();
//		game g = new game("1944");
	}
}


/* 참고 자료
 * https://binghedev.tistory.com/56
 * http://cris.joongbu.ac.kr/course/java/api/java/awt/Graphics.html
 * 
 */
