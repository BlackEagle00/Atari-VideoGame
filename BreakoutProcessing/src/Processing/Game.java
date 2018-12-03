package Processing;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author Andrés_Bonilla
 *
 */
public class Game extends PApplet
{
	int x, y, w, h, speedX, speedY;
	int paddleX, paddleY, paddleW, paddleH, paddleS;
	boolean left, right;
	int score = 0;
	int level = 1;
	int[] blocks = new int[20];
	PImage background;
	
	public void settings() 
	{	
		
		background = loadImage("BackgroundNight.png");
		size (700, 600); //Window size
		x = 350;
	    y = 570;
	    w = 20; //Ball size
	    h = 20; //Ball size
	    speedX = 3; //Ball Speed
	    speedY = 3; //Ball Speed
	    
	    paddleX = 300; //Paddle's location
	    paddleY = 580; //Paddle's location
	    paddleW = 100; //Paddle's size
	    paddleH = 30;  //Paddle's size
	    paddleS = 8;   //Paddle's speed
	    
	    for (int i=0; i<20; i++) 
		{
		    blocks[i] = 1;
		}
	}
	  
	public void draw() //Calling all functions
	{
		image(background, 0, 0);
		//background(0);
	    drawCircle();
	    moveCircle();
	    bounceOff();
	    
	    drawPaddle();
	    movePaddle();
	    restrictPaddle();
	    contactPaddle();
	    
	    drawBricks();
	    
	    score();
	   
	}
	 
	public void drawCircle()
	{
	    fill(255,255,0);
	    ellipse(x, y, w, h);
	}

	public void moveCircle()
	{    
	    x = x + speedX;
	    y = y + speedY;
	}   
	  
	public void bounceOff()
	{
	    if (x > width - w/2)
		{	    	
	    	speedX = -speedX;
		}
		else if (x < 0 + w/2)
		{
		    speedX = -speedX;
		}
		if (y > height - h/2)
		{
		    settings(); //Reset the game, when it hits the bottom edge
		    speedY = -speedY;
		}
		else if (y < 0 + h/2)
		{
		    speedY = -speedY;
		}
	}
	  
  	public void drawPaddle() 
  	{
  		fill(255, 0, 0);
  		rect(paddleX, paddleY, paddleW, paddleS);
  	}
  	
  	public void movePaddle() 
  	{
  		if (left) 
  		{
  			paddleX = paddleX - paddleS;
  		}
  		if (right) 
  		{
  			paddleX = paddleX + paddleS;
  		}
  	}
  	
  	public void restrictPaddle() //The paddle won't dissapear, it will collide against the edge
  	{
  		if(paddleX < 0) //Left edge
  		{
  			paddleX = paddleX + paddleS;
  		}
  		if(paddleX - paddleW/2 > 560) //Right Edge 
  		{
  			paddleX = paddleX - paddleS;
  		}
  	}
  	
  	public void contactPaddle() //Bounce in the paddle, and go to until the bottom edge
  	{
  		if(y - h > paddleY - paddleH && x - w > paddleX - paddleW && x + w < paddleX + paddleW) 
  		{
  			speedY = -speedY;
  		}
  	}
  	
  	public void drawBricks() //Map Generator
  	{
  		int i;
  		int x_tmp, y_tmp;
  		int blocks_gone;
        for (i = 0; i < 20; i++) 
        {
             blocks[i] = 1;
        }
        blocks_gone = 1;
        for (i= 0; i < 20; i++) 
        {
        	x_tmp = i % 5 * 100 + 10;
        	y_tmp = 40 * (i/5) + 10; 
          
        	if (blocks [i] == 1) 
        	{ 
        		rect(x_tmp + 40, y_tmp + 10, 80, 20);
        		blocks_gone = 0;
        	}
          
        	if (x>(x_tmp+4) && x<(x_tmp + 76) && y > y_tmp && y < (y_tmp + 20) && blocks[i] == 1) 
        	{ 
        		blocks [i] = 0;
        		speedY = -speedY;
        		score = score + 5;
        	}
          
        	if (((x>(x_tmp - 5) && x<x_tmp) || (x>(x_tmp+80) && x<(x_tmp+85))) && y>y_tmp && y<(y_tmp+20) && blocks[i]==1)
        	{ 
        		blocks [i] = 0;
        		speedX = -speedX;
        		score = score + 5;
        	}
          
            if (((x>(x_tmp-1) && x<(x_tmp+5)) || (x>(x_tmp+75) && x<(x_tmp+81))) && y>y_tmp && y<(y_tmp+20) && blocks[i]==1) 
            { 
            	blocks [i] = 0;
            	speedX = -speedX;
            	speedY = -speedY;
            	score = score + 5;
            }
        }
  	}
  	
  	public void score() 
  	{
  		fill(255);
  		textSize(16);
  		textAlign(RIGHT);
  		text("Score", 80, 580);
		textAlign(LEFT);
  	  	text(score, 90, 580);
  	  	
  	  	textAlign(RIGHT);
  	  	text("Level", 650, 580);
  	  	textAlign(LEFT);
  	  	text(level, 660, 580);
  	}
  	
  	public void keyPressed() 
  	{
  		if (key == 'a' || key == 'A' || keyCode == LEFT) 
  		{
  			left = true;
  		}
  		if (key == 'd' || key == 'D'|| keyCode == RIGHT) 
  		{
  			right = true;
  		}
  		if (key == ' ') 
  		{
  			size (700, 600); //Window size
  			x = 350;
  		    y = 570;
  		    w = 20; //Ball size
  		    h = 20; //Ball size
  		    speedX = 3; //Ball Speed
  		    speedY = 3; //Ball Speed
  		    
  		    paddleX = 300; //Paddle's location
  		    paddleY = 580; //Paddle's location
  		    paddleW = 100; //Paddle's size
  		    paddleH = 30;  //Paddle's size
  		    paddleS = 8;   //Paddle's speed
  		    
  		    for (int i=0; i<20; i++) 
  			{
  			    blocks[i] = 1;
  			}
  		}
  	}
  	
  	public void keyReleased() 
  	{
  		if (key == 'a' || key == 'A' || keyCode == LEFT) 
  		{
  			left = false;
  		}
  		if (key == 'd' || key == 'D'|| keyCode == RIGHT) 
  		{
  			right = false;
  		}
  	}
	  
  public static void main(String[] args) 
  {
	  String[] appletArgs = new String[] {"Processing.Game"};
	  PApplet.main(appletArgs);
  }
}