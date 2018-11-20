package Breakout;
import java.awt.*;
/**
 * 
 * @author Andrés Guillermo Bonilla Olarte
 *
 */
public class MapGenerator 
{
	public int map [][]; //Creating the matrix, for the map
	public int brickWidth; //Creating variable for brick's width
	public int brickHeight; //Creating variable for brick's height
	
	public MapGenerator (int rows, int columns) 
	{
		map = new int [rows][columns];
		
		for(int i = 0; i < map.length; i++) 
		{
			for (int j = 0; j < map.length; j++) 
			{
				map[i][j] = 1;
			}
		}
		
		brickWidth = 650/columns;
		brickHeight = 150/rows;
	}
	
	public void draw (Graphics2D g) //Function to draw the map, generate the bricks and divide them
	{
		for(int i = 0; i < map.length; i++) 
		{
			for (int j = 0; j < map.length; j++) 
			{
				if(map[i][j] > 0) 
				{
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int rows, int columns) 
	{
		map[rows][columns] = value;
	}
}