package restart;

public interface Movable {
	public static final int x = 0;
	public static final int y = 0;
	public static final int speed = 0;
	
//	public void move();
	
	public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();
    
//	public void move(GameController myCon);
}