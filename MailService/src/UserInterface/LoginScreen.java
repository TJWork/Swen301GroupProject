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
		
		cancel = new JButton();
		cancel = setButton("Cancel_Normal.png", "Cancel_Pressed.png", "Cancel_Hover.png", "cancel");
	
		
		add(title);
		add(teamMembers);
		add(cancel);
		
		title.setLocation(100, 100);
		
	}

	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	} 
	
	public JButton setButton(String normalIcon, String pressedIcon, String hoverIcon, String command){
		final JButton button = new JButton();
		
		final ImageIcon pressed = new ImageIcon("Images/" + pressedIcon);
		final ImageIcon normal = new ImageIcon("Images/" + normalIcon);
		final ImageIcon hover = new ImageIcon("Images/" + hoverIcon);
		

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
		
	}
	
	public String EventAction(){
		return actionCommand;
	}

}
