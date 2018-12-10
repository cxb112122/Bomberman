import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard control
 *
 * @author Lingxiao. Created Feb 2, 2017.
 */
public class Keyboardcontrol implements KeyListener {
	private GameComponent gameComponent;
	private int timepaused=0;

	/**
	 * Constructor for KeyBoardControl
	 * 
	 * @param gameComponent
	 */

	public Keyboardcontrol(GameComponent gameComponent) {
		this.gameComponent = gameComponent;
	}

	/**
	 * Actions corresponding to keyPressed
	 *
	 * @param e
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		// check to see if move intersects wall
		this.gameComponent.getHero().moveHero(e);
		int count = 0;
		for (Wall wall : this.gameComponent.level.getWalls()) {
			if (count < 1) {
				if (this.gameComponent.hero.getHero().intersects(wall.wall))
					count++;
			}
		}
		for (Fence fence : this.gameComponent.level.getFences()) {
			if (count < 1) {
				if (this.gameComponent.hero.getHero().intersects(fence.fence))
					count++;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_B){
			if(this.gameComponent.hero.getBombs().size() > 0){
				this.gameComponent.hero.getBombs().get(0).setBlowUp(1000);
			}
		}
		
		// Move one step back if intersect with walls
		if (count > 0) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				this.gameComponent.hero.updatePositiondown();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				this.gameComponent.hero.updatePositionup();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				this.gameComponent.hero.updatePositionleft();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				this.gameComponent.hero.updatePositionright();
			}
		}

		// Change level
		if (e.getKeyCode() == KeyEvent.VK_U && this.gameComponent.level.getLevelNumber() < 5) {
			this.gameComponent.level = new LevelRead(this.gameComponent.level.getLevelNumber() + 1);
			if(this.gameComponent.level.getLevelNumber() ==1){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(1);
				this.gameComponent.getAudio().music();
			}
			if(this.gameComponent.level.getLevelNumber() ==2){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(2);
				this.gameComponent.getAudio().music();
			}
			if(this.gameComponent.level.getLevelNumber() ==3){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(3);
				this.gameComponent.getAudio().music();
			}
			if(this.gameComponent.level.getLevelNumber() ==4){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(4);
				this.gameComponent.getAudio().music();
			}

	
			
		} else if (e.getKeyCode() == KeyEvent.VK_D && this.gameComponent.level.getLevelNumber() > 1) {
			this.gameComponent.level = new LevelRead(this.gameComponent.level.getLevelNumber() - 1);
			if(this.gameComponent.level.getLevelNumber() ==1){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(1);
				this.gameComponent.getAudio().music();
			}
			if(this.gameComponent.level.getLevelNumber() ==2){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(2);
				this.gameComponent.getAudio().music();
			}
			if(this.gameComponent.level.getLevelNumber() ==3){
				this.gameComponent.getAudio().getClip().close();
				this.gameComponent.getAudio().updateCount(3);
				this.gameComponent.getAudio().music();
			}
		} else {
			// do nothing
		}
		//isPause
		if(e.getKeyCode()==KeyEvent.VK_P){
			timepaused++;
			if(timepaused%2!=0){
				this.gameComponent.getAudio().pauseMusic();
			}
			else{
				this.gameComponent.getAudio().continueMusic();
			}
			
			this.gameComponent.getHero().setIsPaused(timepaused%2==0);
			for (Policeman policeman : this.gameComponent.level.getPolicemans()) {
				policeman.setIsPaused(timepaused%2==0);
			}
			for(Helicopter helicopter:this.gameComponent.level.getHelicopters()){
				helicopter.setIsPaused(timepaused%2==0);
			}
			for(HoundDog hounddog:this.gameComponent.level.getHoundDogs()){
				hounddog.setIsPaused(timepaused%2==0);
			}
			for(PolicemanWithHeavyArmor twopolice:this.gameComponent.level.getPolicemanWithHeavyArmor()){
				twopolice.setIsPaused(timepaused%2==0);
			}
			for(MetalGear boss:this.gameComponent.level.getMetalGear()){
				boss.setIsPaused(timepaused%2==0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
