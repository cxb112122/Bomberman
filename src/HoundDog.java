import java.awt.geom.Rectangle2D;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5. Created Jan 30, 2017.
 */
public class HoundDog implements Temporal, Relocatable {
	
	private Double x;
	private Double y;
	private int xspeed = 10;
	private int yspeed = 0;
	private java.awt.geom.Rectangle2D.Double houndDog;
	private GameComponent world; 
	private int count = 0;
	private boolean movement=true;

	public HoundDog(Double x, Double y, GameComponent world) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.houndDog = new Rectangle2D.Double(this.x, this.y, 50, 50);
	}
	
	public java.awt.geom.Rectangle2D.Double getHoundDog() {
		return this.houndDog;
	}

	public void setHoundDog(java.awt.geom.Rectangle2D.Double houndDog) {
		this.houndDog = houndDog;
	}

	public GameComponent getWorld() {
		return this.world;
	}

	public void setWorld(GameComponent world) {
		this.world = world;
	}

	public void moveTo() {
		if(this.movement==true){
		this.setHoundDog(new Rectangle2D.Double(this.houndDog.getX() + this.xspeed, this.houndDog.getY()+yspeed, 50, 50));
		for (Fence fence : this.world.level.getFences()){
		for (Wall wall : this.world.level.getWalls()) {
//			this.setHoundDog(new Rectangle2D.Double(this.houndDog.getX() + this.speed, this.houndDog.getY(), 50, 50));
			if(this.houndDog.intersects(wall.wall) && count==0) {
				this.xspeed =0;
				this.yspeed = -10;
				this.setHoundDog(new Rectangle2D.Double(this.houndDog.getX()-10, this.houndDog.getY(), 50, 50));
				count++;
			}
			if(this.houndDog.intersects(wall.wall) && count%4==1) {
				this.xspeed = -10;
				this.yspeed=0;
				this.setHoundDog(new Rectangle2D.Double(this.houndDog.getX(), this.houndDog.getY()+10, 50, 50));
				count++;
			}
			if(this.houndDog.intersects(wall.wall) && count%4==2) {
				this.xspeed = 0;
				this.yspeed=10;
				this.setHoundDog(new Rectangle2D.Double(this.houndDog.getX()+10, this.houndDog.getY(), 50, 50));
				count++;
			}
			if(this.houndDog.intersects(wall.wall) && count%4==3 || this.houndDog.intersects(fence.fence) && count%4==3) {
				this.xspeed = 10;
				this.yspeed=0;
				this.setHoundDog(new Rectangle2D.Double(this.houndDog.getX(), this.houndDog.getY()-10, 50, 50));
				count=0;
			}
			
		}
	}
	}
	}
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub.
		
	}
	
	@Override
	public void setIsPaused(boolean isPaused) {
		this.movement=isPaused;
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
}