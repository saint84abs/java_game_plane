package planegame;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferDouble;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.tools.javac.Main;

public class game extends JFrame {
	private JButton bt_1 = new JButton();
	private JButton bt_2 = new JButton();
	
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	
	private Image backImage = Toolkit.getDefaultToolkit().getImage("image/myBackGround.jpg");
	private Image planeImage = Toolkit.getDefaultToolkit().getImage("image/myPlane.png");
	
	private JLabel myPlaneLabel = new JLabel();
	
	private Thread thread_plane = new Thread(new MyRunnable());
	
	private int x = 200, y = 720;
	private boolean isUP, isDOWN, isLEFT, isRIGHT;
	
	//double buffering용 변수
	private Image img;
	private Graphics img_g;
	private BufferedImage backBuffer;
	
	
	
	
	public game(String title) {
		super(title);
		setBounds(300, 150, background.getIconWidth(), background.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		addKeyListener(new MykeyListner());
		Thread myth = new Thread(new MyRunnable());
		myth.start();
		
		BufferedImage backbuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = (Graphics2D) backBuffer.getGraphics();
		// 그래픽 작업 수행
		g2d.drawImage(planeImage, x, y, this);
		
//		try {
//			// 이미지 파일 읽어오기
//			BufferedImage MyPlaneImage = ImageIO.read(game.class.getResourceAsStream("image/myPlane.png"));
//			
//			// 그래픽 작업 수행
//			g2d.drawImage(MyPlaneImage, 0, 0, this);
//		} catch (Exception e) {
//			System.out.println("there is no image!");
//			e.printStackTrace();
//		} finally {
//			g2d.dispose();
//		}
//        // 그래픽 작업이 완료된 후에 이미지를 화면에 표시
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice gd = ge.getDefaultScreenDevice();
//        GraphicsConfiguration gc = gd.getDefaultConfiguration();
//        Frame frame = new Frame(gc);
//        frame.add(new Canvas() {
//            @Override
//            public void paint(Graphics g) {
//                g.drawImage(image, x, y, null);
//            }
//        });
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
		
		//g.drawImage(planeImage, x, y, planeImage.getWidth(this), planeImage.getHeight(this), this);
		g.drawImage(backBuffer, x, y, this);
	}
	
	public static void main(String args[]) {
		game g = new game("1944");
		
	}
	
	public void Move() {
		// 비행기가 계속 깜빡거리는데, 더블 버퍼링을 통해 해결 가능?
		while(isUP == true || isDOWN == true || isLEFT == true || isRIGHT == true) {
			if (isUP == true) {
				if (y > 450)
					y -= 1;
				else 
					y = 450;
			}
			if (isDOWN == true) {
				if (y >= background.getIconHeight() - planeImage.getHeight(this))
					y = background.getIconHeight() - planeImage.getHeight(this);
				else 
					y += 1;
			}
			if (isLEFT == true) {
				if (x <= 0) 
					x = 0;
				else 
					x -= 2;
			}
			if (isRIGHT == true) {
				if (x < background.getIconWidth() - planeImage.getWidth(this))
					x += 2;
				else
					x = background.getIconWidth() - planeImage.getWidth(this);
			}
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				repaint();				
			}
		}
	}

	class MyRunnable implements Runnable {
		private volatile boolean isRunning = true;
		
		@Override
		public void run() {
			while(isRunning) {
				try {
					Move();	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class MykeyListner implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isLEFT = true;
				break;
			case KeyEvent.VK_RIGHT:
				isRIGHT = true;
				break;
			case KeyEvent.VK_UP:
				isUP = true;
				break;
			case KeyEvent.VK_DOWN:
				isDOWN = true;
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				isLEFT = false;
				break;
			case KeyEvent.VK_RIGHT:
				isRIGHT = false;
				break;
			case KeyEvent.VK_UP:
				isUP = false;
				break;
			case KeyEvent.VK_DOWN:
				isDOWN = false;
				break;
			}
		}
	}
}


/* 참고 자료
 * https://binghedev.tistory.com/56
 * http://cris.joongbu.ac.kr/course/java/api/java/awt/Graphics.html
 * 
 */
