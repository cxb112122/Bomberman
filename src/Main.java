import javax.swing.JFrame;

//import events.ColoredSquareComponent;
//import events.Main;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {
	
	
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		Main instance = new Main();
		instance.createWindow();
	}
	public void createWindow() {
		GameComponent myComponent = new GameComponent();
		JFrame frame = new GameFrame(myComponent);
		frame.setSize(900,630);	
		frame.setVisible(true);
}
}
