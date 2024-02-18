package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;

public class GameController {
	// MVC패턴 변수
    private GameView View;
    private GameModel Model;
    private MyPropertyListener myPl;
    
    // 플레이어블 객체 변수
    private Player player;
    
	// 총알 객체 변수
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private int myDelay = 0;
    private int enemyDelay = 0;
    
    // 적 객체 변수
    private ArrayList<Enemy> enemies;
    private Enemy enemy;
    private Enemy Boss;
    private int hp = 10, speed = 1;
    private Random random = new Random();
    private int pattern;
    
    // 게임 내부 정보 변수
    private int score = 0;
    private int difficulty = 1;


    public GameController(Player player, GameModel Model, GameView View) {
    	this.player = player;
    	this.Model = Model;
    	this.View = View;
    	this.Model.addPropertyChangeListener(myPl);
    	
    	bullets = new ArrayList<>();
    	enemyBullets = new ArrayList<>();
    	enemies = new ArrayList<>();
    }

	public void handleKeyInput(boolean keyStates[]) {
        if (keyStates[KeyEvent.VK_UP]) 
        	player.moveUp();
        if (keyStates[KeyEvent.VK_DOWN]) 
            player.moveDown();
        if (keyStates[KeyEvent.VK_LEFT])
        	player.moveLeft();
        if (keyStates[KeyEvent.VK_RIGHT])
        	player.moveRight();
        if (keyStates[KeyEvent.VK_E]) 
        	Fire();
	}
	
	public void addBullet(Player p) {
		bullets.add(new Bullet(p));
	}
	public void addBullet(Enemy e) {
		enemyBullets.add(new Bullet(e));
	}
	
	public void drawBullet(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Bullet bullet : bullets) {
			bullet.drawBullet(image, g2d, IO);
		}
		for (Bullet enemyBullet : enemyBullets) {
			enemyBullet.drawBullet(image, g2d, IO);
		}
	}
	
	public void updateBullets() {
        Iterator<Bullet> it = bullets.iterator();
        Iterator<Bullet> eit = enemyBullets.iterator();
		while (it.hasNext()) {
			Bullet bullet = it.next();
			if (bullet.isOutOfScreen()) {
				it.remove();
			}
			bullet.move(10);
		}
		while (eit.hasNext()) {
			Bullet enemyBullet = eit.next();
			if (enemyBullet.isOutOfScreen()) {
				eit.remove();
			}
			enemyBullet.move(-5);
		}
	}
	
	public void Fire() {
		if (myDelay <= 0) {
			addBullet(player);
			myDelay = 50;
		}
		else 
			myDelay -= 2;
	}
	public void Fire(Enemy enemy) {
		if (enemyDelay <= 0) {
			addBullet(enemy);
			enemyDelay = 300;
		}
		else
			enemyDelay -= 2;
	}
	
	public void addEnemy() {
		enemies.add(new Enemy(hp * difficulty, speed));
		pattern = random.nextInt(3) + 1;
	}
	
	public void drawEnemy(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Enemy enemy : enemies) {
			enemy.drawEnemy(image, g2d, IO);
		}
	}
	
	public void updateEnemy() {		
		if (enemies.size() <= 3 && score < 30 * difficulty) {
			addEnemy();		
		}
		Iterator<Enemy> it = enemies.iterator();
		while (it.hasNext()) {
			Enemy enemies = it.next();
			if (enemies.getX() >= 400 || enemies.getX() < 0 || enemies.getY() > 800 || enemies.getY() < 0) 
				it.remove();
			enemies.move(pattern);
			Fire(enemies);
	        Iterator<Bullet> bulletIterator = bullets.iterator();
	        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
	        while (bulletIterator.hasNext()) {
	            Bullet bullet = bulletIterator.next();
	            if (checkCollision(bullet, enemies)) {
	            	enemies.setHP(bullet.getDamage());
	                bulletIterator.remove();
	                if (enemies.getHP() <= 0) {
	                	it.remove();
	                	score += enemies.dropScore(difficulty);
	                	System.out.println(score);
	                }
	                break;
	            }
	        }
	        while (enemyBulletIterator.hasNext()) {
	            Bullet enemyBullet = enemyBulletIterator.next();
	        	if (checkCollision(enemyBullet, player)) {
	        		player.setHP(enemyBullet.getDamage() * difficulty);
	        		enemyBulletIterator.remove();
	        		if (player.getHP() <= 0) {
	        			System.out.println("game over!");
	        		}
	        	}
	        	
	        }
	        
	        if (score == 30 * difficulty) 
	        	break;
		}
//		bossEvent();
//		difficulty += 1;
	}
	
	public void bossEvent() {
		int hp_boss = 100 * difficulty;
		
	}
	
	public boolean checkCollision(Bullet bullet, Enemy enemy) {
		return bullet.getBounds(Model.getBulletImage()).intersects(enemy.getBounds(Model.getEnemyNormalImage()));
	}
	public boolean checkCollision(Bullet bullet, Player player) {
		return bullet.getBounds(Model.getBulletImage()).intersects(player.getBounds(Model.getEnemyNormalImage()));
	}
	
	public int getScore() {
		return score;
	}
//	public JLabel getScore() {
//		
//	}
}
