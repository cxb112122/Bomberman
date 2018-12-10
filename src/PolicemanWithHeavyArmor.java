
/**
 * TODO Put here a description of what this class does.
 *
 * @author Lingxiao. Created Feb 6, 2017.
 */
public class PolicemanWithHeavyArmor extends Policeman {
	
	public java.awt.geom.Rectangle2D.Double getPolicemanWithHeavyArmor() {
		return this.PolicemanWithHeavyArmor;
	}

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param m
	 * @param x
	 * @param y
	 * @param world
	 */
	private java.awt.geom.Rectangle2D.Double PolicemanWithHeavyArmor;
	private long deathTimer;

	public PolicemanWithHeavyArmor(boolean m, Double x, Double y, GameComponent world) {
		super(m, x, y, world);
		super.speed = 3;
		super.lives = 2;
		this.deathTimer = 0;
		
		// TODO Auto-generated constructor stub.
	}

	/**
	 * Returns the value of the field called 'deathTimer'.
	 * 
	 * @return Returns the deathTimer.
	 */
	public long getDeathTimer() {
		return this.deathTimer;
	}

	/**
	 * Sets the field called 'deathTimer' to the given value.
	 * 
	 * @param l
	 *            The deathTimer to set.
	 */
	public void setDeathTimer(long l) {
		this.deathTimer = l;
	}

	/**
	 * Sets the field called 'policemanWithHeavyArmor' to the given value.
	 * 
	 * @param policemanWithHeavyArmor
	 *            The policemanWithHeavyArmor to set.
	 */
	private void setPolicemanWithHeavyArmor(java.awt.geom.Rectangle2D.Double policemanWithHeavyArmor) {
		this.PolicemanWithHeavyArmor = policemanWithHeavyArmor;
	}

}
