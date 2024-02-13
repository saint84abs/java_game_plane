package restart;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class GameView extends JFrame implements ActionListener {
	private GameModel Model;
	private GameView View;
	private GameController Controller;
	
	private Player player;
	
	private boolean[] keyStates = new boolean[256];
	
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	
    private JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g;
            g2d.drawImage(Model.getBackGroundImage(), 0, 0, this);
            player.drawPlane(Model.getPlayerImage(), g2d, this);
            g.drawImage(backBuffer, 0, 0, this);
        }
    };
    
    private Timer timer;
    private int FPS = 75;
    private boolean inGame = true;
	
	public GameView(String title) {
		super(title);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		player = new Player();
		Model = new GameModel(player);
		Controller = new GameController(player, Model, this);
		
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				keyStates[e.getKeyCode()] = true;
			}
			public void keyReleased(KeyEvent e) {
				keyStates[e.getKeyCode()] = false;
			}
		});
		
		// 백버퍼에 아무것도 그리고있지 않음//
		backBuffer = new BufferedImage
				(Model.getBackGroundIcon().getIconWidth(),
				Model.getBackGroundIcon().getIconHeight(), 
				BufferedImage.TYPE_INT_ARGB);
		g2d = backBuffer.createGraphics();

		initvariables(FPS);
		add(panel);
		setBounds(300, 100,
				Model.getBackGroundIcon().getIconWidth(),
				Model.getBackGroundIcon().getIconHeight());
	}

	private void initvariables(int fps) {
		timer = new Timer(1000 / fps, this);
		timer.start();
	}
	
	public boolean[] getKeyStates() {
		return keyStates;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.handleKeyInput(keyStates);
		this.repaint();		
	}
}
