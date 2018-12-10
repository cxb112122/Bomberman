import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Fence {

	Double fence;
	private int xCoord;
	private int yCoord;
	private GameComponent world;

	public Fence(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
		this.fence = new Rectangle2D.Double(this.xCoord, this.yCoord, 50, 50);
	}

	public Double getfence() {
		return this.fence;
	}

	public void setfence(Double fence) {
		this.fence = fence;
	}

	public int getxCoord() {
		return this.xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return this.yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public GameComponent getWorld() {
		return this.world;
	}
	
	public void setWorld(GameComponent world) {
		this.world = world;
	}

}
