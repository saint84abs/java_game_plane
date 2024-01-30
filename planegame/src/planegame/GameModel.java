package planegame;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
	private Player player = new Player();
	private Enemy enemy = new Enemy();
	private Bullet bullet = new Bullet();
	
	private Image playerImage = player.getImage();
	private Image enemyImage = enemy.getImage();
	private Image bulletImage = bullet.getImage();
	
	private List<Player> players = new ArrayList<>();
	private List<Enemy> enemies = new ArrayList<>();
	private List<Bullet> bullets = new ArrayList<>();
	
	public GameModel() {
		players.add(new Player());
		enemies.add(new Enemy());
		bullets.add(new Bullet());
	}
	
	public void addBullet(Bullet bt) {
		bullets.add(bt);
	}
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

    public List<Player> getPlayers() {
        return players;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
    public List<Bullet> getBullets() {
        return bullets;
    }
}
