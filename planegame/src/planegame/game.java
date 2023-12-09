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
	private int x = 200, y = 720;
	
	private ImageIcon background = new ImageIcon("../planegame/image/myBackGound.jpg");
	private ImageIcon myPlane = new ImageIcon("../planegame/image/myPlane.png");
	
	private JLabel backgroundLabel = new JLabel();
	private JLabel myPlaneLabel = new JLabel();
	
	private Thread thread_plane = new Thread(new MyRunnable());
	
	public game(String title) {
		super(title);
		setSize(520, 900);
		setResizable(false);
		setLayout(null);
		
		backgroundLabel.setIcon(background);
		setContentPane(backgroundLabel);
		
		myPlaneLabel.setIcon(myPlane);
		
		JPanel plane = new JPanel() {
			public void paintComponent(Graphics g) {
				paintPlane(g);
			}
		};
		
		//thread_plane.start();
		plane.setBounds(x, y, myPlane.getIconWidth(), myPlane.getIconHeight());
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
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP :
						System.out.println("pressed W!");
						y++;
						break;
					}
					
			}
		});
		add("myPlane", plane);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void paintPlane(Graphics g) {
		g.drawImage(myPlane.getImage(), 0, 0, myPlane.getIconWidth(), myPlane.getIconHeight(), null);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	
	public static void main(String args[]) {
		game g = new game("1944");
	}

	class MyRunnable implements Runnable {
		private volatile boolean isRunning = true;
		
		@Override
		public void run() {
			try {
				repaint();
				Thread.sleep(15);				
			} catch (Exception e) {}
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	
}


