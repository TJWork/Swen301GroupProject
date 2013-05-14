package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class KPSUserInterface extends JFrame implements ComponentListener{
	private Time clock;
	private JPanel topPanel;
	private LoginScreen login;

	public KPSUserInterface(){
		login = new LoginScreen();
		
		setLayout(new BorderLayout());
		add(topPanel(), BorderLayout.NORTH);

		add(login).addComponentListener(this);
		
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}

	

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getComponent().getClass().getName());
		
	}

	
	
	
	public JPanel topPanel(){
		clock = new Time();
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(clock, BorderLayout.EAST);

		return topPanel;
	}
	
	public void confirmDataInput(){
		System.out.println(clock.getCurrentTime());
		System.out.println(clock.getCurrentDay());
	}
	
	/**
	 * Calculate the current system time and date
	 */
	private class Time  extends JLabel implements Runnable{

		private String currentTime;
		public Time(){
			new Thread(this).start();
		}

		@Override
		public void run() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			
			while(true) {
				Calendar cal = Calendar.getInstance();
				currentTime = dateFormat.format(cal.getTime());
				
				Date time = cal.getTime();
				setText( time.toString());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
			
			
		}
		
		public String getCurrentTime(){
			return (currentTime);
		}
		
		public String getCurrentDay(){
			Calendar cal = Calendar.getInstance();
			Date time = cal.getTime();
			String[] str = time.toString().split("\\s+");
			
			return(str[0]+"day");
			
		}
	}


}
