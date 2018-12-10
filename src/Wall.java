import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5. Created Jan 30, 2017.
 */
public class Wall implements Temporal, Relocatable {
	Double wall;
	private int xCoord;
	private int yCoord;

	public Wall(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
		this.wall = new Rectangle2D.Double(this.xCoord, this.yCoord, 50, 50);
	}

	public Double getWall() {
		return this.wall;
	}

	public void setWall(Double wall) {
		this.wall = wall;
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

	@Override
	public void moveTo(java.lang.Double x, java.lang.Double y) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void timePassed() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub.
		return false;
	}

}
