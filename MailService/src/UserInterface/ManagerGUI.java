package UserInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ManagerGUI extends JPanel implements ActionListener{


	public ManagerGUI(){


	}
	public JPanel TopPanel(){
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 0));
		FlowLayout layout = new FlowLayout();
		topPanel.setLayout(layout);

		CustomButton dash = new CustomButton("DashBoard", "db");
		dash.addActionListener(this);
		CustomButton route = new CustomButton("Route", "route");
		route.addActionListener(this);
		CustomButton price = new CustomButton("Price", "price");
		price.addActionListener(this);
		CustomButton signout = new CustomButton("Signout", "signout");
		signout.addActionListener(this);

		topPanel.add(dash);
		layout.setHgap(30);
		topPanel.add(route);
		layout.setHgap(30);
		topPanel.add(price);
		layout.setHgap(30);
		topPanel.add(signout);



		return topPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
