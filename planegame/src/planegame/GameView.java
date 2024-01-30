package planegame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;

public class GameView extends JPanel {
    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private BufferedImage backBuffer;
    private Graphics2D g2d;
    private Image backImage;

    public GameView(Player player, List<Enemy> enemies, List<Bullet> bullets, BufferedImage backBuffer, Graphics2D g2d, Image backImage) {
        this.player = player;
        this.enemies = enemies;
        this.bullets = bullets;
        this.backBuffer = backBuffer;
        this.g2d = g2d;
        this.backImage = backImage;
        
        setFocusable(true);
        setVisible(true);
        
        this.setBounds(0, 0, backBuffer.getWidth(), backBuffer.getHeight());
    }

    public GameView() {

	}

	@Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d.drawImage(backImage, 0, 0, this);
        drawBullets(g);
        drawEnemies(g);
        player.drawPlane(g2d, this);
        g.drawImage(backBuffer, 0, 0, this);
    }

    private void drawBullets(Graphics g) {
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
    
    public void Fire(int fireDelay) {
        if (fireDelay <= 0) {
            bullets.add(new Bullet(player.getX(), player.getY(), 5));
            fireDelay = 50;
        }
        else 
            fireDelay -= 2;
    }
}
