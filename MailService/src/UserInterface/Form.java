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
public class Form extends JDialog implements ActionListener{

	private BufferedImage image;

	private JPanel panel;

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

	private ArrayList<Route> routes = XMLWorker.getAllRoutes();
	private ArrayList<Route> route = new ArrayList<Route>();
	private Route temp;


	public Form(){


		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);


		try {
			image = ImageIO.read(new File("Images/BG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		title = new JLabel("Form");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		price = new JLabel("<html><font face=Verdana size=5>Price: </font></html>");
		price.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		price.enableInputMethods(true);

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
					if(isDigit(weightField.getText()) == false) {
						weightLabel.setText("*Please input numbers");
					} else {weightLabel.setText("");}
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
					if(isDigit(volumeField.getText()) == false) {
						volumeLabel.setText("*Please input numbers");
					} else {volumeLabel.setText("");}
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
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(volume)
								.addComponent(weight))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(weightField, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addComponent(volumeField))
										.addGap(18, 18, 18)
										.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(weightLabel, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
												.addComponent(volumeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addContainerGap())
				);
		cardLayout.setVerticalGroup(
				cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(cardLayout.createSequentialGroup()
						.addGap(0, 0, 0)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(weight)
								.addComponent(weightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(weightLabel))
								.addGap(20, 20, 20)
								.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(volume)
										.addComponent(volumeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(volumeLabel))
										.addContainerGap(20, Short.MAX_VALUE))
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
				destinationField.removeAllItems();
				destinationField.setSelectedIndex(-1);
				route.clear();

				for(Route r:routes){
					if(r.getPriority().equals(priorityField.getSelectedItem().toString())){
						route.add(r);
					}

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
		ArrayList<String> city = XMLWorker.getCitiesFromCountry("New Zealand");

		// sort the city list in alphabetical order
		Collections.sort(city);

		originCityField = new JComboBox(city.toArray());
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
		originCityField.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				destinationField.removeAllItems();
				destinationField.setSelectedIndex(-1);
				for(Route r: route){
					if(originCityField.getSelectedItem().toString().equals(r.getOrigin())){
						destinationField.addItem(r.getDestination());
					}
				}

			}
		});
		originCityWarning = new JLabel();
		originCityWarning.setForeground(Color.RED);


		/*=========================== Destination Field ==================================*/

		destination = new JLabel("Destination Address");
		destination.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		destinationField = new JComboBox();
		destinationField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		destinationField.setBackground(Color.WHITE);
		destinationField.setSelectedIndex(-1);
		destinationField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) { }

