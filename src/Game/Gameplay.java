package Game;
//The Abstract Window Toolkit (AWT) supports Graphical User Interface (GUI) programming.
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;

// two interfaces, keyListener --> moving paddle         
//               actionListener --> moving ball

public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	private boolean play=false;
	private int score=0;   //initial score and total bricks
	private int totalBricks=21; //total bricks 3*7=21
	
	private Timer timer;   // declaring timer class object
	private int delay=3;
	
	private int playerX=380; // initial dimensions of paddle

	private int ballposX=120;  // initial dimensions of ball
	private int ballposY=350;  
	
	private int ballXdir=-1;  // initial direction of ball 
	private int ballYdir=-2;
	
	private MapGenerator map; 
	
	//constructor
	public Gameplay()
	
	{
		map=new MapGenerator(3,7);
		//this is keyListener as argument
		addKeyListener(this); //predefined keyListener method to handle key events
		setFocusable(true);   //must be true to access keyboard
		setFocusTraversalKeysEnabled(false); //receive KeyEvents for focus traversal keys

		//using timer class for animation effect
	    timer = new Timer(delay,this); //delay ->time in milliseconds for firing each event
		timer.start();

	}

//paint method for panel creating and graphics
	public void paint(Graphics g)  //Graphics g -> the abstract base class for all graphics contexts 
	{
		//background
		g.setColor(Color.pink);
		g.fillRect(1,1,692,592); //x,y,width,height
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.black);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		
		//scores
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);

		//paddles
		g.setColor(Color.white);
		g.fillRect(playerX,550,100,8);
		
		//balls
		g.setColor(Color.white);
		g.fillOval(ballposX,ballposY,20,20);
	
		//user has broken all the bricks then he/she will win 
		//total bricks will be 0, printing message won and re-start
		if(totalBricks<=0)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOU WON!! ",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart the game ",230,350);
			
		}
		
		// if ball will get out of Y dimension --> user will lose the game 
		//displaying game over and re-starting msg
		if(ballposY>570)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("GAME OVER!!",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart the game ",230,350);
			
			
			
		}
		
		//Disposes/freeing of this graphics context
		g.dispose();
					
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		repaint();
		if(play)
		{
			//using predefined intersect method to check intersection 
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir=-ballYdir;
			}

			//using map object to access another class variable and methods

		B:	for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0){
						
						//determining position of brick
						int brickX=j*map.brickwidth+80;
						int brickY=i*map.brickheight+50;
						int brickwidth=map.brickwidth;
						int brickHeight=map.brickheight;
						
						Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickHeight);
						Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickRect=rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,i,j); //setting 0 to that brick position row i,row j-->making it disappear
							totalBricks--;
							score+=10;
							
							//corner cases
							if(ballposX+19 <= brickRect.x || ballposX+1 >=brickRect.x + brickRect.width) {
								ballXdir=-ballXdir;
								}
							
							else
							{
								ballYdir=-ballYdir;
							}
							
							break B;
						}
						
					}
			
				}
			}
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
		
// bouncing back if ball cross origin	
			//left border
		if(ballposX<0)
		{
			ballXdir=-ballXdir;
			
		}
		//top border
		if(ballposY<0)
		{
			ballYdir=-ballYdir;
			
		}
		
// bouncing back if ball cross x coordinate limit 
		//right border 			
		if(ballposX>670)
		{
			ballXdir=-ballXdir;
			
		}
	
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) 
			//matching code of right key
		{  
			//set paddle position if cross right x limit
			if(playerX>=600)
			{
				playerX=600;
			}
			else
			{
				moveRight();
			}
		}
			
			
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			//matching code of left key
			if(playerX<10)
			{
				//set paddle position if cross left x limit
				playerX=10;
			}
			else
			{
				moveLeft();
			}
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			//using flag play and re-starting game
			if(!play)
			{
				play=true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-2;
				playerX=310;
				score=0;
				totalBricks=21;
				map=new MapGenerator(3,7);
				
				// to call paint method as soon as possible.
				repaint();
				
			}
		}
		
	}
	
	public void moveRight(){
		play=true;
		playerX+=20;
		
	}
	
	public void moveLeft(){
		play=true;
		playerX-=20;
		
	}


	

}

