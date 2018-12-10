import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5.
 *         Created Jan 30, 2017.
 */
public class PowerUp {
	Double location;
	private double xCoord;
	private double yCoord;
	private String skill;

	public PowerUp(double x, double y, String string) {
		this.xCoord = x;
		this.yCoord = y;
		this.location = new Rectangle2D.Double(this.xCoord, this.yCoord, 50, 50);
		this.skill = string;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Double getLocation() {
		return this.location;
	}

	public void setLocation(Double wall) {
		this.location = wall;
	}

	public double getxCoord() {
		return this.xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return this.yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

}
