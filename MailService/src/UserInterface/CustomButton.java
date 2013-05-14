package UserInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CustomButton extends JButton{

	public CustomButton(String normalIcon, String pressedIcon, String hoverIcon, String command ){//, Component c){
		final ImageIcon pressed = new ImageIcon("Images/" + pressedIcon + ".png");
		final ImageIcon normal = new ImageIcon("Images/" + normalIcon + ".png");
		final ImageIcon hover = new ImageIcon("Images/" + hoverIcon + ".png");
		

		setIcon(normal);
		setContentAreaFilled(false);
		setPressedIcon(pressed);
		setBorder(null);
		setBackground(new Color(0,0,0,0));
		setActionCommand(command);

		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(normal);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setIcon(hover);
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

	} 
	
	public CustomButton(String text){
		setText(text);
		setForeground(Color.GRAY);
		setFont(new Font("", Font.PLAIN, 30));
		
		setContentAreaFilled(false);
		setBorder(null);
		setBackground(new Color(0,0,0,0));
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.GRAY);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		

	}
	
	
}
