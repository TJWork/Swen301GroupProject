package UserInterface;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class ClerkGUI extends JPanel implements ActionListener{
	
	private Form form;
	private PriceUI priceP;
	private RoutesUI routesP;
	private JPanel cardPanel;
	private DashBoard dashBoard;
	
	public ClerkGUI(){
		form = new Form();
		priceP = new PriceUI();
		routesP = new RoutesUI();
		dashBoard = new DashBoard();

		setLayout(new BorderLayout());
		
		add(TopPanel(), BorderLayout.NORTH);
		
		cardPanel = new JPanel(new CardLayout());
		
		cardPanel.add(dashBoard, "dashboardP");
		cardPanel.add(form, "form");
		cardPanel.add(priceP, "priceP");
		cardPanel.add(routesP, "routeP");
		
		
		add(cardPanel, BorderLayout.CENTER);

		
	}
	
	public JPanel TopPanel(){
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 0));
		FlowLayout layout = new FlowLayout();
		topPanel.setLayout(layout);
		
		CustomButton dash = new CustomButton("DashBoard", "db");
		dash.addActionListener(this);
		CustomButton mail = new CustomButton("Mail", "mail");
		mail.addActionListener(this);
		CustomButton route = new CustomButton("Route", "route");
		route.addActionListener(this);
		CustomButton price = new CustomButton("Price", "price");
		price.addActionListener(this);
		CustomButton signout = new CustomButton("Signout", "signout");
		signout.addActionListener(this);

		topPanel.add(dash);
		
		topPanel.add(mail);
//		layout.setHgap(50);
		topPanel.add(route);
//		layout.setHgap(50);
		topPanel.add(price);
//		layout.setHgap(50);
		topPanel.add(signout);
		
		

		return topPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout c = (CardLayout) cardPanel.getLayout();
		

		if("mail".equals(e.getActionCommand())){
			c.show(cardPanel, "form");
		}
		else if("db".equals(e.getActionCommand())){
			c.show(cardPanel, "dashboardP");
		}
		else if("route".equals(e.getActionCommand())){
			c.show(cardPanel, "routeP");
			
		}
		else if("price".equals(e.getActionCommand())){
			c.show(cardPanel, "priceP");
		}
		else if("signout".equals(e.getActionCommand())){
			setVisible(false);
			getParent().add(new LoginScreen());
		}
		
	}
	
}
