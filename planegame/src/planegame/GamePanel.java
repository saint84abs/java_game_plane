package planegame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel {
    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private BufferedImage backBuffer;
    private Graphics2D g2d;
    private GameModel gameModel = new GameModel();
    private GameView gameView;
    private GameLoop gameLoop;
    private GameController gameController;
    private Image backImage = gameModel.getBackGround();
    
    public GamePanel() {
        // Create the game's objects
        player = new Player();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        // Create the backBuffer and Graphics2D object
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        g2d = backBuffer.createGraphics();

        // Create the model, view, and controller
        gameView = new GameView(player, enemies, bullets, backBuffer, backImage);
        gameLoop = new GameLoop(gameView, 1000 / 60);  // Assuming 60 FPS
        gameController = new GameController(gameView, gameLoop);
        
        // Set up the game window
        setFocusable(true);
        setVisible(true);
        this.setBounds(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
        this.add(gameView);
    }
    
    public void startGame() {
        gameController.start();
    }
}


/*
 * public class GamePanel extends JPanel {
	// double buffering용 변수
	private BufferedImage backBuffer;
	private Graphics2D g2d;
	// 객체 위치 전용 변수
	private int planeX = 200, planeY = 720;
	private int EnemyPlaneX = -12, EnemyPlaneY = 10;
	private int bulletX, bulletY, damage = 5;
	private int movingCase = 1;
	// 배경 및 플레이어 캐릭터 이미지 변수
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
	private Image planeImage = new ImageIcon("image/myPlane.png").getImage();
	// 적 객체용 이미지 변수
	private Enemy EnemyPlane_BOSS = new Enemy(EnemyPlaneX, EnemyPlaneY, 2, "image/EnemyPlane_BOSS.png", 30, 5);
	private Enemy EnemyPlane_Normal = new Enemy(EnemyPlaneX, EnemyPlaneY, 1, "image/EnemyPlane_Normal.png", 20, 0);
	private ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	// 키 이벤트용 변수
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Bullet> bulletsToRemove = new ArrayList<Bullet>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Enemy> enemiesToRemove = new ArrayList<Enemy>();
	// 스레드용 변수
	private boolean inGame = true;
	private Timer timer;
	private int FPS = 75;
	private int fireDelay = 100;
	
	private Controller MyCon = new Controller();
	private Player player = new Player(MyCon);
	
	public GamePanel() {
		initGamePanel();
	}
	
	private void initGamePanel() {
		setFocusable(true);
		addKeyListener(MyCon);
		setVisible(true);
		
		JPanel gamePanel = new JPanel();
		GameLoop gameLoop = new GameLoop(this, 1000 / FPS);
		Thread loopThread = new Thread(gameLoop);
		
		enemies.add(EnemyPlane_BOSS);
		
		gamePanel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		add(gamePanel);
		loopThread.start();		
		
		backBuffer = new BufferedImage(background.getIconWidth(), background.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = backBuffer.createGraphics();
		g2d.drawImage(backImage, 0, 0, this);
	}


	public void Move() {
		if (MyCon.getIsLeft()) {
			if (planeX <= 0) 
				planeX = 0;
			else 
				planeX -= 2;
		}
		if (MyCon.getIsRight()) {
			if (planeX < background.getIconWidth() - planeImage.getWidth(this))
				planeX += 2;
			else
				planeX = background.getIconWidth() - planeImage.getWidth(this);
		}
		if (MyCon.getIsUp()) {
			if (planeY > 450)
				planeY -= 1;
			else 
				planeY = 450;
		}
		if (MyCon.getIsDown()) {
			if (planeY >= background.getIconHeight() - planeImage.getHeight(this))
				planeY = background.getIconHeight() - planeImage.getHeight(this);
			else 
				planeY += 1;
		}
	}

	public ImageIcon getBackImageIcon() {
		return background;
		
	}
}
 * */
