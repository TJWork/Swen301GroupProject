package ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import UserInterface.KPSUserInterface;

public class MainWindow extends JFrame{
	
	
	public MainWindow(){
		
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args){
		//new MainWindow().setVisible(true);
		new KPSUserInterface();
	}
}
