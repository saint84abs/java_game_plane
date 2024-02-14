package restart;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;

public class GameController {
    private GameView View;
    private GameModel Model;
    
    private Player player;
    
	private MyPropertyListener myPl;
	
    private boolean[] keyStates = new boolean[256];
    private int delay = 0;
    
    private ArrayList<Bullet> bullets;

    public GameController(Player player, GameModel Model, GameView View) {
    	this.player = player;
    	this.Model = Model;
    	this.View = View;
    	this.Model.addPropertyChangeListener(myPl);
    	
    	bullets = new ArrayList<>();
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
        if (keyStates[KeyEvent.VK_E]) {
        	Fire();
        	
        }
	}
	
	public void addBullet(Player p) {
		bullets.add(new Bullet(p));
	}
	
	public void drawBullet(Image image, Graphics2D g2d, ImageObserver IO) {
		for (Bullet bullet : bullets) {
			bullet.drawBullet(image, g2d, IO);
		}
	}
	
	public void updateBullets() {
        Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()) {
			Bullet bullet = it.next();
			if (bullet.isOutOfScreen()) {
				it.remove();
			}
			bullet.move();
		}
	}
	
	public void Fire() {
		if (delay <= 0) {
			addBullet(player);
			delay = 50;
		}
		else 
			delay -= 2;
	}
}
