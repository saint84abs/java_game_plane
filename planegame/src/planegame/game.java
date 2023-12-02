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
	
	private ImageIcon background = new ImageIcon("../planegame/image/myBackGound.jpg");
	private ImageIcon myPlane = new ImageIcon("../planegame/image/myPlane.png");
	
	private JLabel backgroundLabel = new JLabel();
	private JLabel myPlaneLabel = new JLabel();
	
	game(String title) {
		super(title);
		setSize(600, 900);
		setResizable(false);
		setLayout(null);
		
		backgroundLabel.setIcon(background);
		myPlaneLabel.setIcon(myPlane);
		
		JPanel plane = new JPanel();
		plane.add(myPlaneLabel);

		plane.setBounds(100, 100, myPlaneLabel.getWidth(), myPlaneLabel.getHeight());
		backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		
		add("background", backgroundLabel);
		add("myPlane", plane);
		
		//pack();
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

