import java.awt.Color;
import java.util.ArrayList;


public class GameWorld {
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	private ArrayList<BossWeapon> nukes;
	private int width;
	private int height;
	private Color color;
	private ArrayList<WinScreen> winScreen = new ArrayList<WinScreen>();

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param width
	 * @param height
	 * @param color
	 */
	public GameWorld(int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.bombs = new ArrayList<Bomb>();
		this.nukes = new ArrayList<BossWeapon>();

	}

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public GameWorld() {
		this.nukes = new ArrayList<BossWeapon>();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param bomb
	 */
	public void addBomb(Bomb bomb) {
		this.bombs.add(bomb);
	}
	
	public void addNuke(BossWeapon nuke) {
		this.nukes.add(nuke);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public ArrayList<Bomb> getBombs() {
		return this.bombs;
	}
	
	public ArrayList<BossWeapon> getNukes() {
		return this.nukes;
	}


}
