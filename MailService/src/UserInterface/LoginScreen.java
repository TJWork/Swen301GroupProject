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
			image = ImageIO.read(new File("Images/LoginBG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		title = new JLabel();
		title.setText(titlename);
		title.setFont(new Font("Comic Sans", Font.BOLD, 40));
		
		teamMembers = new JLabel();
		teamMembers.setText(members);
		
		cancel = new JButton();
		cancel = setButton("Cancel_Normal", "Cancel_Pressed", "Cancel_Hover", "cancel");
	
		signin = new JButton();
		signin = setButton("Signin_Normal", "Signin_Pressed", "Signin_Hover", "signin");
		
		usrName = new JLabel("Username");
		usrNameField = new JTextField();
		
		passwrd = new JLabel("Password");
		passwrdField = new JPasswordField();
		
		type = new JLabel("Login as");
		userType = new JSpinner(new SpinnerListModel(new String[] {"Clerk", "Manager"}));
		
		
		
		
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(signin)
                .addGap(30, 30, 30)
                .addComponent(cancel)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(teamMembers)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(usrName)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(type)
                                        .addComponent(passwrd)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usrNameField)
                                    .addComponent(passwrdField, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(userType))))))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teamMembers)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usrName)
                    .addComponent(usrNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwrd)
                    .addComponent(passwrdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(type))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(signin)
                    .addComponent(cancel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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
	
	public JButton setButton(String normalIcon, String pressedIcon, String hoverIcon, String command){
		final JButton button = new JButton();
		
		final ImageIcon pressed = new ImageIcon("Images/" + pressedIcon + ".png");
		final ImageIcon normal = new ImageIcon("Images/" + normalIcon + ".png");
		final ImageIcon hover = new ImageIcon("Images/" + hoverIcon + ".png");
		

		button.setIcon(normal);
		button.setContentAreaFilled(false);
		button.setPressedIcon(pressed);
		button.setBorder(null);
		button.setBackground(new Color(0,0,0,0));
		button.setActionCommand(command);
//		button.setPreferredSize(new Dimension(50, 50));
		
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(hover);
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		button.addActionListener(this);
		
		return button;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if("cancel".equals(e.getActionCommand())){
			actionCommand = "cancel";
		}
		else if("signin".equals(e.getActionCommand())){
			actionCommand = "signin";
		}
		System.out.println(actionCommand);
	}
	
	public String EventAction(){
		System.out.println(actionCommand);
		return actionCommand;
	}

}
