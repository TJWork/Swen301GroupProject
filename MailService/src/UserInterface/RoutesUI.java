package UserInterface;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import file.XMLWorker;


public class RoutesUI extends JPanel {
	
	private JLabel title;
	private JButton addRoutes;
	private JScrollPane tableScrollPane;
	private JTable routesTable;
	
	public RoutesUI(){

		setBackground(Color.WHITE);
		title = new JLabel("Routes");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		addRoutes = new JButton("Add New Routes");
		addRoutes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRoutes();
			}
		});
		
		
		
		
		
		String[] columnNames = new String[]{"Origin", "Destination", "Priority", "Price per gram", "Price per cm3" };

		
		add(addRoutes);
	}
	
	
	/**
	 * A dialog box that prompt users to enter desired routes with the given details
	 */
	public void addRoutes(){
		final JDialog dialog = new JDialog();
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel companyName = new JLabel("Carrier Name");
		JTextField companyField = new JTextField();
		JLabel companyWarning = new JLabel();
		
		JLabel origin = new JLabel("Origin");
		final JLabel originWarning = new JLabel();
		originWarning.setForeground(Color.RED);
		final JComboBox originField = new JComboBox(XMLWorker.getCitiesFromCountry("New Zealand").toArray());;
		originField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		originField.setBackground(Color.WHITE);
		originField.setSelectedIndex(-1);
		originField.addFocusListener(new FocusListener() {			
			@Override
			public void focusLost(FocusEvent e) {
				if(originField.getSelectedIndex()==-1) {
					originWarning.setText("*Please select origin");
				} else {originWarning.setText("");}
			}

			@Override
			public void focusGained(FocusEvent e) { }
		});

		
		JLabel destination = new JLabel("Destination");
		final JComboBox destinationField = new JComboBox();
		JLabel destinationWaning = new JLabel();
		
		JLabel priority = new JLabel("Priority");
		final JLabel priorityWarning = new JLabel();
		priorityWarning.setForeground(Color.RED);
		final JComboBox priorityField = new JComboBox(new String[]{"International Air", "International Sea" , "Domestic Air", "Domestic Land"});
		priorityField.setSelectedIndex(-1);
		priorityField.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString().equals("International Sea") || e.getItem().toString().equals("International Air")){
					destinationField.removeAllItems();
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

		
		
		JLabel costW = new JLabel("Cost / gram");
		JLabel costV = new JLabel("Cost / cm3");
		
		
		//Buttons for controlling the event inside the dialog box.
		CustomButton submit = new CustomButton("Submit_Normal", "Submit_Pressed", "Submit_Hover", "submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dialog.dispose();				
			}
		});
		CustomButton cancel = new CustomButton("Cancel_Normal", "Cancel_Pressed", "Cancel_Hover", "cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();				
			}
		});
		
		
		//===================== Layout for DialogBox ===============================
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		
		
		
		
		
		dialog.add(panel);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setSize(600, 750);
		dialog.setVisible(true);
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
	
}
