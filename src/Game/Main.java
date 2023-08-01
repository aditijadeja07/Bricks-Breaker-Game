package Game;
//The javax.swing package provides classes for java swing API 
//JFrame,JButton, JTextField, JTextArea, JRadioButton, JCheckbox, JMenu, JColorChooser etc.
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args){
		//creating object of predefined class JFrame
		JFrame obj = new JFrame();
		
		//creating object of GamePlay class
		Gameplay gamePlay=new Gameplay();
		
		//creating frame 
        obj.setBounds(10,10,700,600);        //setting boundaries (x,y,width,height) 
        obj.setTitle("BRICKBREAKER GAME");  //setting title
        obj.setResizable(false);           //user can't maximize the frame
        obj.setVisible(true);              // has to be true so user can see it on screen
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hide the JFrame when the user closes the window
        obj.add(gamePlay);  //adding JPanel gamePlay on screen
        
	}

}
