package Game;
import java.io.File;
import java.io.IOException;
public class filecreation
{
	
	public static void main(String args[])
	{
		File myfile=new File("C:\\Users\\ADITI\\eclipse-workspace\\Game\\src\\Game\\Gamemodule.txt");
		try {
			if(myfile.createNewFile())
			{
				System.out.println("WELCOME TO BRICKBREAKER GAME");
			}
			
			else
			{
				System.out.println("ERROR IN CREATING FILE!!");
			}
		} catch (IOException e) {
			System.out.println("ERROR...!!");
			
		}
			
			
	}

}

