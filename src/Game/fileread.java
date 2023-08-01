package Game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileread {
	public static void main(String args[])
	{
		
		char[] data=new char[500];
		
		try {
			FileReader input=new FileReader("C:\\\\Users\\\\ADITI\\\\eclipse-workspace\\\\Game\\\\src\\\\Game\\\\Gamemodule.txt");
			input.read(data);
			System.out.println("HOW TO PLAY? ");
			System.out.println(data);
			input.close();
			
		} catch (IOException e) {
			System.out.println("ERROR...!!");
			
		}
		
		
	}

}
