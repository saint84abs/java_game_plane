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
	
	private int x = 220, y = 220;
	
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
		//myPlaneLabel.setBackground(null);
		
		JPanel plane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(myPlane.getImage(), 0, 0, myPlane.getIconWidth(), myPlane.getIconHeight(), null);

			}
		};
		plane.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'w' ) {
					y++;
				}
				if (e.getKeyChar() == 's') {
					y--;
				}
				
			}
		});
		plane.setBounds(x, y, myPlane.getIconWidth(), myPlane.getIconHeight());
		backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		
		add("myPlane", plane);
		add("background", backgroundLabel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w') {
			y++;
		}
		if (e.getKeyChar() == 's') {
			y--;
		}
		if (e.getKeyChar() == 'd') {
			x++;
		}
		if (e.getKeyChar() == 'a') {
			x--;
		}
	}
	
	public static void main(String args[]) {
		game g = new game("1944");
	}
}

