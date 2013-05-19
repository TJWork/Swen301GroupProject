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
 *
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
		price = new JLabel("Price: ");
		price.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));


		/*=========================== Parcel Mail Field ==================================*/

		cardPanel = new JPanel(new CardLayout());
		cardPanel.setBackground(Color.WHITE);
		weight = new JLabel("Weight (gram)");
		weight.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		weightField = new JTextField();
		weightField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		weightLabel = new JLabel();
		weightLabel.setForeground(Color.RED);
		volume = new JLabel("Volume (cm3)");
		volume.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		volumeField = new JTextField();
		volumeField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
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
		priorityField.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getItem().toString().equals("International Sea") || e.getItem().toString().equals("International Air")){
//					destinationField.removeAllItems();
//					populateCountries();
				}
				else{
					destinationField.removeAllItems();
					ArrayList<String> city = XMLWorker.getCitiesFromCountry("New Zealand");

					for(String s: city)
						destinationField.addItem(s);
				}
			}
		});
		priorityField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		priorityField.setBackground(Color.WHITE);
		priorityWarning = new JLabel();
		priorityWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));


		/*=========================== Origin City Field ==================================*/

		originCity = new JLabel("Origin Address");
		originCity.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originCityField = new JComboBox(XMLWorker.getCitiesFromCountry("New Zealand").toArray());
		originCityField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originCityField.setBackground(Color.WHITE);
		originCityField.setSelectedIndex(-1);
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
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(80, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(customerName)
                                .addGap(10, 10, 10)
                                .addComponent(customerField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(customerWarning, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(type)
                                .addGap(10, 10, 10)
                                .addComponent(typeField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(typeWarning, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(priority)
                                        .addGap(10, 10, 10)
                                        .addComponent(priorityField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(originCity)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(originCityField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(originCityWarning, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(priorityWarning, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(price, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(submit)
                                    .addGap(30, 30, 30)
                                    .addComponent(cancel))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(destination)
                                        .addGap(10, 10, 10)
                                        .addComponent(destinationField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(destinationWarning, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(80, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(title)
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(customerName))
                        .addComponent(customerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(customerWarning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(typeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(type)
                                .addComponent(typeWarning))))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(priorityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(priority)
                                .addComponent(priorityWarning))))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(originCityWarning)
                        .addComponent(originCityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(originCity))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(destination)
                        .addComponent(destinationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(destinationWarning)))
                    .addGap(18, 18, 18)
                    .addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(price, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(cancel)
                            .addComponent(submit)))
                    .addContainerGap(80, Short.MAX_VALUE))
            );
	}


	/**
	 *	Creating background image 
	 */
	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	}

	
	/**
	 * Helper method to populate the JComboBox
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
	 * Helper method to check if the field taht requires digit is correct
	 */
	private boolean isDigit( String input )  {  
		try {  
			Double.parseDouble(input) ;
			return true;  
		}  
		catch(NumberFormatException e ) {  return false;  }  
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*=============================== Submit Button Event ==========================================*/

		if(e.getActionCommand().equals("submit")){
			if(customerField.getText().equals("")){
				customerWarning.setText("*Plese input customer name");
			} else {customerWarning.setText("");}

			if(destinationField.getSelectedIndex()==-1){
				destinationWarning.setText("*Please select a destination");
			} else {destinationWarning.setText("");}

			if(originCityField.getSelectedIndex()==-1) {
				originCityWarning.setText("*Please select origin");
			} else {originCityWarning.setText("");} 

			/*Parcel Event*/
			if(typeField.getValue().toString().equals("Parcel")){

				if(weightField.getText().equals("") ){
					weightLabel.setText("*Please input weight");
				}

				else {
					if(isDigit(weightField.getText()) == false)
						weightLabel.setText("*Please input numbers");
					else {weightLabel.setText("");}
				} 

				if(volumeField.getText().equals("")){
					volumeLabel.setText("*Please input volume");
				}
				else {
					if(isDigit(volumeField.getText()) == false)
						volumeLabel.setText("*Please input numbers");
					else {volumeLabel.setText("");}
				}


				if((!customerField.getText().equals("")) && (isDigit(volumeField.getText()) == true) && (isDigit(weightField.getText()) == true) && (typeField.getValue().toString().equals("Parcel")) && destinationField.getSelectedIndex()>-1){
					Parcel p = new Parcel(KPSUserInterface.clock.getCurrentDate(), destinationField.getSelectedItem().toString().trim(), originCityField.getSelectedItem().toString().trim(),
							weightField.getText().trim(), volumeField.getText().trim(), (priorityField.getSelectedIndex() +1));


					System.out.println(p.toString());
					XMLWorker.addMail(p);
				}
			}

			/*Mail Event*/
			else if(typeField.getValue().toString().equals("Mail")){
				if( (!customerField.getText().equals("")) && (destinationField.getSelectedIndex()>-1) && (originCityField.getSelectedIndex()>-1) ){
					Mail m = new Mail(KPSUserInterface.clock.getCurrentDate(), destinationField.getSelectedItem().toString().trim(), originCityField.getSelectedItem().toString().trim(),
							(priorityField.getSelectedIndex()+1));
					
					System.out.println(m.toString());
					XMLWorker.addMail(m);
					
				}
			}
		}
		
		/*=============================== Cancel back to DashBoard ==========================================*/
		else if(e.getActionCommand().equals("cancel")){
			CardLayout c = (CardLayout) ClerkGUI.cardPanel.getLayout();
			c.show(ClerkGUI.cardPanel, "dashboardP");
		}

		getParent().validate();
		
	}
}
