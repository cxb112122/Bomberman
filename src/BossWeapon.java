import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5.
 *         Created Feb 6, 2017.
 */
public class BossWeapon {

	private Double x;
	private Double y;
	private int v=20;
	java.awt.geom.Rectangle2D.Double bossWeapon;
	private GameComponent world; 
	private int motion;
	private boolean ispaused=true;


	public BossWeapon(Double x, Double y,GameComponent world) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.bossWeapon = new Rectangle2D.Double(this.getX(), this.getY(), 30, 30);
	}
	
	public void randomMotion() {
		Random r = new Random();
		int Low = 0;
		int High = 4;
		this.motion = r.nextInt(High-Low) + Low;
	}
	public void moveTo() {
		if(this.ispaused==true){

		if(this.motion==0){
			this.setBossWeapon(new Rectangle2D.Double(this.bossWeapon.getX()+this.v, this.bossWeapon.getY(), 30, 30));
		}
		if(this.motion==1){
			this.setBossWeapon(new Rectangle2D.Double(this.bossWeapon.getX(), this.bossWeapon.getY()+this.v, 30, 30));
		}
		if(this.motion==2){
			this.setBossWeapon(new Rectangle2D.Double(this.bossWeapon.getX()-this.v, this.bossWeapon.getY(), 30, 30));
		}
		if(this.motion==3){
			this.setBossWeapon(new Rectangle2D.Double(this.bossWeapon.getX(), this.bossWeapon.getY()-this.v, 30, 30));
		}
		
		}
	}
	
	public java.awt.geom.Rectangle2D.Double getBossWeapon() {
		return this.bossWeapon;
	}

	public void setBossWeapon(java.awt.geom.Rectangle2D.Double bossWeapon) {
		this.bossWeapon = bossWeapon;
	}
	public GameComponent getWorld() {
		return this.world;
	}

	public void setWorld(GameComponent world) {
		this.world = world;
	}
	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

//	public void setIsPaused(boolean isPaused) {
//		this.movement=isPaused;
//	}
}


