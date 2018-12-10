
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5.
 *         Created Feb 6, 2017.
 */
public class Helicopter {

	private Double x = 100.;
	private Double y = 100.;
	private int velocityX ;
	private int velocityY ;
	java.awt.geom.Rectangle2D.Double helicopter;
	private boolean movement;
	private GameComponent world; 
	private double width=860;
	private double height=580;
	
	
	public Helicopter(boolean m, Double x, Double y, GameComponent world) {
		this.movement = true;
		this.world = world;
		this.x = x;
		this.y = y;
		this.helicopter = new Rectangle2D.Double(this.x, this.y, 50, 50);
		
		Random random=new Random();
		
		boolean vx = random.nextBoolean();
		boolean vy = random.nextBoolean();
		this.velocityX=1+random.nextInt(3);
		this.velocityY=1+random.nextInt(3);

		if (vx == true) {
			velocityX = velocityX * (-3);
		}
		if (vy == true) {
			velocityY = velocityY * (-3);
		}
	}
	public void moveTo() {
		if(this.movement==true){
		if (this.helicopter.getCenterX() < 50 & velocityX < 0
				|| this.helicopter.getCenterX() >= this.width & velocityX > 0) {
			velocityX = -1 * velocityX;
		}
		
		
		if (this.helicopter.getCenterY() < 50 && velocityY < 0
				|| this.helicopter.getCenterY()> this.height && velocityY > 0) {
			velocityY = -1 * velocityY;
		}
		this.setHelicopter(
				new Rectangle2D.Double(this.helicopter.getX() + this.velocityX, this.helicopter.getY()+this.velocityY, 50, 50));
	}
	}
	
	public java.awt.geom.Rectangle2D.Double getHelicopter() {
		return this.helicopter;
	}

	public void setHelicopter(java.awt.geom.Rectangle2D.Double helicopter) {
		this.helicopter = helicopter;
	}
	public GameComponent getWorld() {
		return this.world;
	}

	public void setWorld(GameComponent world) {
		this.world = world;
	}
	public void setIsPaused(boolean isPaused) {
		this.movement=isPaused;
	}
}

