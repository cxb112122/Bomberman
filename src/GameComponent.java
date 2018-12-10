import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class GameComponent extends JComponent {
	GameWorld world = new GameWorld();
	Hero hero = new Hero(this.world, this);

	private Keyboardcontrol keyboardcontrol;
	LevelRead level;
	ArrayList<Bomb> bombsToRemove;
	ArrayList<Policeman> policemansToRemove;
	ArrayList<Fence> fencesToRemove;
	ArrayList<Helicopter> helicoptersToRemove;
	ArrayList<HoundDog> dogsToRemove;
	ArrayList<MetalGear> metalGearToRemove;
	ArrayList<PolicemanWithHeavyArmor> policemanWithHeavyArmorToRemove;
	ArrayList<PowerUp> powersToRemove;
	ArrayList<BossWeapon> nukesToRemove;
	HashMap<String, Integer> leaders;
	private int score;
	private int time;
	private Audio audio;
	private int endgame = 0;

	public GameComponent() {
		this.audio = new Audio();
		this.policemansToRemove = new ArrayList<Policeman>();
		this.fencesToRemove = new ArrayList<Fence>();
		this.helicoptersToRemove = new ArrayList<Helicopter>();
		this.bombsToRemove = new ArrayList<Bomb>();
		this.powersToRemove = new ArrayList<PowerUp>();
		this.dogsToRemove = new ArrayList<HoundDog>();
		this.metalGearToRemove = new ArrayList<MetalGear>();
		this.policemanWithHeavyArmorToRemove = new ArrayList<PolicemanWithHeavyArmor>();
		this.nukesToRemove = new ArrayList<BossWeapon>();
		this.leaders = new HashMap<String, Integer>();
		this.keyboardcontrol = new Keyboardcontrol(this);
		this.level = new LevelRead(1);
		this.score = 0;

		this.time = 10000000;

		this.time = 100000;

		this.time = 1000000;

		this.audio = new Audio();
		// if(this.level.getLevelNumber()!=1){
		this.audio.music();
		// }
		// System.out.println(this.level.getLevelNumber());

		Runnable repainter = new Runnable() {

			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(10);

						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		// draw background
		String filebackground = "Images/background";
		filebackground += ".png";
		BufferedImage imgbackground;
		try {
			imgbackground = ImageIO.read(new File(filebackground));
			g.drawImage(imgbackground, 0, 0, 950, 650, null);
		} catch (IOException e) {

		}

		/*
		 * this method redraws the entire game environment it starts with bombs
		 * because most other classes depend on bomb reactions to determine
		 * whether to redraw or remove. second are the policemans because they
		 * can also kill the hero. third will be breakable walls, then the hero,
		 * and lastly the walls.
		 */
		// draws all of the unbreakable walls
		for (WinScreen winScreen : this.level.getWinScreen()) {
			String fileNameBuffalo = "Images/BuffaloWin";
			fileNameBuffalo += ".png";
			BufferedImage imgwall;
			try {
				imgwall = ImageIO.read(new File(fileNameBuffalo));
				g.drawImage(imgwall, 250, 5, 400, 550, null);
			} catch (IOException e) {
			}
		}

		// draw bombs
		for (Bomb bomb : this.world.getBombs()) {
			String fileName = "Images/bomb";
			fileName += ".png";
			BufferedImage img;
			try {
				img = ImageIO.read(new File(fileName));
				g.drawImage(img, (int) bomb.getX(), (int) bomb.getY(), 50, 50, null);
			} catch (IOException e) {
			}
		}

		// draw nukes
		for (BossWeapon nuke : level.getWorld().getNukes()) {
			nuke.setWorld(this);
			nuke.moveTo();
			if (this.hero.getHero().intersects(nuke.getBossWeapon())) {
				this.audio.playSound("scream");
				this.nukesToRemove.add(nuke);
				this.hero.die();

				System.out.println("died");
			}
			for (Wall wall : this.level.getWalls()) {
				if (nuke.getBossWeapon().intersects(wall.getWall())) {
					this.nukesToRemove.add(nuke);
				}
			}
			String fileName = "Images/nuke";
			fileName += ".png";
			BufferedImage img;
			try {
				img = ImageIO.read(new File(fileName));
				g.drawImage(img, (int) nuke.getBossWeapon().getBounds().getX(),
						(int) nuke.getBossWeapon().getBounds().getY(), 30, 30, null);
			} catch (IOException e) {

			}
		}
		// draws the powerups
		for (PowerUp power : this.level.getPowerUps()) {
			if (power.getSkill().equals("DoubleBomb")) {
				String fileName = "Images/powerupmutibomb";
				fileName += ".png";
				BufferedImage img;
				try {
					img = ImageIO.read(new File(fileName));
					g.drawImage(img, (int) power.getxCoord(), (int) power.getyCoord(), 50, 50, null);
				} catch (IOException e) {

				}
			} else if (power.getSkill().equals("FarBlast")) {
				String fileName = "Images/powerupdistance";
				fileName += ".png";
				BufferedImage img;
				try {
					img = ImageIO.read(new File(fileName));
					g.drawImage(img, (int) power.getxCoord(), (int) power.getyCoord(), 50, 50, null);
				} catch (IOException e) {

				}
			} else if (power.getSkill().equals("DelayBlast")) {
				String fileName = "Images/controlbomb";
				fileName += ".PNG";
				BufferedImage img;
				try {
					img = ImageIO.read(new File(fileName));
					g.drawImage(img, (int) power.getxCoord(), (int) power.getyCoord(), 50, 50, null);
				} catch (IOException e) {

				}
			} else if (power.getSkill().equals("GoHome")) {
				String fileName = "Images/gohome";
				fileName += ".PNG";
				BufferedImage img;
				try {
					img = ImageIO.read(new File(fileName));
					g.drawImage(img, (int) power.getxCoord(), (int) power.getyCoord()-30, 60, 80, null);
				} catch (IOException e) {

				}
			} else {
				String fileName = "Images/heart";
				fileName += ".png";
				BufferedImage img;
				try {
					img = ImageIO.read(new File(fileName));
					g.drawImage(img, (int) power.getxCoord(), (int) power.getyCoord(), 50, 50, null);
				} catch (IOException e) {

				}
			}

		}
		// creates the 5 rectangles that make up the bomb blasts
		if (this.hero.getBombs().size() > 0) {
			for (Bomb bomb1 : this.hero.getBombs()) {
				if (this.hero.getPowerUp() != null) {
					if (this.hero.getPowerUp().equals("DelayBlast")) {
						bomb1.setBlowUp(bomb1.getBlowUp() - 100);
					}
				}
				if (bomb1.getBlowUp() > 500) {
					if (bomb1.getBlastTime() < 20) {
						bomb1.explode();
						this.audio.playSound("bomb");
					}
					for (Rectangle2D.Double blast : bomb1.getBlasts()) {
						String fileNamefire = "Images/hqdefault1";
						fileNamefire += ".png";
						BufferedImage imgfire;
						try {
							imgfire = ImageIO.read(new File(fileNamefire));
							g.drawImage(imgfire, (int) blast.getBounds().getX(), (int) blast.getBounds().getY(), 50, 50,
									null);
						} catch (IOException e) {
						}
					}
					bomb1.setBlastTime(bomb1.getBlastTime() + 50);
					if (bomb1.getBlastTime() > 500) {
						this.bombsToRemove.add(bomb1);
					}
					for (Bomb bomb2 : this.hero.getBombs()) {
						for (Rectangle2D.Double blast : bomb1.getBlasts()) {
							if (bomb2.getBomb().intersects(blast)) {
								bomb2.setBlowUp(1000);
							}
						}
					}
				} else {
					bomb1.setBlowUp(bomb1.getBlowUp() + 25);
				}
			}
		}

		// checks to make sure the hero isn't inside the bomb blasts
		if (this.hero.getBombs().size() > 0) {
			for (Bomb bomb1 : this.hero.getBombs()) {
				for (Rectangle2D.Double blast : bomb1.getBlasts()) {
					if (blast.intersects(this.hero.getHero())) {
						this.audio.playSound("scream");
						this.hero.die();
						System.out.println("died");
					}
				}
			}
		}
		// draws the hero
		String fileName = "Images/bomberman";
		fileName += ".png";
		BufferedImage img;
		try {
			img = ImageIO.read(new File(fileName));
			g.drawImage(img, (int) this.hero.getHero().getX(), (int) this.hero.getHero().getY(), 50, 50, null);
		} catch (IOException e) {

		}

		// draw policemans/ adds them to be removed if they are inside the
		// blasts
		for (Policeman policeman : this.level.getPolicemans()) {
			policeman.setWorld(this);
			policeman.moveTo();
			if (this.hero.getHero().intersects(policeman.getPoliceman())) {
				this.audio.playSound("scream");
				this.hero.die();
				System.out.println("died");
			}
			if (this.hero.getBombs().size() > 0) {
				for (Rectangle2D.Double blast : this.hero.getBombs().get(0).getBlasts()) {
					if (blast.intersects(policeman.getPoliceman())) {
						this.policemansToRemove.add(policeman);
						this.score = this.score + 100;

					}
				}
			}
			String fileNamepolice = "Images/copp";
			fileNamepolice += ".png";
			BufferedImage imgpolice;
			try {
				imgpolice = ImageIO.read(new File(fileNamepolice));
				g.drawImage(imgpolice, (int) policeman.getPoliceman().getX(), (int) policeman.getPoliceman().getY(), 50,
						50, null);
			} catch (IOException e) {
			}
		}
		// draw policemanWithHeavyArmor adds them to be removed if they are
		// inside the blasts
		for (PolicemanWithHeavyArmor policemanWithHeavyArmor : this.level.getPolicemanWithHeavyArmor()) {
			policemanWithHeavyArmor.setWorld(this);
			policemanWithHeavyArmor.moveTo();
			if (this.hero.getHero().intersects(policemanWithHeavyArmor.getPoliceman())) {
				this.audio.playSound("scream");
				this.hero.die();
				System.out.println("died");
			}
			if (this.hero.getBombs().size() > 0) {
				for (Rectangle2D.Double blast : this.hero.getBombs().get(0).getBlasts()) {
					if (blast.intersects(policemanWithHeavyArmor.getPoliceman())) {

						if (policemanWithHeavyArmor.getLives() == 2)
							policemanWithHeavyArmor.setDeathTimer(System.currentTimeMillis());
						policemanWithHeavyArmor.setLives(policemanWithHeavyArmor.getLives() - 1);

						if (System.currentTimeMillis() > policemanWithHeavyArmor.getDeathTimer() + 2000) {
							this.policemanWithHeavyArmorToRemove.add(policemanWithHeavyArmor);
							this.score = this.score + 200;
						}
						;
					}

				}
			}
			String fileNamepolice = "Images/heavyarmor operator1";
			fileNamepolice += ".PNG";
			BufferedImage imgpolice;
			try {
				imgpolice = ImageIO.read(new File(fileNamepolice));
				g.drawImage(imgpolice, (int) policemanWithHeavyArmor.getPoliceman().getX(),
						(int) policemanWithHeavyArmor.getPoliceman().getY(), 50, 50, null);
			} catch (IOException e) {
			}
		}

		// draw metalGear adds them to be removed if they are inside the blasts
		for (MetalGear metalGear : this.level.getMetalGear()) {
			metalGear.setWorld(this);
			metalGear.moveTo();
			if (this.hero.getHero().intersects(metalGear.getMetalGear())) {
				this.audio.playSound("scream");
				this.hero.die();
				metalGear.setMetalGear(new Rectangle2D.Double(450, 300, 180, 180));
				System.out.println("died");
			}
			if (this.hero.getBombs().size() > 0) {
				for (Rectangle2D.Double blast : this.hero.getBombs().get(0).getBlasts()) {
					if (blast.intersects(metalGear.getMetalGear())) {

						if (metalGear.getHp() > 1 && metalGear.getDeathTimer() == 0) {
							metalGear.setDeathTimer(System.currentTimeMillis());
							metalGear.beHarmed();
						}

						if (System.currentTimeMillis() > metalGear.getDeathTimer() + 2000 && metalGear.getHp() > 0) {
							metalGear.beHarmed();
							metalGear.setDeathTimer(System.currentTimeMillis());
						}
						if (metalGear.getHp() == 0)
							this.metalGearToRemove.add(metalGear);
						;
					}
					if (metalGear.getHp() == 0) {
						this.metalGearToRemove.add(metalGear);
						this.score = this.score + 500;
					}
					;

				}
			}
			String fileNamepolice = "Images/metalgear1";
			fileNamepolice += ".PNG";
			BufferedImage imgpolice;
			try {
				imgpolice = ImageIO.read(new File(fileNamepolice));
				g.drawImage(imgpolice, (int) metalGear.getMetalGear().getX(), (int) metalGear.getMetalGear().getY(),
						180, 180, null);
			} catch (IOException e) {
			}
		}
		// draw dogs
		for (HoundDog dog : this.level.getHoundDogs()) {
			dog.setWorld(this);
			dog.moveTo();
			if (this.hero.getHero().intersects(dog.getHoundDog())) {
				this.audio.playSound("scream");
				this.hero.die();

				System.out.println("died");
			}
			if (this.hero.getBombs().size() > 0) {
				for (Rectangle2D.Double blast : this.hero.getBombs().get(0).getBlasts()) {
					if (blast.intersects(dog.getHoundDog())) {
						this.dogsToRemove.add(dog);
						this.score = this.score + 150;
					}
				}
			}
			String fileNamedog = "Images/dog";
			fileNamedog += ".png";
			BufferedImage imgboss;
			try {
				imgboss = ImageIO.read(new File(fileNamedog));
				g.drawImage(imgboss, (int) dog.getHoundDog().getX(), (int) dog.getHoundDog().getY(), 50, 50, null);
			} catch (IOException e) {
			}

		}

		// draws all of the unbreakable walls
		for (Wall wall : this.level.getWalls()) {
			String fileNamewall = "Images/wall";
			fileNamewall += ".png";
			BufferedImage imgwall;
			try {
				imgwall = ImageIO.read(new File(fileNamewall));
				g.drawImage(imgwall, (int) wall.getxCoord(), (int) wall.getyCoord(), 50, 50, null);
			} catch (IOException e) {
			}
		}
		// draws all of the fences
		for (Fence fence : this.level.getFences()) {
			fence.setWorld(this);
			if (this.hero.getBombs().size() > 0) {
				for (Rectangle2D.Double blast : this.hero.getBombs().get(0).getBlasts()) {
					if (blast.intersects(fence.getfence())) {
						this.fencesToRemove.add(fence);
						this.score = this.score + 10;
					}
				}
			}
			String fileNamefence = "Images/fence";
			fileNamefence += ".png";
			BufferedImage imgfence;
			try {
				imgfence = ImageIO.read(new File(fileNamefence));
				g.drawImage(imgfence, (int) fence.getfence().getBounds().getX(),
						(int) fence.getfence().getBounds().getY(), 50, 50, null);
			} catch (IOException e) {
			}
		}

		// draw helicopter

		for (Helicopter helicopter : this.level.getHelicopters()) {
			helicopter.setWorld(this);
			helicopter.moveTo();

			if (this.hero.getHero().intersects(helicopter.getHelicopter())) {
				this.audio.playSound("scream");
				this.hero.die();

				System.out.println("died");
			}
			if (this.hero.getBombs().size() > 0) {
				for (Rectangle2D.Double blast : this.hero.getBombs().get(0).getBlasts()) {
					if (blast.intersects(helicopter.getHelicopter())) {
						this.helicoptersToRemove.add(helicopter);
						this.score = this.score + 300;
					}
				}
			}
			String fileNameHelicopter = "Images/helicopter";
			fileNameHelicopter += ".png";
			BufferedImage imgHelicopter;
			try {
				imgHelicopter = ImageIO.read(new File(fileNameHelicopter));
				g.drawImage(imgHelicopter, (int) helicopter.getHelicopter().getX(),
						(int) helicopter.getHelicopter().getY(), 80, 80, null);
			} catch (IOException e) {
			}
			// checks if the hero intercects the powerups
		}
		for (PowerUp power : this.level.getPowerUps()) {
			if (power.location.intersects(this.hero.getHero())) {
					this.hero.setPowerUp(power.getSkill());
					this.powersToRemove.add(power);
				if(power.getSkill().equals("DoubleBomb") || power.getSkill().equals("FarBlast") || power.getSkill().equals("DelayBlast")){
						this.audio.pauseMusic();
						this.audio.playSound("powerup");
						this.audio.continueMusic();
				}
				if (power.getSkill().equals("AddLive")) {
					this.audio.playSound("heart");
					this.hero.setLives(this.hero.getLives() + 1);
					power.setSkill(this.hero.getPowerUp());
				}
				if (power.getSkill().equals("GoHome")) {
					this.audio.playSound("gohome");
					if (this.hero.getBombs().size() > 0) {
						this.hero.getBombs().removeAll(this.hero.getBombs());
						this.world.getBombs().removeAll(this.hero.getBombs());
					}
					int lastScore = this.score;
					this.score = 0;
					this.time = 100000;
					this.hero.setPowerUp(null);
					this.hero.setLives(3);
					this.hero.moveTo(50., 50.);
					this.level = new LevelRead(1);
					this.getAudio().getClip().close();
					this.getAudio().updateCount(1);
					this.getAudio().music();
				}
			}
		}
		Font f1 = new Font("Helvetica", Font.PLAIN, 25);
		g.setFont(f1);
		g.setColor(Color.black);
		String life = "Lives:" + "  " + this.hero.getLives();
		g.drawString(life, 750, 30);
		String le = "Level:  " + this.level.getLevelNumber();
		g.drawString(le, 30, 30);
		g.drawString("Score:" + this.score, 300, 30);
		g.drawString("Time:" + this.time / 1000, 500, 30);

		// removes the policemans, "kills" the hero, and eventually breakable
		// walls, from the gameworld
		if (this.fencesToRemove.size() > 0) {
			// this.score += 10 * this.fencesToRemove.size();
			this.level.getFences().removeAll(this.fencesToRemove);
			this.fencesToRemove.removeAll(this.fencesToRemove);
		}

		if (this.metalGearToRemove.size() > 0) {
			// this.score += 1000 * this.metalGearToRemove.size();
			this.level.getMetalGear().removeAll(this.metalGearToRemove);
			this.metalGearToRemove.removeAll(this.metalGearToRemove);
		}

		if (this.dogsToRemove.size() > 0) {
			// this.score += 100 * this.dogsToRemove.size();
			this.level.getHoundDogs().removeAll(this.dogsToRemove);
		}

		if (this.policemansToRemove.size() > 0) {
			// this.score += 100 * this.policemansToRemove.size();
			this.level.getPolicemans().removeAll(this.policemansToRemove);
			this.policemansToRemove.removeAll(this.policemansToRemove);
		}
		if (this.helicoptersToRemove.size() > 0) {
			// this.score += 150 * this.helicoptersToRemove.size();
			this.level.getHelicopters().removeAll(this.helicoptersToRemove);
			this.helicoptersToRemove.removeAll(this.helicoptersToRemove);
		}
		if (this.powersToRemove.size() > 0) {
			// this.score += 25 * this.powersToRemove.size();
			this.level.getPowerUps().removeAll(this.powersToRemove);
			this.powersToRemove.removeAll(this.powersToRemove);
		}
		if (this.bombsToRemove.size() > 0) {
			this.world.getBombs().removeAll(this.bombsToRemove);
			this.hero.getBombs().removeAll(this.bombsToRemove);
			this.bombsToRemove.removeAll(this.bombsToRemove);
		}
		if (this.nukesToRemove.size() > 0) {
			this.level.getWorld().getNukes().removeAll(this.nukesToRemove);
			this.bombsToRemove.removeAll(this.bombsToRemove);

		}
		if (this.policemanWithHeavyArmorToRemove.size() > 0) {
			this.level.getPolicemanWithHeavyArmor().removeAll(this.policemanWithHeavyArmorToRemove);
		}
		if (this.level.getPolicemans().size() == 0 && this.level.getHoundDogs().size() == 0
				&& this.level.getHelicopters().size() == 0 && this.level.getPolicemanWithHeavyArmor().size() == 0
				&& this.level.getMetalGear().size() == 0 && this.level.getWinScreen().size() == 0) {
			// if (this.hero.getBombs().size() > 0) {
			// for (Bomb bomb : this.hero.getBombs()) {
			// this.hero.getBombs().remove(bomb);
			// this.world.getBombs().remove(bomb);
			// }
			// }

			this.hero.die();
			this.hero.setLives(this.hero.getLives() + 1);
			this.level = new LevelRead(this.level.getLevelNumber() + 1);
			if (this.level.getLevelNumber() == 2) {
				this.getAudio().getClip().close();
				this.getAudio().updateCount(2);
				this.getAudio().music();
			}
			if (this.level.getLevelNumber() == 3) {
				this.getAudio().getClip().close();
				this.getAudio().updateCount(3);
				this.getAudio().music();
			}
			if (this.level.getLevelNumber() == 4) {
				int lastScore = this.score;
				String leadersList = "";
				if (this.leaders.size() > 0) {
					for (String leader : this.leaders.keySet()) {
						leadersList += leader + " " + this.leaders.get(leader);
						leadersList += "\n";
					}
				}
				this.getAudio().getClip().close();
				this.getAudio().updateCount(4);
				this.getAudio().music();
				String leader = JOptionPane.showInputDialog(
						"LEADERS: \n" + leadersList + "YOUR SCORE: " + lastScore + "\n" + "ENTER YOUR NAME:");
				this.leaders.put(leader, lastScore);

			}
			this.time = 10000000;
		}
		if (this.level.getLevelNumber() == 4) {
			this.time = 10000000;
		}
		if (this.hero.getLives() < 1 || this.time / 1000 == 0 && this.endgame == 0) {
			if (this.hero.getBombs().size() > 0) {
				this.hero.getBombs().removeAll(this.hero.getBombs());
				this.world.getBombs().removeAll(this.hero.getBombs());
			}
			int lastScore = this.score;
			this.score = 0;
			this.time = 100000;
			this.hero.setPowerUp(null);
			this.hero.setLives(3);
			System.out.println("END GAME");
			this.level = new LevelRead(1);
			this.getAudio().getClip().close();
			this.getAudio().updateCount(1);
			this.getAudio().music();
			String leadersList = "";
			if (this.leaders.size() > 0) {
				for (String leader : this.leaders.keySet()) {
					leadersList += leader + " " + this.leaders.get(leader);
					leadersList += "\n";
				}
			}
			String leader = JOptionPane.showInputDialog(
					"LEADERS: \n" + leadersList + "YOUR SCORE: " + lastScore + "\n" + "ENTER YOUR NAME:");
			this.leaders.put(leader, lastScore);
		}
		// updates the time and prints the score
		this.time -= 30;
		return;
	}

	public Hero getHero() {
		return this.hero;
	}

	public KeyListener getKeyboardControl() {
		// TODO Auto-generated method stub.
		return this.keyboardcontrol;
	}

	// public void gameOver(){
	// GameComponent myComponent = new GameComponent();
	// JFrame frame = new GameFrame(myComponent);
	// frame.setSize(800,800);
	// frame.setVisible(true);
	// }
	public Audio getAudio() {
		return this.audio;
	}
}
