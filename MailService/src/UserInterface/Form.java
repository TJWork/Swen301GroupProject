package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import service.*;
import file.XMLWorker;

/**
 * @author Sam
 * This is a form for users to input customer details. 
 * The required field for mails are the customer's name, origin address, destination and type of transport.
 * More fields such as volume and weight are required for parcels sending.
 */
public class Form extends JPanel implements ActionListener{

	private BufferedImage image;

	private JLabel customerName;
	private JTextField customerField;
	private JLabel customerWarning;

	private JLabel type;
	private JSpinner typeField;
	private JLabel typeWarning;

	private JLabel priority;
	private JComboBox priorityField;
	private JLabel priorityWarning;

	private JLabel originCity;
	private JComboBox originCityField;
	private JLabel originCityWarning;

	private JLabel destination;
	private JComboBox destinationField;
	private JLabel destinationWarning;

	private JPanel cardPanel;
	private JLabel weight;
	private JLabel weightLabel;
	private JTextField weightField;
	private JLabel volume;
	private JTextField volumeField;
	private JLabel volumeLabel;

	private JLabel price;
	private JLabel title;

	public Form(){

		try {
			image = ImageIO.read(new File("Images/BG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		title = new JLabel("Form");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		price = new JLabel("<html><font face=Verdana size=5>Price: </font></html>");
		price.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		price.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
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
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});


		/*=========================== Parcel Mail Field ==================================*/

		cardPanel = new JPanel(new CardLayout());
		cardPanel.setBackground(Color.WHITE);
		weight = new JLabel("Weight (gram)");
		weight.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		weightField = new JTextField();
		weightField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		weightField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(weightField.getText().equals("") ){
					weightLabel.setText("*Please input weight");
				}

				else {
					if(isDigit(weightField.getText()) == false)
						weightLabel.setText("*Please input numbers");
					else {weightLabel.setText("");}
				}
			}

			@Override
			public void focusGained(FocusEvent e) { }
		});
		weightLabel = new JLabel();
		weightLabel.setForeground(Color.RED);


		volume = new JLabel("Volume (cm3)");
		volume.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		volumeField = new JTextField();
		volumeField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		volumeField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(volumeField.getText().equals("")){
					volumeLabel.setText("*Please input volume");
				}
				else {
					if(isDigit(volumeField.getText()) == false)
						volumeLabel.setText("*Please input numbers");
					else {volumeLabel.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});
		volumeLabel = new JLabel();
		volumeLabel.setForeground(Color.RED);

		JPanel card1 = new JPanel();
		card1.setBackground(Color.WHITE);
		JPanel card2 = new JPanel();
		card2.setBackground(Color.WHITE);

		card1.add(weight);
		card1.add(weightField);
		card1.add(volume);
		card1.add(volumeField);

		cardPanel.add(card2, "mail");
		cardPanel.add(card1,"parcel");


		GroupLayout cardLayout = new GroupLayout(card1);
		card1.setLayout(cardLayout);
		cardLayout.setHorizontalGroup(
				cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(cardLayout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(volume)
								.addComponent(weight))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(weightField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addComponent(volumeField))
										.addGap(40, 40, 40)
										.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(weightLabel)
												.addComponent(volumeLabel))
												.addContainerGap(50, Short.MAX_VALUE))
				);
		cardLayout.setVerticalGroup(
				cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(cardLayout.createSequentialGroup()
						.addGap(20, 20, 20)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(weight)
								.addComponent(weightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(weightLabel))
								.addGap(20, 20, 20)
								.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(volume)
										.addComponent(volumeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(volumeLabel))
										.addContainerGap(40, Short.MAX_VALUE))
				);


		/*=========================== Customer Field ==================================*/

		customerName = new JLabel("Customer Name");
		customerName.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		customerField = new JTextField();

		customerField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		customerField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(customerField.getText().equals("")){
					customerWarning.setText("*Plese input customer name");
				} else {customerWarning.setText("");}
			}
			@Override
			public void focusGained(FocusEvent e) {	}
		});
		customerWarning = new JLabel();
		customerWarning.setForeground(Color.RED);



		/*=========================== Mail Type Field ==================================*/

		type = new JLabel("Mail Type");
		type.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		typeField = new JSpinner(new SpinnerListModel(new String[] {"Mail", "Parcel"}));
		typeField.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				CardLayout c = (CardLayout) cardPanel.getLayout();
				if(typeField.getValue().toString().equals("Mail")){
					c.show(cardPanel, "mail");
				}
				else{
					c.show(cardPanel, "parcel");
				}
			}
		});
		typeField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		typeWarning = new JLabel();
		typeWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));


		/*=========================== Mail Priority Field ==================================*/

		priority = new JLabel("Priority");
		priority.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		priorityField = new JComboBox(new String[]{"International Air", "International Sea" , "Domestic Air", "Domestic Land"});
		priorityField.setSelectedIndex(-1);
		priorityField.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString().equals("International Sea") || e.getItem().toString().equals("International Air")){
					//					destinationField.removeAllItems();
					//					populateCountries();
					destinationField.setSelectedIndex(-1);
				}
				else{
					destinationField.removeAllItems();
					ArrayList<String> city = XMLWorker.getCitiesFromCountry("New Zealand");

					for(String s: city)
						destinationField.addItem(s);
					destinationField.setSelectedIndex(-1);
				}
			}
		});
		priorityField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {	}

			@Override
			public void focusLost(FocusEvent e) {
				if(priorityField.getSelectedIndex()==-1){
					priorityWarning.setText("*Please select a priority");
				} else {priorityWarning.setText("");}
			}
		});
		priorityField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		priorityField.setBackground(Color.WHITE);
		priorityWarning = new JLabel();
		priorityWarning.setForeground(Color.RED);


		/*=========================== Origin City Field ==================================*/

		originCity = new JLabel("Origin Address");
		originCity.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originCityField = new JComboBox(XMLWorker.getCitiesFromCountry("New Zealand").toArray());
		originCityField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originCityField.setBackground(Color.WHITE);
		originCityField.setSelectedIndex(-1);
		originCityField.addFocusListener(new FocusListener() {			
			@Override
			public void focusLost(FocusEvent e) {
				if(originCityField.getSelectedIndex()==-1) {
					originCityWarning.setText("*Please select origin");
				} else {originCityWarning.setText("");}
			}

			@Override
			public void focusGained(FocusEvent e) { }
		});
		originCityWarning = new JLabel();
		originCityWarning.setForeground(Color.RED);


		/*=========================== Destination Field ==================================*/

		destination = new JLabel("Destination Address");
		destination.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		destinationField = new JComboBox();
		populateCountries();
		destinationField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		destinationField.setBackground(Color.WHITE);
		destinationField.setSelectedIndex(-1);
		destinationField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {	}

			@Override
			public void focusLost(FocusEvent e) {
				if(destinationField.getSelectedIndex()==-1){
					destinationWarning.setText("*Please select a destination");
				} else {destinationWarning.setText("");}
			}
		});
		destinationWarning = new JLabel();
		destinationWarning.setForeground(Color.RED);


		/*=========================== Form Buttons ==================================*/

		CustomButton submit = new CustomButton("Submit_Normal", "Submit_Pressed", "Submit_Hover", "submit");
		submit.addActionListener(this);
		CustomButton cancel = new CustomButton("Cancel_Normal", "Cancel_Pressed", "Cancel_Hover", "cancel");
		cancel.addActionListener(this);

		/*=========================== Form Layout ==================================*/

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(62, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(title)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(customerName)
												.addGap(10, 10, 10)
												.addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(10, 10, 10)
												.addComponent(customerWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(type)
														.addGap(10, 10, 10)
														.addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(10, 10, 10)
														.addComponent(typeWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(priority)
																				.addGap(10, 10, 10)
																				.addComponent(priorityField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addGroup(layout.createSequentialGroup()
																						.addComponent(originCity)
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																						.addComponent(originCityField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addGap(10, 10, 10)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																								.addComponent(originCityWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(priorityWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
																								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addGroup(layout.createSequentialGroup()
																												.addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																												.addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGroup(layout.createSequentialGroup()
																														.addComponent(destination)
																														.addGap(10, 10, 10)
																														.addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addGap(10, 10, 10)
																														.addComponent(destinationWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
																														.addContainerGap(62, Short.MAX_VALUE))
																														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																																.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																.addComponent(submit)
																																.addGap(30, 30, 30)
																																.addComponent(cancel)
																																.addGap(60, 60, 60))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(45, 45, 45)
						.addComponent(title)
						.addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(3, 3, 3)
										.addComponent(customerName))
										.addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(customerWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(20, 20, 20)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup()
														.addGap(3, 3, 3)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(type)
																.addComponent(typeWarning))))
																.addGap(20, 20, 20)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(priorityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGroup(layout.createSequentialGroup()
																				.addGap(3, 3, 3)
																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(priority)
																						.addComponent(priorityWarning))))
																						.addGap(20, 20, 20)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																								.addComponent(originCityWarning)
																								.addComponent(originCityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(originCity))
																								.addGap(20, 20, 20)
																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(destination)
																										.addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGroup(layout.createSequentialGroup()
																												.addGap(3, 3, 3)
																												.addComponent(destinationWarning)))
																												.addGap(18, 18, 18)
																												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(cancel)
																																.addComponent(submit))
																																.addGap(19, 19, 19))
				);
	}


	/**
	 *	Creating background image
	 */
	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	}


	/**
	 * Helper method to populate the JComboBox for countries
	 */
	private void populateCountries(){


		//				ArrayList<String> countries = XMLWorker.loadCountries();

		//				for(String s: countries){
		//					destinationField.addItem(s);
		//					ArrayList<String> cities = XMLWorker.getCitiesFromCountry("New Zealand");
		//					for(String c: cities){
		//						destinationField.addItem("    " + c);
		//					}

		//				}
	}

	/**
	 * Helper method to check if the field the that requires number is digit.
	 */
	private boolean isDigit( String input )  {
		try {
			Double.parseDouble(input) ;
			return true;
		}
		catch(NumberFormatException e ) {  return false;  }
	}


	/**
	 * ActionListener that responds to the button pressed  	
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {

		CardLayout c = (CardLayout) ClerkGUI.cardPanel.getLayout();

		/*=============================== Submit Button Event ==========================================*/

		if(e.getActionCommand().equals("submit")){
			/*Parcel Event*/
			if(typeField.getValue().toString().equals("Parcel")){

				if((!customerField.getText().equals("")) && (isDigit(volumeField.getText()) == true) && (isDigit(weightField.getText()) == true) && (typeField.getValue().toString().equals("Parcel")) && destinationField.getSelectedIndex()>-1){
					Parcel p = new Parcel(KPSUserInterface.clock.getCurrentDate(), destinationField.getSelectedItem().toString().trim(), originCityField.getSelectedItem().toString().trim(),
							weightField.getText().trim(), volumeField.getText().trim(), (priorityField.getSelectedIndex() +1));

					XMLWorker.addMail(p);
					ClerkGUI.dashBoard.addItem("Parcel", p.getData());
				}
				else{
					if(customerField.getText().equals("")) customerWarning.setText("*Plese input customer name");
					if(destinationField.getSelectedIndex()==-1) destinationWarning.setText("*Please select a destination");
					if(originCityField.getSelectedIndex()==-1) originCityWarning.setText("*Please select origin");
					if(priorityField.getSelectedIndex()==-1) priorityWarning.setText("*Please select a priority");
					if(weightField.getText().equals("")) weightLabel.setText("*Please input weight");
					if(volumeField.getText().equals("")) volumeLabel.setText("*Please input numbers");
				}
			}

			/*Mail Event*/
			else if(typeField.getValue().toString().equals("Mail")){
				if( (!customerField.getText().equals("")) && (destinationField.getSelectedIndex()>-1) && (originCityField.getSelectedIndex()>-1) ){
					Mail m = new Mail(KPSUserInterface.clock.getCurrentDate(), destinationField.getSelectedItem().toString().trim(), originCityField.getSelectedItem().toString().trim(),
							(priorityField.getSelectedIndex()+1));

					XMLWorker.addMail(m);
					ClerkGUI.dashBoard.addItem("Mail", m.getData());
					price.setText("<html><font face=Verdana size=5>Price: </font>\t\t <font size=6>" + m.getCost() + "</font></html>" );
				}
				else{
					if(customerField.getText().equals("")) customerWarning.setText("*Plese input customer name");
					if(destinationField.getSelectedIndex()==-1) destinationWarning.setText("*Please select a destination");
					if(originCityField.getSelectedIndex()==-1) originCityWarning.setText("*Please select origin");
					if(priorityField.getSelectedIndex()==-1) priorityWarning.setText("*Please select a priority");
				}
			}
		}

		/*=============================== Cancel back to DashBoard ==========================================*/
		else if(e.getActionCommand().equals("cancel")){

			c.show(ClerkGUI.cardPanel, "dashboardP");
			repaint();
		}



	}
}
