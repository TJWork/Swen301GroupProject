package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class KPSUserInterface extends JFrame{
	public static Time clock;
	private JPanel topPanel;
	private LoginScreen login;

	public KPSUserInterface(){
		login = new LoginScreen();
		
		setLayout(new BorderLayout());
		add(topPanel(), BorderLayout.NORTH);

		add(login);

		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public JPanel topPanel(){
		clock = new Time();
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(clock, BorderLayout.WEST);
		topPanel.setBackground(Color.BLACK);
		return topPanel;
	}
	

	/**
	 * Calculate the current system time and date
	 */
	class Time  extends JLabel implements Runnable{

		private String currentTime;
		public Time(){
			setForeground(Color.LIGHT_GRAY);
			new Thread(this).start();
		}

		@Override
		public void run() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			
			while(true) {
				Calendar cal = Calendar.getInstance();
				currentTime = dateFormat.format(cal.getTime());
				
				Date time = cal.getTime();
				setText( "  " + time.toString());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}

		public String getCurrentDate(){
			String[] str = currentTime.split("\\s+");		
			return(str[0]);
//			
		}
	}


}
