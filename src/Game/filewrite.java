package Game;

import java.io.FileWriter;
import java.io.IOException;

public class filewrite {
	public static void main(String args[])
	{
		
		String data="Game is simple! You have to break the bricks through ball by given paddle. You have left and right key to move the paddle.if you fail to bounce the ball with paddle, ball will fell down and you will lose the game ! Wish you Good luck!";
		
		
		try {
			FileWriter output=new FileWriter("C:\\\\Users\\\\ADITI\\\\eclipse-workspace\\\\Game\\\\src\\\\Game\\\\Gamemodule.txt");
			output.write(data);
			System.out.println("Data written successfully!");
			output.close();
			
			
		} catch (IOException e) {
			System.out.println("ERROR...!!");
		}
	}

}
