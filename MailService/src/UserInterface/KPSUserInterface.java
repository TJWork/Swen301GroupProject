package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class KPSUserInterface extends JFrame{
	private Form form;
	private JTabbedPane tab;
	private Time clock;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel login;

	public KPSUserInterface(){
		tab = new JTabbedPane();
		form = new Form();
		login = new LoginScreen();

		tab.add("Form" , form);
		tab.add("Login", login);

		setLayout(new BorderLayout());
		add(tab, BorderLayout.CENTER);
		add(topPanel(), BorderLayout.NORTH);
//		add(bottomPanel(), BorderLayout.SOUTH);
		
		setJMenuBar(menubar());
		setSize(600, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	
	public JPanel topPanel(){
		clock = new Time();
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(clock, BorderLayout.EAST);

		return topPanel;
	}
	
	public JMenuBar menubar(){
		JMenuBar menubar = new JMenuBar();
		
		JMenu menu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menu.add(exit);
		menubar.add(menu);
		return menubar;
	}

	public void confirmDataInput(){
		form.logEvent();
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
