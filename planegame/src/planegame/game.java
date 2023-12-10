package planegame;



import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.tools.javac.Main;


public class game extends JFrame implements KeyListener {
	private JButton bt_1 = new JButton();
	private JButton bt_2 = new JButton();
	private int x = 200, y = 720;
	
	private ImageIcon background = new ImageIcon("../planegame/image/myBackGound.jpg");
	
	private Image backImage = Toolkit.getDefaultToolkit().getImage("../planegame/image/myBackGound.jpg");
	private Image planeImage = Toolkit.getDefaultToolkit().getImage("../planegame/image/myPlane.png");
	
	private JLabel myPlaneLabel = new JLabel();
	
	private Thread thread_plane = new Thread(new MyRunnable());
	
	public game(String title) {
		super(title);
		setBounds(300, 150, background.getIconWidth(), background.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		addKeyListener(null);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(planeImage, x, y, this);

	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	// implements keyListner
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			if (x < 0)
				x = 0;
				x -= 5;
				repaint();
				break;
			
				
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	
	
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

