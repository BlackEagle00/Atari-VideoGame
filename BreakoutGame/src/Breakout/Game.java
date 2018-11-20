package Breakout;
import java.awt.event.KeyListener;
import java.io.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
/**
 * 
 * @author Andrés Guillermo Bonilla Olarte
 *
 */
public class Game extends JPanel implements KeyListener, ActionListener
{
	Image img = Toolkit.getDefaultToolkit().createImage("D:\\Eclipse\\Eclipse\\Breakout\\src\\background.jpg");
	private boolean play = false; //Game haven't already started
	
	private int score = 0; //Score must start at 0, because we haven't already play
	private int totalbricks = 25; //Total bricks to break
	
	private Timer time;
	private int delay = 5; //Ball speed
	
	private int playerX = 300; //Initial player position
	
	private int ball_positionX = 340; //Initial ball position
	private int ball_positionY = 535; //Initial ball position
	private int ball_X_direction = -1;
	private int ball_Y_direction = -2;
	
	private MapGenerator map;
	
	public Game() 
	{
		map = new MapGenerator(5, 6);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) 
	{
		//Creating Background
		g.setColor(Color.black);
		g.fillRect(0, 0, 700, 600);
		//g.drawImage(img, 0, 0, null);
		
		//Drawing map
		map.draw((Graphics2D)g); //Importing the MapGenerator Class
		
		//Creating Borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 700, 3);
		g.fillRect(692, 0, 3, 600);
		
		//Score
		g.setColor(Color.cyan);
		g.setFont(new Font("serif", Font.BOLD, 23));
		g.drawString("Score: "+score, 550, 30); //Showing the score
		
		//Creating Paddle
		g.setColor(Color.red);
		g.fillRect(playerX, 550, 100, 5);
		
		//Creating Ball
		g.setColor(Color.blue);
		g.fillOval(ball_positionX, ball_positionY, 15, 15); //Ball size
		
		if(totalbricks == 0) 
		{
			play = false;
			ball_X_direction = 0;
			ball_Y_direction = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString("Congratulations, you won. Final Score: "+score, 260, 300);
			g.drawString("Press Enter to restart", 230, 350);
		}
		
		if(ball_positionY > 570) 
		{
			play = false;
			ball_X_direction = 0;
			ball_Y_direction = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 23));
			g.drawString("Game Over, Final Score: "+score, 200, 300);
			g.drawString("Press Enter to restart", 250, 350);
		}
		g.dispose();
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		time.start();
		if(play) 
		{
			if(new Rectangle(ball_positionX, ball_positionY, 15, 15).intersects(new Rectangle(playerX, 550, 100, 5))) 
			{
				ball_Y_direction = -ball_Y_direction;
			}
			
			A : for (int i = 0; i <map.map.length; i++) 
			{
				for (int j = 0; j <map.map[0].length; j++) 
				{
					if (map.map[i][j] > 0) 
					{
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rectangle = new Rectangle (brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRectangle = new Rectangle (ball_positionX, ball_positionY, 20, 20);
						Rectangle brickRectangle = rectangle;
						
						if(ballRectangle.intersects(brickRectangle)) 
						{
							map.setBrickValue(0, i, j);
							totalbricks--;
							score += 5;
							
							if(ball_positionX + 19 <= brickRectangle.x || ball_positionX + 1 >= brickRectangle.x + brickRectangle.width) 
							{
								ball_X_direction = -ball_X_direction; //Moving the ball to the opposite direction
							}
							else 
							{
								ball_Y_direction = -ball_Y_direction; //Moving the ball to the opposite direction
							}
							break A; //Getting out of the "A" loop
						}
					}
				}
			}
			
			ball_positionX += ball_X_direction;
			ball_positionY += ball_Y_direction;
			
			if(ball_positionX < 0) 
			{
				ball_X_direction = -ball_X_direction;
			}
			if(ball_positionY < 0) 
			{
				ball_Y_direction = -ball_Y_direction;
			}
			if(ball_positionX > 680) 
			{
				ball_X_direction = -ball_X_direction;
			}
		}
		
		repaint(); //Call Paint method and will paint again the map
		
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) //Moving player's paddle
		{
			if(playerX >= 600) 
			{
				playerX = 600; 
			}
			else
			{
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) //Moving player's paddle
		{
			if(playerX < 4) 
			{
				playerX = 4;
			}
			else
			{
				moveLeft();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) 
		{
			if (!play) 
			{
				play = true;
				ball_positionX = 340;
				ball_positionX = 535;
				ball_X_direction = -1;
				ball_Y_direction = -2;
				playerX = 300;
				score = 0;
				totalbricks = 30;
				map = new MapGenerator (5, 6);
				
				repaint();
			}
		}
	}
	
	public void moveRight() 
	{
		play = true;
		playerX += 20;
	}
	public void moveLeft() 
	{
		play = true;
		playerX -= 20;
	}
}