			@Override
			public void focusLost(FocusEvent e) {
				if(destinationField.getSelectedIndex()==-1){
					destinationWarning.setText("*Please select a destination");
				} else {destinationWarning.setText("");}
			}
		});
		destinationWarning = new JLabel();
		destinationWarning.setForeground(Color.RED);



		destinationField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(destinationField.getSelectedIndex()!=-1){
					for(Route ro: route){
						if(		ro.getPriority().equals(priorityField.getSelectedItem().toString()) &&
								ro.getOrigin().equals(originCityField.getSelectedItem().toString() )  &&
								ro.getDestination().equals(destinationField.getSelectedItem().toString())){

							temp = ro;
							System.out.println(temp.toString());
						}
					}
				}
			}
		});




		/*=========================== Form Buttons ==================================*/

		CustomButton submit = new CustomButton("Submit_Normal", "Submit_Pressed", "Submit_Hover", "submit");
		submit.addActionListener(this);
		CustomButton cancel = new CustomButton("Cancel_Normal", "Cancel_Pressed", "Cancel_Hover", "cancel");
		cancel.addActionListener(this);

		/*=========================== Form Layout ==================================*/

		panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
			}
		};

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(25, Short.MAX_VALUE)
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
																								.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																												.addGroup(layout.createSequentialGroup()
																														.addComponent(destination)
																														.addGap(10, 10, 10)
																														.addComponent(destinationField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
																														.addGap(10, 10, 10)
																														.addComponent(destinationWarning, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
																														.addGroup(layout.createSequentialGroup()
																																.addGap(9, 9, 9)
																																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																																		.addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																		.addGroup(layout.createSequentialGroup()
																																				.addComponent(price, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
																																				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																				.addComponent(submit)
																																				.addGap(30, 30, 30)
																																				.addComponent(cancel)
																																				.addGap(37, 37, 37)))))
																																				.addGap(3, 3, 3))))
																																				.addContainerGap(25, Short.MAX_VALUE))
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
																										.addGroup(layout.createSequentialGroup()
																												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																														.addComponent(destination)
																														.addComponent(destinationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																														.addGap(18, 18, 18)
																														.addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																														.addGroup(layout.createSequentialGroup()
																																.addGap(3, 3, 3)
																																.addComponent(destinationWarning)))
																																.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																		.addComponent(price, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
																																		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																																				.addComponent(cancel)
																																				.addComponent(submit)))
																																				.addContainerGap())
				);



		price.addNotify();

		add(panel);
	}


	/**
	 *	Creating background image
	 */
	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	}


	public void allFieldsValue(){
		Route r;
		if(checkFields("Mail")==true){

		}


		price.setText("");
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
	 * Helper method to check if all the input fields are correct
	 */
	private boolean checkFields(String mailType){
		if(mailType.equals("Parcel") && (!customerField.getText().equals("")) && (isDigit(volumeField.getText()) == true) && (isDigit(weightField.getText()) == true) && (typeField.getValue().toString().equals("Parcel")) && destinationField.getSelectedIndex()>-1){
			return true;
		}
		else if(mailType.equals("Mail") && (!customerField.getText().equals("")) && (destinationField.getSelectedIndex()>-1) && (originCityField.getSelectedIndex()>-1) ){
			return true;
		} else {
			return false;
		}

	}

	private void notifyPrice(){
		if((!customerField.getText().equals("")) && (isDigit(volumeField.getText()) == true) && (isDigit(weightField.getText()) == true) && (typeField.getValue().toString().equals("Parcel")) && destinationField.getSelectedIndex()>-1){
			;
		}
	}


	/**
	 * ActionListener that responds to the button pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*=============================== Submit Button Event ==========================================*/
		if(e.getActionCommand().equals("submit")){
			if(checkFields("Parcel")==true || checkFields("Mail")== true){

				if(checkFields("Parcel")==true || checkFields("Mail")== true){
					if(typeField.getValue().toString().equals("Parcel")){

						Parcel p = new Parcel(KPSUserInterface.clock.getCurrentDate(),
								destinationField.getSelectedItem().toString().trim(),
								originCityField.getSelectedItem().toString().trim(),
								weightField.getText(),
								volumeField.getText(),
								0, (priorityField.getSelectedIndex()+1));

						int result = JOptionPane.showConfirmDialog(panel, "Confirm customer details?", "Confirm", JOptionPane.YES_NO_OPTION);;


						if(result == JOptionPane.YES_OPTION){
							ClerkGUI.dashBoard.addItem("Parcel", p.getData());
							XMLWorker.addMail(p);
							dispose();
						}

					}

					else if(typeField.getValue().toString().equals("Mail")){
						Mail m = new Mail(KPSUserInterface.clock.getCurrentDate(),
								destinationField.getSelectedItem().toString().trim(),
								originCityField.getSelectedItem().toString().trim(),
								0, (priorityField.getSelectedIndex()+1));

						int result = JOptionPane.showConfirmDialog(panel, "Confirm customer details?", "Confirm", JOptionPane.YES_NO_OPTION);;

						if(result == JOptionPane.YES_OPTION){
							ClerkGUI.dashBoard.addItem("Mail", m.getData());
							XMLWorker.addMail(m);
							dispose();
						}
					}

				}
			}
			else{
				if(customerField.getText().equals("")) {
					customerWarning.setText("*Please input customer name");
				}
				if(destinationField.getSelectedIndex()==-1) {
					destinationWarning.setText("*Please select a destination");
				}
				if(originCityField.getSelectedIndex()==-1) {
					originCityWarning.setText("*Please select origin");
				}
				if(priorityField.getSelectedIndex()==-1) {
					priorityWarning.setText("*Please select a priority");
				}
				if(typeField.getValue().equals("Parcel") && weightField.getText().equals("")) {
					weightLabel.setText("*Please input weight");
				}
				if(typeField.getValue().equals("Parcel") && volumeField.getText().equals("")) {
					volumeLabel.setText("*Please input numbers");
				}

			}

		}

		/*=============================== Cancel back to DashBoard ==========================================*/
		else if(e.getActionCommand().equals("cancel")){
			dispose();
		}



	}
}
