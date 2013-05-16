package UserInterface;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class ClerkGUI extends JPanel{
	
	private Form form;
	
	public ClerkGUI(){
		
		
		setBackground(Color.WHITE);
		

		
		add(TopPanel(), BorderLayout.NORTH);

	}
	
	public JPanel TopPanel(){
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 0));
		
		CustomButton mail = new CustomButton("Mail");
		CustomButton route = new CustomButton("Route");
		CustomButton price = new CustomButton("Price");

		topPanel.add(mail);
		topPanel.add(route);
		topPanel.add(price);
		

		return topPanel;
	}
	
	
	
	

}
