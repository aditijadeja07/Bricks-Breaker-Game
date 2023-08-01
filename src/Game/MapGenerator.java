package Game;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;


public class MapGenerator {
	
	//2d array
	public int map[][];
	public int brickwidth;
	public int brickheight;
	
	public MapGenerator(int row,int col){
		map=new int [row][col];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=1; // value added for detection of bricks
			}
		}
		
		brickwidth=540/col; 
		brickheight=150/row;
		
		
	}	
	
	
	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				if(map[i][j]>0) // if 0 then not add graphics brick has been broken
				{ 
					g.setColor(Color.white);
					g.fillRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);
					        //brick X position ,brick Y position ,brick width ,brick height
					
					g.setStroke(new BasicStroke(3)); //border of bricks
					g.setColor(Color.black);
					g.drawRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);
					
					
				}
			}
		}
	}
	
	public void setBrickValue(int value,int row,int col)
	{
		map[row][col]=value;
	}

}
