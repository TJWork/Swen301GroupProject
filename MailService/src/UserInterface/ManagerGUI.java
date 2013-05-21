package UserInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ManagerGUI extends JPanel implements ActionListener{
	private PriceUI priceP;
	private RoutesUI routesP;
	private BusinessStats businessStats;
	public static JPanel cardPanel;
	public static DashBoard dashBoard;

	public ManagerGUI(){
		priceP = new PriceUI();
		routesP = new RoutesUI();
		dashBoard = new DashBoard();
		businessStats = new BusinessStats();

		setLayout(new BorderLayout());

		add(TopPanel(), BorderLayout.NORTH);

		cardPanel = new JPanel(new CardLayout());

		cardPanel.add(dashBoard, "dashboardP");
		cardPanel.add(businessStats, "businessStatP");
		cardPanel.add(priceP, "priceP");
		cardPanel.add(routesP, "routeP");

		add(cardPanel, BorderLayout.CENTER);

	}
	
	
	/**
	 * A customised JPanel for navigation between different components
	 * @return JPanel
	 */
	public JPanel TopPanel(){
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 0));
		BorderLayout bl = new BorderLayout();
		topPanel.setLayout(bl);
		
		JLabel label = new JLabel("  Manager");
		label.setFont(new Font("Verdana", Font.BOLD, 20));
		label.setForeground(Color.LIGHT_GRAY);

		JPanel buttonsPanel = new JPanel();
		FlowLayout layout = new FlowLayout();
		buttonsPanel.setLayout(layout);
		buttonsPanel.setBackground(new Color(0, 0, 0));
		
		CustomButton dash = new CustomButton("DashBoard", "db");
		dash.addActionListener(this);
		CustomButton bss = new CustomButton("Business Satistics", "bss");
		dash.addActionListener(this);
		CustomButton route = new CustomButton("Route", "route");
		route.addActionListener(this);
		CustomButton price = new CustomButton("Price", "price");
		price.addActionListener(this);
		CustomButton signout = new CustomButton("Sign Out", "signout");
		signout.addActionListener(this);

		buttonsPanel.add(dash);
		layout.setHgap(30);
		buttonsPanel.add(bss);
		layout.setHgap(30);
		buttonsPanel.add(route);
		layout.setHgap(30);
		buttonsPanel.add(price);
		layout.setHgap(30);
		buttonsPanel.add(signout);

		topPanel.add(label, BorderLayout.WEST);
		topPanel.add(buttonsPanel, BorderLayout.EAST);

		return topPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout c = (CardLayout) cardPanel.getLayout();

		if("db".equals(e.getActionCommand())){
			c.show(cardPanel, "dashboardP");
		}
		else if("bss".equals(e.getActionCommand())){
			c.show(cardPanel, "businessStatP");

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
