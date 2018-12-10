import java.awt.geom.Rectangle2D;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5. Created Jan 30, 2017.
 */
public class Policeman implements Temporal, Relocatable {
	public java.awt.geom.Rectangle2D.Double getPoliceman() {
		return this.policeman;
	}

	public void setPoliceman(java.awt.geom.Rectangle2D.Double policeman) {
		this.policeman = policeman;
	}
	private Double x;
	private Double y;
	protected int speed = 5;
	private java.awt.geom.Rectangle2D.Double policeman;
	private boolean movement;
	private GameComponent world; 
	private boolean ispaused=true;
	protected int lives;

	public Policeman(boolean m, Double x, Double y, GameComponent world) {
		this.movement = m;
		this.world = world;
		this.x = x;
		this.y = y;
		this.policeman = new Rectangle2D.Double(this.x, this.y, 50, 50);
		this.lives = 1;
	}
	
	public void moveTo() {
		if(this.ispaused==true){// for pause and restart
		if (this.movement == true) {
			this.setPoliceman(new Rectangle2D.Double(this.policeman.getX() + this.speed, this.policeman.getY(), 50, 50));
			for (Wall wall : this.world.level.getWalls()) {
				if (this.policeman.intersects(wall.wall)) {
					this.speed = -this.speed;
					this.setPoliceman(
							new Rectangle2D.Double(this.policeman.getX() + this.speed, this.policeman.getY(), 50, 50));
				}
			}
			
			for (Fence fence : this.world.level.getFences()) {
				if (this.policeman.intersects(fence.fence)) {
					this.speed = -this.speed;
					this.setPoliceman(
							new Rectangle2D.Double(this.policeman.getX() + this.speed, this.policeman.getY(), 50, 50));
				}
			}
			
			
		} else {
			this.setPoliceman(new Rectangle2D.Double(this.policeman.getX(), this.policeman.getY()+this.speed, 50, 50));
			for (Wall wall : this.world.level.getWalls()) {
				if (this.policeman.intersects(wall.wall)) {
					this.speed = -this.speed;
					this.setPoliceman(
							new Rectangle2D.Double(this.policeman.getX(), this.policeman.getY()+this.speed, 50, 50));
				}
			}
			for (Fence fence : this.world.level.getFences()) {
				if (this.policeman.intersects(fence.fence)) {
					this.speed = -this.speed;
					this.setPoliceman(
							new Rectangle2D.Double(this.policeman.getX(), this.policeman.getY()+this.speed, 50, 50));
				}
			}
		}
		}

		}




	public GameComponent getWorld() {
		return this.world;
	}

	public void setWorld(GameComponent world) {
		this.world = world;
	}

	public void moveHere(Double x, Double y) {
		this.x = x;
		this.y = y;
		this.policeman = new Rectangle2D.Double(this.x, this.y, 50, 50);
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
	
	public boolean getMovement(){
		return this.movement;
	}

	/**
	 * Returns the value of the field called 'lives'.
	 * @return Returns the lives.
	 */
	public int getLives() {
		return this.lives;
	}

	/**
	 * Sets the field called 'lives' to the given value.
	 * @param lives The lives to set.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

}