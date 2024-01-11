package planegame;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferDouble;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.tools.javac.Main;

public class game extends JFrame {	
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");


	public game(String title) {
		super(title);
		//setBounds(300, 100, background.getIconWidth(), background.getIconHeight());
		setResizable(false);
		setIgnoreRepaint(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		GamePanel gamePanel = new GamePanel();
		gamePanel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		add(gamePanel);
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
