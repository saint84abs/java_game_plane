package restart;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GamePanel extends JFrame {
	private GameModel Model;
	private GameView View;
//	private GameLoop Loop;
	private GameController Controller;
	
	private Player player;
	
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	
	public GamePanel(String title) {
//		super(title);
//		setResizable(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setVisible(true);
//		//Controller 초기화 필요
//		player = new Player();
//		Model = new GameModel(player);
//		Controller = new GameController(player, Model);
//		
//		setFocusable(true);
//		addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent e) {
//				System.out.println("Pressed key!");
//				Controller.handleKeyInput(e);
//			}
//		});
//		
//		backBuffer = new BufferedImage
//			(Model.getBackGroundIcon().getIconWidth(),
//			Model.getBackGroundIcon().getIconHeight(), 
//			BufferedImage.TYPE_INT_ARGB);
//		g2d = backBuffer.createGraphics();
//		
//		View = new GameView(player, Model, Controller);
//
//		Loop = new GameLoop(player, Model, Controller, this);
////		Loop.run();
//		Thread thread = new Thread(Loop);
//		thread.start();
//		
//		add(View);
//		setBounds(300, 100,
//			Model.getBackGroundIcon().getIconWidth(),
//			Model.getBackGroundIcon().getIconHeight());
	}
}
