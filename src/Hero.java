import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Hero implements Relocatable, Temporal {
	private GameWorld world;
	private double locationX = 50;
	private double locationY = 50;

	private int lives;
	private PowerUp power;
	private int speed = 50;
	private int side = 25;
	private Rectangle2D.Double hero;
	private ArrayList<Bomb> bombs;

	private int bombsAllowed;
	private boolean movement;

	private String powerUp;
	private GameComponent component;

	public Hero(GameWorld world, GameComponent component) {
		this.hero = new Rectangle2D.Double(this.getX(), this.getY(), 50, 50);
		this.world = world;
		this.bombs = new ArrayList<Bomb>();
		this.lives = 3;
		this.bombsAllowed = 1;
		this.powerUp = "";
		this.movement=true;
		this.component = component;
	}

	public int getBombsAllowed() {
		return this.bombsAllowed;
	}

	public String getPowerUp() {
		return this.powerUp;
	}

	public void setPowerUp(String powerUp) {
		this.powerUp = powerUp;
	}

	public void setBombsAllowed(int bombsAllowed) {
		this.bombsAllowed = bombsAllowed;
	}

	public void fire() {
		if (this.powerUp != null) {
			if (this.getPowerUp().equals("DoubleBomb"))
				this.bombsAllowed = 2;
		}
		if (this.bombs.size() < this.bombsAllowed) {
			Bomb bomb = new Bomb(this.getX(), this.getY(), this, this.component);
			this.bombs.add(bomb);
			this.world.addBomb(bomb);

		}
	}

	public ArrayList<Bomb> getBombs() {
		return this.bombs;
	}

	public Rectangle2D.Double getHero() {
		return this.hero;
	}

	public void setHero(Rectangle2D.Double hero) {
		this.hero = hero;
	}

	public Double getX() {
		return locationX;
	}

	public Double getY() {
		return locationY;
	}

	@Override
	public void moveTo(Double x, Double y) {
		this.locationX = x;
		this.locationY = y;
		this.hero = new Rectangle2D.Double(x, y, 50, 50);

	}

	@Override
	public void timePassed() {

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.movement=isPaused;
	}

	@Override
	public boolean getIsPaused() {
		return true;
	}

	// update positions
	public void updatePositionup() {
		if(this.movement==true){
		if (this.getY() - this.speed < 0)
			;
		else
			this.moveTo(this.getX(), this.getY() - this.speed);
	}
	}

	public void updatePositiondown() {
		if(this.movement==true){
		if (this.getY() + this.speed > 800 - this.side - this.speed)
			;
		else
			this.moveTo(this.getX(), this.getY() + this.speed);

	}
	}

	public void updatePositionleft() {
		if(this.movement==true){
		if (this.getX() - this.speed < 0)
			;
		else
			this.moveTo(this.getX() - this.speed, this.getY());

	}
	}

	public void updatePositionright() {
		if(this.movement==true){
		if (this.getX() + this.speed > 900 - this.side)
			;
		else
			this.moveTo(this.getX() + this.speed, this.getY());

	}
	}

	// hero's movement
	public void moveHero(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.component.getAudio().playSound("foot");
			this.updatePositionup();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.component.getAudio().playSound("foot");
			this.updatePositiondown();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.component.getAudio().playSound("foot");
			this.updatePositionright();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.component.getAudio().playSound("foot");
			this.updatePositionleft();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.fire();
			this.component.getAudio().playSound("fire");

		}

	}

	public void die() {
		// TODO Auto-generated method stub.
		this.locationX = 50;
		this.locationY = 50;
		this.hero.setRect(this.locationX, this.locationY, 50, 50);
		this.component.bombsToRemove.addAll(this.world.getBombs());
		this.lives -= 1;

	}

	public double getLocationY() {
		return this.locationY;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}

	public int getLives() {
		return this.lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	private void gameOver() {
		// TODO Auto-generated method stub.

	}
}
