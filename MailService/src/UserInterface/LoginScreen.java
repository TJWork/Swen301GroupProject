package UserInterface;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class LoginScreen extends JPanel{

	private BufferedImage image;
	private JLabel title;
	private JLabel teamMembers;
	private String titlename = "Welcome to KPSmart";
	private String members= "Ishan, Joshi, Rory, Samantha, Tony";

	
	public LoginScreen(){
		try {      
			
			image = ImageIO.read(new File("Images/LoginBG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		title = new JLabel();
		title.setText(titlename);
		title.setFont(new Font("Comic Sans", Font.BOLD, 36));
		
		
		
		teamMembers = new JLabel();
		teamMembers.setText(members);
		
		add(title);
		add(teamMembers);
		
		title.setLocation(100, 100);
		
	}

	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	} 

}
