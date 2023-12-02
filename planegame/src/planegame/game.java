package planegame;



import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.tools.javac.Main;


public class game extends JFrame implements ActionListener {
	private JButton bt_1 = new JButton();
	private JButton bt_2 = new JButton();
	
	private int x = 0, y = 0;
	
	private ImageIcon background = new ImageIcon
			(Main.class.getResource("../planegame/planegame.image/myPlane.png"));
	private JLabel imgLabel = new JLabel();
	
	game(String title) {
		super(title);
		setSize(600, 900);
		setResizable(false);
		
		imgLabel.setIcon(background);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w') {
			
		}
	}
	
	public static void main(String args[]) {
		game g = new game("1944");
	}
}

