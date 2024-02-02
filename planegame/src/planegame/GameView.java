package planegame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameView extends JPanel {
    private Player player;
    private GameModel gameModel;
    private List<Enemy> enemies;
	private List<Bullet> bullets = new ArrayList<>();
    private BufferedImage backBuffer;
    private Graphics2D g2d;
	private Image backImage = new ImageIcon("image/myBackGround.jpg").getImage();
    private int fireDelay = 50;

    public GameView() {
    	player = new Player();
    	
    }
    
    public GameView(Player player, List<Enemy> enemies, List<Bullet> bullets) {
        this.player = player;
        this.enemies = enemies;
        this.bullets = bullets;
        this.backBuffer = backBuffer;
        this.gameModel = new GameModel();
        this.backImage = backImage;

//        setFocusable(true);
//        setVisible(true);

//        this.setBounds(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.drawImage(backImage, 0, 0, this);
//        drawBullets(g);
//        drawEnemies(g);
        player.drawPlane(g2d, this);
        g.drawImage(backBuffer, 0, 0, this);
    }

    private void drawBullets(Graphics g) {
    	if (bullets.isEmpty())
    		bullets.add(new Bullet());
        for (Bullet bullet : bullets) {
            if (bullet.getY() > 0) {
                g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemy enemy : enemies) {
            g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        }
    }

    public void Fire() {
        if (fireDelay <= 0) {
            bullets.add(new Bullet(player.getX(), player.getY(), 5));
            fireDelay = 50;
        }
        else 
            fireDelay -= 2;
    }
}
