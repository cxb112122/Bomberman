import java.util.ArrayList;

/**
 * Read file for each level
 *
 * @author Lingxiao. Created Feb 2, 2017.
 */
public class LevelRead {
	
	private ArrayList<Wall> walls;
	private int levelNumber;
	private ArrayList<Policeman> policemans;
	private ArrayList<Fence> fences;
	private ArrayList<Helicopter> helicopters;
	private ArrayList<HoundDog> dogs;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<MetalGear> metalGear;
	private ReadFile file;
	private ArrayList<PolicemanWithHeavyArmor> policemanWithHeavyArmor;
	private GameWorld world;
	private GameComponent Component;
	private ArrayList<WinScreen> winScreen;


	/**
	 * Constructor for LevelRead
	 *
	 * @param z
	 */
	public LevelRead(int z) {
		this.levelNumber = z;
		this.walls = new ArrayList<Wall>();
		this.policemans = new ArrayList<Policeman>();
		this.dogs = new ArrayList<HoundDog>();
		this.fences = new ArrayList<Fence>();
		this.powerUps = new ArrayList<PowerUp>();
		this.helicopters = new ArrayList<Helicopter>();
		this.metalGear = new ArrayList<MetalGear>();
		this.policemanWithHeavyArmor = new ArrayList<PolicemanWithHeavyArmor>();
		this.world = new GameWorld();
		this.winScreen = new ArrayList<WinScreen>();
//		this.Component = new GameComponent();
//		this.audio = new Audio();
		
		this.file = new ReadFile(this.levelNumber);
		int x = 0;
		int y = 0;
		for (int i = 0; i < file.file.length; i++) {
			for (int j = 0; j < file.file[0].length; j++) {
				// Add wall on the map
				if (file.file[i][j] == 'W') {
					Wall wall = new Wall(x, y);
					this.walls.add(wall);
				}
				// Add breakable wall on the map
				if (file.file[i][j] == 'B') {
					Fence fence = new Fence(x, y);
					this.fences.add(fence);
				}
				// Add policeman(with vertical movement) on the map
				if (file.file[i][j] == 'V') {
					Policeman policeman = new Policeman(false, (double) x, (double) y, null);
					this.policemans.add(policeman);
				}
				// Add policeman(with horizontal movement) on the map
				if (file.file[i][j] == 'H') {
					Policeman policeman = new Policeman(true, (double) x, (double) y, null);
					this.policemans.add(policeman);
				}
				// Add helicopter(with random movement) on the map
				if (file.file[i][j] == 'Z') {
					Helicopter helicopter = new Helicopter(true, (double) x, (double) y, null);
					this.helicopters.add(helicopter);
				}
				if (file.file[i][j] == 'P') {
					PowerUp powers = new PowerUp((double) x, (double) y, "DoubleBomb");
					this.powerUps.add(powers);
				}
				if (file.file[i][j] == 'D') {
					PowerUp powers = new PowerUp((double) x, (double) y, "DelayBlast");
					this.powerUps.add(powers);
				}
				if (file.file[i][j] == 'F') {
					PowerUp powers = new PowerUp((double) x, (double) y, "FarBlast");
					this.powerUps.add(powers);
				}
				if (file.file[i][j] == 'L') {
					PowerUp powers = new PowerUp((double) x, (double) y, "AddLive");
					this.powerUps.add(powers);
				}
				if (file.file[i][j] == 'G') {
					HoundDog dogs = new HoundDog((double) x, (double) y, null);
					this.dogs.add(dogs);
				}
				if (file.file[i][j] == 'M') {
					MetalGear metalGear = new MetalGear((double) x, (double) y, null,this.world);
					this.metalGear.add(metalGear);
				}
				if (file.file[i][j] == 'I') {
					PolicemanWithHeavyArmor policemanWithHeavyArmor = new PolicemanWithHeavyArmor(true, (double) x, (double) y, null);
					this.policemanWithHeavyArmor.add(policemanWithHeavyArmor);
				}
				if (file.file[i][j] == 'A') {
					WinScreen winScreen = new WinScreen(100.0, 100.0,null);
					this.winScreen.add(winScreen);
				}
				if (file.file[i][j] == 'C') {
						PowerUp powers = new PowerUp((double) x, (double) y, "GoHome");
						this.powerUps.add(powers);
				}
				
				x += 50;

			}
			y += 50;
			x = 0;
		}


	}

	
	public ArrayList<PowerUp> getPowerUps() {
		return this.powerUps;
	}

	public void setPowerUps(ArrayList<PowerUp> powerUps) {
		this.powerUps = powerUps;
	}

	public ArrayList<Fence> getFences(){
		return this.fences;
	}
	public ArrayList<MetalGear> getMetalGear() { 
		return this.metalGear;
	}
	public ArrayList<PolicemanWithHeavyArmor> getPolicemanWithHeavyArmor() { 
		return this.policemanWithHeavyArmor;
	}
	
	

	/**
	 * Getter for walls
	 *
	 * @return ArrayList
	 */
	public ArrayList<Wall> getWalls() {
		return this.walls;
	}

	/**
	 * Getter for policemans
	 *
	 * @return ArrayList
	 */
	public ArrayList<Policeman> getPolicemans() {
		return this.policemans;
	}
	public ArrayList<HoundDog> getHoundDogs() {
		return this.dogs;
	}
	
	public ArrayList<Helicopter> getHelicopters() {
		return this.helicopters;
	}
	
	public GameWorld getWorld(){
		return this.world;
	}
	
	/**
	 * Set up walls
	 *
	 * @param walls
	 */
	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}
	
	public void setFences(ArrayList<Fence> fences){
		this.fences = fences;
	}
	
	

	/**
	 * Getter for number of level
	 *
	 * @return integer
	 */
	public int getLevelNumber() {
		return this.levelNumber;
	}
	

	public ReadFile getFile(){
		return this.file;
	}


	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public  ArrayList<WinScreen> getWinScreen() {
		return this.winScreen;
	}
	
//	public Audio getAudio() {
//		return this.audio;
//	}

	
}
