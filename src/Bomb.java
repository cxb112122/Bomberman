import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * TODO Put here a description of what this class does.
 *
 * @author chenx5. Created Jan 31, 2017.
 */
public class Bomb {
	private int distance;
	private double locationX;
	private double locationY;
	private java.awt.geom.Ellipse2D.Double bomb;
	private int blowUp;
	private ArrayList<java.awt.geom.Rectangle2D.Double> blasts;
	private int blastTime;
	private int explodeType;
	private Hero hero;
	private GameComponent component;
	
	private char eleS;
	private char eleW;
	private char eleN;
	private char eleE;

	public Bomb(Double locationX, Double locationY, Hero hero, GameComponent component) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.hero = hero;
		this.blasts = new ArrayList<Rectangle2D.Double>();
		this.bomb = new Ellipse2D.Double(this.getX(), this.getY(), 50, 50);
		this.setBlowUp(0);
		this.setBlastTime(0);
		this.explodeType = 1;
		this.component = component;

	}

	public ArrayList<java.awt.geom.Rectangle2D.Double> getBlasts() {
		return this.blasts;
	}

	public void setBlasts(ArrayList<java.awt.geom.Rectangle2D.Double> blasts) {
		this.blasts = blasts;
	}

	public java.awt.geom.Ellipse2D.Double getBomb() {
		return this.bomb;
	}

	public Bomb() {

	}

	public double getX() {
		return locationX;
	}

	public double getY() {
		return locationY;
	}

	public void explode() {
		if (this.hero.getPowerUp() != null) {
			if (this.hero.getPowerUp().equals("FarBlast"))
				explode2();
		}
		if (this.explodeType == 1)
			explode1();
	}

	public int getExplodeType() {
		return this.explodeType;
	}

	public void setExplodeType(int explodeType) {
		this.explodeType = explodeType;
	}

	public void explode1() {
		Rectangle2D.Double blast1 = new Rectangle2D.Double(this.locationX, this.locationY, 50, 50);
		this.blasts.add(blast1);
		
		int hero_y = (int) (this.locationX / 50);
		int hero_x = (int) (this.locationY / 50);
		char[][] gamemap = this.component.level.getFile().getFile();
		this.eleS = gamemap[hero_x + 1][hero_y];
		this.eleN = gamemap[hero_x - 1][hero_y];
		this.eleE = gamemap[hero_x][hero_y + 1];
		this.eleW = gamemap[hero_x][hero_y - 1];
		if(this.eleE != 'W'){
			this.blasts.add(new Rectangle2D.Double(this.locationX + 50, this.locationY, 50, 50));
		}
		if(this.eleW != 'W'){
			this.blasts.add(new Rectangle2D.Double(this.locationX - 50, this.locationY, 50, 50));
		}
		if(this.eleS != 'W'){
			this.blasts.add(new Rectangle2D.Double(this.locationX, this.locationY + 50, 50, 50));
		}
		if(this.eleN != 'W'){
			this.blasts.add(new Rectangle2D.Double(this.locationX, this.locationY - 50, 50, 50));
		}
	}

	public void explode2() {
		explode1();
		if(this.eleE != 'W' && this.eleE != 'B'){
			this.blasts.add(new Rectangle2D.Double(this.locationX + 100, this.locationY, 50, 50));
		}
		if(this.eleW != 'W' && this.eleW != 'B'){
			this.blasts.add(new Rectangle2D.Double(this.locationX - 100, this.locationY, 50, 50));
		}
		if(this.eleS != 'W' && this.eleS != 'B'){
			this.blasts.add(new Rectangle2D.Double(this.locationX, this.locationY + 100, 50, 50));
		}
		if(this.eleN != 'W' && this.eleN != 'B'){
			this.blasts.add(new Rectangle2D.Double(this.locationX, this.locationY - 100, 50, 50));
		}
	}

	public void adddistance() {

	}

	public int getBlowUp() {
		return blowUp;
	}

	public void setBlowUp(int blowUp) {
		this.blowUp = blowUp;
	}

	public int getBlastTime() {
		return blastTime;
	}

	public void setBlastTime(int blowUp) {
		this.blastTime = blowUp;
	}

}
