import java.awt.geom.Rectangle2D;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Lingxiao.
 *         Created Feb 15, 2017.
 */
public class WinScreen {

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	int x;
	int y;
	private GameComponent world;
	private java.awt.geom.Rectangle2D.Double policeman;
	public WinScreen(Double x, Double y, GameComponent world) {
		this.world = world;
		this.policeman = new Rectangle2D.Double(this.x, this.y, 50, 50);
		
	}

}
