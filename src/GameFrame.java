import javax.swing.JFrame;


public class GameFrame extends JFrame{
	final protected GameComponent component;
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param component
	 */
	public GameFrame(GameComponent component) {
		this.component = component;
		this.add(this.component);
		this.addKeyListener(this.component.getKeyboardControl());
		if(this.component.hero.getLives() <1)
			this.setTitle("GAME OVER");
		this.setTitle("Bomber Man");
		this.setResizable(false);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
