
import java.awt.geom.Rectangle2D;
import java.util.Random;




/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5. Created Jan 30, 2017.
 */
public class MetalGear implements Temporal, Relocatable {
	private GameWorld bigWorld;
	private Double x;
	private Double y;
	private java.awt.geom.Rectangle2D.Double metalGear;
	private boolean movement;
	private GameComponent world; 
	private boolean ispaused=true;
	private Timer timer;
	private int HP = 3;
	private int MP = 300;
	private BossWeapon nuke;
	private int fireAllowed;
	private long deathTimer;
	int v;


	public MetalGear(Double x, Double y,GameComponent world,GameWorld bigWorld) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.metalGear = new Rectangle2D.Double(this.x, this.y, 100, 100);
		this.timer = new Timer();
		this.timer.start();
		this.bigWorld = bigWorld;
		this.fireAllowed = 3;
		this.deathTimer = 0;
		
	}
	
	public long getDeathTimer() {
		return this.deathTimer;
	}

	public void setDeathTimer(long deathTimer) {
		this.deathTimer = deathTimer;
	}

	public java.awt.geom.Rectangle2D.Double getMetalGear() {
		return this.metalGear;
	}

	public void setMetalGear(java.awt.geom.Rectangle2D.Double metalGear) {
		this.metalGear = metalGear;
	}
	
	private void fire() {
		if (this.bigWorld.getNukes().size() < this.fireAllowed) {
		BossWeapon nuke = new BossWeapon(this.metalGear.getX(), this.metalGear.getY(),this.world);
		this.bigWorld.addNuke(nuke);
		nuke.randomMotion();
		}
	}
	
	public void randomMotion() {
		if (this.timer.getElapsedTime() > 500) {
		this.timer.reset();          
		Random r = new Random();
		int xLow = -10;
		int xHigh = 10;
		this.v = r.nextInt(xHigh-xLow) + xLow;

//		this.fire();
		this.timer.start();
		}
		
	}
	public void moveTo() {
		
		if(this.ispaused==true){
			this.setMetalGear(new Rectangle2D.Double(this.metalGear.getX()+v, this.metalGear.getY()+v, 100, 100));
			if(this.metalGear.getX()> 800 || this.metalGear.getX()< 50){
				this.setMetalGear(new Rectangle2D.Double(this.metalGear.getX()-v, this.metalGear.getY(), 100, 100));
			}
			if(this.metalGear.getY()<50 || this.metalGear.getY()>380){
				this.setMetalGear(new Rectangle2D.Double(this.metalGear.getX(), this.metalGear.getY()-v, 100, 100));
			}
			this.randomMotion();
			
			if ( this.MP == 30) {
				this.fire();
				this.MP = this.MP - 30;
			} else if (this.MP == 300) {
				this.fire();
				this.MP = this.MP - 30;
			} else{
				this.MP ++;
			}

		}
	}


	public GameComponent getWorld() {
		return this.world;
	}
	public GameWorld getBigWorld(){
		return this.bigWorld;
	}

	public void setWorld(GameComponent world) {
		this.world = world;
	}
	
	public int getHp(){
		return this.HP;
	}
	public void beHarmed() {
		this.HP = this.HP-1;
	}
	
	public Double getX() {
		return this.x;
	}

	public Double getY() {
		return this.y;
	}
	@Override
	public void timePassed() {
		// moveTo();

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.ispaused=isPaused;
	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub.
		return true;
	}

	@Override
	public void moveTo(Double x, Double y) {
		// TODO Auto-generated method stub.
		
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param currentTimeMillis
	 */

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */





}