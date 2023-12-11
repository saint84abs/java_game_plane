package planegame;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.tools.javac.Main;

public class game extends JFrame {
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
		addKeyListener(new MykeyListner());
		// 스레드 시작시 keylistner에서 thread로 true, false 로 값 전달
		// 실질적인 이동은 thread에서
		// https://dreamchallenger.blogspot.com/2011/08/keylistener-thread.html
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(planeImage, x, y, this);
	}
	
	public static void main(String args[]) {
		game g = new game("1944");
	}

	class MyRunnable implements Runnable {
		private volatile boolean isRunning = true;
		
		@Override
		public void run() {
			try {
				while(isRunning) {
					repaint();
					Thread.sleep(1);									
				}
			} catch (Exception e) {				
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	class MykeyListner implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			switch (keyCode) {
			case KeyEvent.VK_A:
				System.out.println("A key is pressed!");
				if (x < 0)
					x = 0;
				x -= 3;
				repaint();
				break;
			case KeyEvent.VK_D:
				System.out.println("D key is pressed!");
				if (x > background.getIconWidth() - planeImage.getWidth(null)) 
					x = background.getIconWidth() - planeImage.getWidth(null);
				x += 3;
				repaint();
				break;
			case KeyEvent.VK_W:
				System.out.println("W key is pressed!");
				if (y < 550)
					y = 550;
				y -= 3;
				repaint();
				break;
			case KeyEvent.VK_S:
				System.out.println("S key is pressed!");
				if (y > background.getIconHeight() - planeImage.getHeight(null))
					y = background.getIconHeight() - planeImage.getHeight(null);
				y += 3;
				repaint();
				break;
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {}
	}
}
