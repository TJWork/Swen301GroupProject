package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class LoginScreen extends JPanel implements ActionListener{

	private BufferedImage image;
	private JLabel title;
	private JLabel teamMembers;
	private JButton signin;
	private JButton cancel;

	private String titlename = "Welcome to KPSmart";
	private String members= "Ishan, Joshi, Rory, Samantha, Tony";
	private String actionCommand;

	private JLabel usrName;
	private JLabel passwrd;
	private JLabel type;
	private JSpinner userType;
	private JTextField usrNameField;
	private JPasswordField passwrdField;

	public LoginScreen(){
		try {      
			image = ImageIO.read(new File("Images/BG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		title = new JLabel();
		title.setText(titlename);
		title.setFont(new Font("Verdana", Font.BOLD, 60));

		teamMembers = new JLabel();
		teamMembers.setText(members);
		teamMembers.setFont(new Font("Verdana", Font.ITALIC, 20));

		cancel = new CustomButton("Cancel_Normal", "Cancel_Pressed", "Cancel_Hover", "cancel");
		cancel.addActionListener(this);

		signin = new CustomButton("Confirm_Normal", "Confirm_Pressed", "Confirm_Hover", "signin");
		signin.addActionListener(this);

		usrName = new JLabel("Username");
		usrName.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		usrNameField = new JTextField();
		usrNameField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));

		passwrd = new JLabel("Password");
		passwrd.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		passwrdField = new JPasswordField();
		passwrdField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));

		type = new JLabel("Login as");
		type.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		userType = new JSpinner(new SpinnerListModel(new String[] {"Clerk", "Manager"}));
		userType.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));



		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);



		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(signin)
						.addGap(34, 34, 34)
						.addComponent(cancel)
						.addGap(28, 28, 28))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(150, 150, 150)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addGroup(layout.createSequentialGroup()
																.addComponent(type, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(userType, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(usrName, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																		.addGap(18, 18, 18)
																		.addComponent(usrNameField, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(passwrd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																				.addGap(18, 18, 18)
																				.addComponent(passwrdField, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))))
																				.addGroup(layout.createSequentialGroup()
																						.addGap(95, 95, 95)
																						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																								.addComponent(teamMembers)
																								.addComponent(title))))
																								.addContainerGap(120, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(70, 70, 70)
						.addComponent(title)
						.addGap(18, 18, 18)
						.addComponent(teamMembers)
						.addGap(26, 26, 26)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(usrNameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(usrName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addGap(38, 38, 38)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(passwrdField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addComponent(passwrd, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
										.addGap(34, 34, 34)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(userType, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addComponent(type, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(signin)
														.addComponent(cancel))
														.addGap(31, 31, 31))
				);






		add(title);
		add(teamMembers);

		add(usrName);
		add(usrNameField);
		add(passwrd);
		add(passwrdField);
		add(userType);

		add(signin);
		add(cancel);



	}

	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	} 



	@Override
	public void actionPerformed(ActionEvent e) {
		if("cancel".equals(e.getActionCommand())){			
			System.exit(0);
		}
		else if("signin".equals(e.getActionCommand())){
			setVisible(false);

			if(userType.getValue().toString().equals("Clerk")){
				getParent().add(new ClerkGUI());
			}
			else if(userType.getValue().toString().equals("Manager")){
				getParent().add(new ManagerGUI());
			}
		}
		System.out.println(e.getActionCommand().toString());
	}

}
