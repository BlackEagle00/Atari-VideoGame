package Breakout;
import javax.swing.JFrame;
/**
 * 
 * @author Andrés Guillermo Bonilla Olarte
 *
 */
public class Main
{
	public static void main (String [] args) 
	{
		JFrame object = new JFrame();
		Game game = new Game();
		object.setBounds(10, 10, 700, 600); //Creating the size of the frame
		object.setTitle("Breakout"); //Frame's title
		object.setResizable(false);
		object.setVisible(true); 
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close the frame, if we click "Close button"
		object.add(game);
	}
}