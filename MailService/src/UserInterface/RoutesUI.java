package UserInterface;

import java.awt.*;
import java.awt.Dialog.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;
import service.*;

import file.XMLWorker;


public class RoutesUI extends JPanel {

	private JLabel title;
	private JButton addRoutes;
	private JScrollPane tableScrollPane;
	private JTable routesTable;
	String[] columnTitle;

	public RoutesUI(){

		setBackground(Color.WHITE);
		title = new JLabel("Routes");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		addRoutes = new JButton("Add New Routes");
		addRoutes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRoutesForm();
			}
		});


		columnTitle = new String[]{"Origin", "Destination", "Carrier Company", "Priority", "Price / gram", "Price / cm3" };

		ArrayList<Route> routes = XMLWorker.getAllRoutes();
		String[][] datas = new String[routes.size()][6]; 
		for(int i=0; i<routes.size(); i++){
			datas[i] = new String[] {routes.get(i).getOrigin(), routes.get(i).getDestination(), routes.get(i).getCompany(),
					routes.get(i).getPriority(), "" + routes.get(i).getWeightCost(), "" + routes.get(i).getVolumeCost()};
		}
		
		routesTable = new JTable(new DefaultTableModel(datas, columnTitle));
		tableScrollPane = new JScrollPane(routesTable);

		routesTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		routesTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 16));
		routesTable.getTableHeader().setResizingAllowed(false);
		routesTable.getTableHeader().setReorderingAllowed(false);
		routesTable.setEnabled(false);
		routesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		routesTable.setRowHeight(30);

		routesTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		routesTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		routesTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		routesTable.getColumnModel().getColumn(3).setPreferredWidth(145);
		routesTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		routesTable.getColumnModel().getColumn(5).setPreferredWidth(50);


		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(80, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(title)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(addRoutes)
										.addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(80, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(45, 45, 45)
						.addComponent(title)
						.addGap(20, 20, 20)
						.addComponent(addRoutes)
						.addGap(20, 20, 20)
						.addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(33, 33, 33))
				);

	}


	/**
	 * A dialog box that prompt users to enter desired routes with the given details
	 */

	private JLabel dialogTitle;

	private JLabel companyName;
	private JTextField companyField;
	private JLabel companyWarning;

	private JLabel origin;
	private JLabel originWarning;
	private JComboBox originField;

	private JLabel destination;
	private JComboBox destinationField;
	private JLabel destinationWarning;

	private JLabel priority;
	private JLabel priorityWarning;
	private JComboBox priorityField;

	private JLabel costW;
	private JTextField costWField;
	private JLabel costWWarning;

	private JLabel costV;
	private JTextField costVField;
	private JLabel costVWarning;

	private JLabel maxWeight;
	private JTextField maxWField;
	private JLabel maxWWarning;

	private JLabel maxVol;
	private JTextField maxVField;
	private JLabel maxVWarning;

	private JLabel mailCost;
	private JTextField mailCostField;
	private JLabel mailCostWarning;

	private JLabel frequency;
	private JTextField frequencyField;
	private JLabel frequencyWarning;

	private JLabel EST;
	private JTextField ESTField;
	private JLabel ESTWarning;


	public void addRoutesForm(){
		final JDialog dialog = new JDialog();
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		dialogTitle = new JLabel("Routes");
		dialogTitle.setFont(new Font("Verdana", Font.BOLD, 36));


		//====================== Company Name =====================================
		companyName = new JLabel("Carrier Name");
		companyName.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		companyField = new JTextField();
		companyField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		companyField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(companyField.getText().equals("")){
					companyWarning.setText("*Plese input carrier name");
				} else {companyWarning.setText("");}
			}
			@Override
			public void focusGained(FocusEvent e) {	}
		});
		companyWarning = new JLabel();
		companyWarning.setForeground(Color.RED);


		//========================= Origin Field ===================================
		origin = new JLabel("Origin");
		origin.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		originWarning = new JLabel();
		originWarning.setForeground(Color.RED);
		ArrayList<String> city = XMLWorker.getCitiesFromCountry("New Zealand");
		
		// sort the city list in alphabetical order
		Collections.sort(city);

		originField = new JComboBox(city.toArray());
		originField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
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

		//======================= Destination Field ==============================
		destination = new JLabel("Destination");
		destination.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		destinationField = new JComboBox();
		destinationField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
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

		//======================= Priority Field ======================================
		priority = new JLabel("Priority");
		priority.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		priorityWarning = new JLabel();
		priorityWarning.setForeground(Color.RED);
		priorityField = new JComboBox(new String[]{"International Air", "International Sea" , "Domestic Air", "Domestic Land"});
		priorityField.setSelectedIndex(-1);
		priorityField.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString().equals("International Sea") || e.getItem().toString().equals("International Air")){
					destinationField.removeAllItems();
					populateCountries();
					destinationField.setSelectedIndex(-1);
				}
				else{
					destinationField.removeAllItems();
					ArrayList<String> city = XMLWorker.getCitiesFromCountry("New Zealand");

					// sort the city list in alphabetical order
					Collections.sort(city);

					for(String s: city) {
						destinationField.addItem(s);
					}
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
		priorityField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		priorityField.setBackground(Color.WHITE);


		//======================== Weight Field ======================================

		costW = new JLabel("Cost / gram");
		costW.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		costWField = new JTextField();
		costWField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		costWField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(costWField.getText().equals("") ){
					costWWarning.setText("*Please input value");
				}

				else {
					if(isDigit(costWField.getText()) == false) {
						costWWarning.setText("*Please input numbers");
					} else {costWWarning.setText("");}
				}
			}

			@Override
			public void focusGained(FocusEvent e) { }
		});
		costWWarning = new JLabel();
		costWWarning.setForeground(Color.RED);


		//===================== Volume Field ==================================
		costV = new JLabel("Cost / cm3");
		costV.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		costVField = new JTextField();
		costVField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		costVField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(costVField.getText().equals("")){
					costVWarning.setText("*Please input value");
				}
				else {
					if(isDigit(costVField.getText()) == false) {
						costVWarning.setText("*Please input numbers");
					} else {costVWarning.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});
		costVWarning = new JLabel();
		costVWarning.setForeground(Color.RED);


		//========================= Max Weight Field ============================
		maxWeight = new JLabel("Maximum Weight");
		maxWeight.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		maxWField = new JTextField();
		maxWWarning = new JLabel();
		maxWWarning.setForeground(Color.RED);
		maxWField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(maxWField.getText().equals("")){
					maxWWarning.setText("*Please input value");
				}
				else {
					if(isDigit(maxWField.getText()) == false) {
						maxWWarning.setText("*Please input numbers");
					} else {maxWWarning.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});

		//======================== max Volume Field ===============================
		maxVol = new JLabel("Maximum Volume");
		maxVol.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		maxVField = new JTextField();
		maxVWarning = new JLabel();
		maxVWarning.setForeground(Color.RED);
		maxVField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(maxVField.getText().equals("")){
					maxVWarning.setText("*Please input value");
				}
				else {
					if(isDigit(maxVField.getText()) == false) {
						maxVWarning.setText("*Please input numbers");
					} else {maxVWarning.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});



		//========================== Mail Cost ======================================
		mailCost = new JLabel("Mail Cost");
		mailCost.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		mailCostField = new JTextField();
		mailCostWarning = new JLabel();
		mailCostWarning.setForeground(Color.RED);
		mailCostField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(mailCostField.getText().equals("")){
					mailCostWarning.setText("*Please input value");
				}
				else {
					if(isDigit(mailCostField.getText()) == false) {
						mailCostWarning.setText("*Please input numbers");
					} else {mailCostWarning.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});


		//=========================== Frequency ===================================
		frequency = new JLabel("Frequency");
		frequency.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		frequencyField = new JTextField();
		frequencyWarning = new JLabel();
		frequencyWarning.setForeground(Color.RED);
		frequencyField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(frequencyField.getText().equals("")){
					frequencyWarning.setText("*Please input value");
				}
				else {
					if(isDigit(frequencyField.getText()) == false) {
						frequencyWarning.setText("*Please input numbers");
					} else {frequencyWarning.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});

		//========================== Estimated Time =================================
		EST = new JLabel("Estimated Delivery");
		EST.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 16));
		ESTField = new JTextField();
		ESTWarning = new JLabel();
		ESTWarning.setForeground(Color.RED);
		ESTField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if(ESTField.getText().equals("")){
					ESTWarning.setText("*Please input value");
				}
				else {
					if(isDigit(ESTField.getText()) == false) {
						ESTWarning.setText("*Please input numbers");
					} else {ESTWarning.setText("");}
				}				
			}

			@Override
			public void focusGained(FocusEvent e) { 	}
		});

		//========================== Button Field ===================================
		CustomButton submit = new CustomButton("Submit_Normal", "Submit_Pressed", "Submit_Hover", "submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkFields() == true){
					
					int result = JOptionPane.showConfirmDialog(panel, "Confirm route details?", "Confirm", JOptionPane.YES_NO_OPTION);

					Route r = new Route(originField.getSelectedItem().toString().trim(),
							destinationField.getSelectedItem().toString().trim(),
							companyField.getText(),
							Integer.parseInt(maxWField.getText()),
							Integer.parseInt(maxVField.getText()),
							Double.parseDouble(costWField.getText()),
							Double.parseDouble(costVField.getText()),
							Double.parseDouble(mailCostField.getText()),
							Integer.parseInt(frequencyField.getText()),
							Integer.parseInt(ESTField.getText()),
							priorityField.getSelectedItem().toString()
							);
					if(result == JOptionPane.YES_OPTION){
						addRoutes(r);
						dialog.dispose();
					}
				}
								
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

		GroupLayout layout = new javax.swing.GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(25, 25, 25)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(EST, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(ESTField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(ESTWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(frequency, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(frequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(frequencyWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																.addComponent(mailCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(priority, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(origin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(companyName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(costV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(costW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(destination, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(maxWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(maxVol, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(costWField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addComponent(costWWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addGroup(layout.createSequentialGroup()
																						.addComponent(costVField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																						.addComponent(costVWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																								.addGroup(layout.createSequentialGroup()
																										.addComponent(submit)
																										.addGap(49, 49, 49)
																										.addComponent(cancel))
																										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(layout.createSequentialGroup()
																														.addComponent(maxWField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																														.addComponent(maxWWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(layout.createSequentialGroup()
																																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																																		.addComponent(originField, 200,200,200)
																																		.addComponent(destinationField, 200,200,200)
																																		.addComponent(priorityField, 200,200,200)
																																		.addComponent(companyField, 200,200,200))
																																		.addGap(10, 10, 10)
																																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																																				.addComponent(companyWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																				.addComponent(priorityWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																				.addComponent(originWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																				.addComponent(destinationWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
																																				.addGroup(layout.createSequentialGroup()
																																						.addComponent(maxVField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																						.addComponent(maxVWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
																																						.addGroup(layout.createSequentialGroup()
																																								.addComponent(mailCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																								.addComponent(mailCostWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
																																								.addComponent(dialogTitle))
																																								.addContainerGap(25, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(45, 45, 45)
						.addComponent(dialogTitle)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(companyName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(priority, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(priorityWarning))
												.addGap(10, 10, 10)
												.addComponent(origin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(10, 10, 10)
												.addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(companyField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(companyWarning))
																.addGap(10, 10, 10)
																.addComponent(priorityField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(10, 10, 10)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(originField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(originWarning))
																		.addGap(10, 10, 10)
																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(destinationWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
																				.addGap(10, 10, 10)
																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(maxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(maxWField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(maxWWarning))
																						.addGap(10, 10, 10)
																						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																								.addComponent(maxVol, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(maxVField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(maxVWarning))
																								.addGap(10, 10, 10)
																								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(costWField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(costW, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(costWWarning))
																										.addGap(10, 10, 10)
																										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																												.addComponent(costVField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(costV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(costVWarning))
																												.addGap(10, 10, 10)
																												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(mailCost, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(mailCostField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(mailCostWarning))
																														.addGap(10, 10, 10)
																														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																																.addComponent(frequency, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(frequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(frequencyWarning))
																																.addGap(10, 10, 10)
																																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																																		.addComponent(ESTField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addComponent(ESTWarning)
																																		.addComponent(EST, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
																																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
																																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																																				.addComponent(submit)
																																				.addComponent(cancel))
																																				.addContainerGap())
				);


		dialog.add(panel);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setSize(620, 620);
		dialog.setResizable(false);
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

	private void populateCountries(){
		ArrayList<String> countries = XMLWorker.loadCountries();
		// Get all cities in order of country
		ArrayList<ArrayList<String>> allCities = XMLWorker.getAllCities();

		for (int i = 0; i < allCities.size(); i++){
			destinationField.addItem(countries.get(i));

			for (String city: allCities.get(i)){
				destinationField.addItem("  " + city);

			}
		}
	}
	
	public boolean checkFields(){
		if( (!companyField.getText().equals("")) && (originField.getSelectedIndex()!=-1) 
				&& (destinationField.getSelectedIndex()!=-1) 
				&& (priorityField.getSelectedIndex()!=-1)
				&& (!maxWField.getText().equals("")) && (isDigit(maxWField.getText()))
				&& (!maxVField.getText().equals("")) && (isDigit(maxVField.getText())) 
				&& (!costWField.getText().equals("")) && (isDigit(costWField.getText())) 
				&& (!costVField.getText().equals("")) && (isDigit(costVField.getText()))
				&& (!mailCostField.getText().equals("")) && (isDigit(mailCostField.getText())) 
				&& (!frequencyField.getText().equals("")) && isDigit((frequencyField.getText())) 
				&& (!ESTField.getText().equals("")) && (isDigit(ESTField.getText()))
				){
		
			return true;
		}
		return false;
	}

	public void addRoutes(Route r){
		//"Origin", "Destination", "Carrier Company", "Priority", "Price / gram", "Price / cm3"
		DefaultTableModel tm = (DefaultTableModel) routesTable.getModel();
		String[] temp = new String[6];
		
		temp[0] = originField.getSelectedItem().toString().trim();
		temp[1] = destinationField.getSelectedItem().toString().trim();
		temp[2] = companyField.getText();
		temp[3] = priorityField.getSelectedItem().toString().trim();
		temp[4] = costWField.getText().toString();
		temp[5] = costVField.getText().toString();
		
		tm.addRow(temp);
		JTable newTable = new JTable(tm);
		routesTable = newTable;
		XMLWorker.addRoute(r);
		
	}
}
