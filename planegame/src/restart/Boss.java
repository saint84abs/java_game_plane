package restart;

public class Boss {
	private int bossX, bossY;
	private int hp;
	private int speed;
	
	private int pattern;
	
	public Boss(int difficulty) {
		this.hp = 100 * difficulty;
		
	}
	
	public void attack(int pattern) {
		this.pattern = pattern;
		
		switch(pattern) {
		case 1:
			
		}
	}
	
	public void pattern_1() {
		
	}
	public void pattern_2() {
		
	}
	public void pattern_3() {
		
	}
}
