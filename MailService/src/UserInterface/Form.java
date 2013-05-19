package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import file.XMLWorker;

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

	private JPanel card;


	public Form(){

		try {      
			image = ImageIO.read(new File("Images/BG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		customerName = new JLabel("Customer Name");
		customerName.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		customerField = new JTextField();
		customerField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		customerWarning = new JLabel();
		customerWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));

		/**/
		type = new JLabel("Mail Type");
		type.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		typeField = new JSpinner(new SpinnerListModel(new String[] {"Mail", "Parcel"}));
		typeField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		typeWarning = new JLabel();
		typeWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));

		priority = new JLabel("Priority");
		priority.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		priorityField = new JComboBox(new String[]{"International Air", "International Sea" , "Domestic Air", "Domestic Land"});
		priorityField.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getItem().toString().equals("International Sea") || e.getItem().toString().equals("International Air")){
					destinationField.removeAllItems();
					populateCountries();
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

		originCity = new JLabel("Origin Address");
		originCity.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originCityField = new JComboBox(XMLWorker.getCitiesFromCountry("New Zealand").toArray());
		originCityField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originCityField.setBackground(Color.WHITE);
		originCityWarning = new JLabel();
		originCityWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));



		destination = new JLabel("Destination Address");
		destination.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		destinationField = new JComboBox();
		populateCountries();
		destinationField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		destinationField.setBackground(Color.WHITE);
		destinationWarning = new JLabel();
		destinationWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));

		card = new JPanel();

		CustomButton submit = new CustomButton("Submit_Normal", "Submit_Pressed", "Submit_Hover", "submit");
		submit.addActionListener(this);
		CustomButton cancel = new CustomButton("Cancel_Normal", "Cancel_Pressed", "Cancel_Hover", "cancel");
		cancel.addActionListener(this);



		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);


		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(101, 101, 101)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(destination)
												.addComponent(originCity)
												.addComponent(priority)
												.addComponent(type)
												.addComponent(customerName))
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(customerField)
														.addComponent(typeField)
														.addComponent(priorityField, 200,200,200)
														.addComponent(originCityField, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(destinationField, 200,200,200))
														.addGap(10, 10, 10)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(typeWarning)
																.addComponent(priorityWarning)
																.addComponent(originCityWarning)
																.addComponent(destinationWarning)))
																.addGroup(layout.createSequentialGroup()
																		.addGap(419, 419, 419)
																		.addComponent(customerWarning)))
																		.addContainerGap(77, Short.MAX_VALUE))
																		.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																				.addGap(0, 0, Short.MAX_VALUE)
																				.addComponent(submit)
																				.addGap(29, 29, 29)
																				.addComponent(cancel)
																				.addGap(50, 50, 50))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(54, 54, 54)
						.addComponent(customerWarning)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(customerName)
								.addComponent(customerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(20, 20, 20)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(type)
										.addComponent(typeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(typeWarning))
										.addGap(20, 20, 20)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(priority)
												.addComponent(priorityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(priorityWarning))
												.addGap(20, 20, 20)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(originCity)
														.addComponent(originCityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(originCityWarning))
														.addGap(20, 20, 20)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(destination)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																		.addComponent(destinationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(destinationWarning)))
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
																		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																				.addComponent(submit)
																				.addComponent(cancel))
																				.addGap(43, 43, 43))
				);


		add(customerName);
		add(customerField);
		add(customerWarning);

		add(type);
		add(typeField);
		add(typeWarning);

		add(priority);
		add(priorityField);
		add(priorityWarning);

		add(originCity);
		add(originCityField);
		add(originCityWarning);

		add(destination);
		add(destinationField);
		add(destinationWarning);

		add(submit);
		add(cancel);

	}



	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	}

	private void populateCountries(){
		ArrayList<String> countries = XMLWorker.loadCountries();

		for(String s: countries){
			destinationField.addItem(s);
			ArrayList<String> cities = XMLWorker.getCitiesFromCountry(s);
			for(String c: cities){
				destinationField.addItem("    " + c);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("submit")){
			System.out.println(e.getActionCommand());
			if(customerField.getText().equals("")){
				customerWarning.setText("Plese input customer name");
			}
			
			
			
		}
		else if(e.getActionCommand().equals("cancel")){
			CardLayout c = (CardLayout) ClerkGUI.cardPanel.getLayout();
			c.show(ClerkGUI.cardPanel, "dashboardP");
		}

	}

}
