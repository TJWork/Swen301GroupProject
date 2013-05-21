package UserInterface;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import file.XMLWorker;

public class RoutesUI extends JPanel implements ActionListener{
	private BufferedImage image;

	private final JLabel carrierName;
	private final JTextField carrierField;
	private final JLabel carrierWarning;

	private final JLabel transportType;
	private final JComboBox transportTypeField;
	private final JLabel transportTypeWarning;

	private final JLabel originCity;
	private final JComboBox originCityField;
	private final JLabel originCityWarning;

	private final JLabel destination;
	private final JComboBox destinationField;
	private final JLabel destinationWarning;

	private final JPanel cardPanel;

	private final JLabel title;

	public RoutesUI(){

		try {
			image = ImageIO.read(new File("Images/BG.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		title = new JLabel("Routes");
		title.setFont(new Font("Verdana", Font.BOLD, 36));

		cardPanel = new JPanel(new CardLayout());
		cardPanel.setBackground(Color.WHITE);
		

		JPanel card1 = new JPanel();
		card1.setBackground(Color.WHITE);
		JPanel card2 = new JPanel();
		card2.setBackground(Color.WHITE);


		cardPanel.add(card2, "mail");
		cardPanel.add(card1,"parcel");


		GroupLayout cardLayout = new GroupLayout(card1);
		card1.setLayout(cardLayout);
		cardLayout.setHorizontalGroup(
				cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(cardLayout.createSequentialGroup()
						.addGap(10, 10, 10)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addGap(40, 40, 40)
										.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				)))));
		cardLayout.setVerticalGroup(
				cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(cardLayout.createSequentialGroup()
						.addGap(20, 20, 20)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addGap(20, 20, 20)
								.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				))));


		/*=========================== Carrier Field ==================================*/

		carrierName = new JLabel("Carrier");
		carrierName.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		carrierField = new JTextField();
		carrierField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		carrierWarning = new JLabel();
		carrierWarning.setForeground(Color.RED);


		/*=========================== Mail Type Field ==================================*/

		/*transportType = new JLabel("Mail Type");
		transportType.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		transportTypeField = new JSpinner(new SpinnerListModel(new String[] {"Mail", "Parcel"}));
		transportTypeField.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				CardLayout c = (CardLayout) cardPanel.getLayout();
				if(transportTypeField.getValue().toString().equals("Mail")){
					c.show(cardPanel, "mail");
				}
				else{
					c.show(cardPanel, "parcel");
				}
			}
		});
		transportTypeField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		transportTypeWarning = new JLabel();
		transportTypeWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));*/


		/*=========================== Transport type Field ==================================*/

		transportType = new JLabel("Transport type");
		transportType.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		transportTypeField = new JComboBox(new String[]{"Air", "Land", "Sea"});
		transportTypeField.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getItem().toString().equals("International Sea") || e.getItem().toString().equals("International Air")){
//					destinationField.removeAllItems();
//					populateCountries();
				}
				else{
					destinationField.removeAllItems();
					ArrayList<String> city = XMLWorker.getCitiesFromCountry("New Zealand");

					for(String s: city) {
						destinationField.addItem(s);
					}
				}
			}
		});
		transportTypeField.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));
		transportTypeField.setBackground(Color.WHITE);
		transportTypeWarning = new JLabel();
		transportTypeWarning.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 18));


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
                                .addComponent(carrierName)
                                .addGap(10, 10, 10)
                                .addComponent(carrierField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(carrierWarning, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                            /*.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(transportType)
                                .addGap(10, 10, 10)
                                .addComponent(transportTypeField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(transportTypeWarning, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))*/
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(transportType)
                                        .addGap(10, 10, 10)
                                        .addComponent(transportTypeField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(originCity)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(originCityField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(originCityWarning, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(transportTypeWarning, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
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
                            .addComponent(carrierName))
                        .addComponent(carrierField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(carrierWarning, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    /*.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(transportTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(transportType)
                                .addComponent(transportTypeWarning))))
                    .addGap(20, 20, 20)*/
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(transportTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(transportType)
                                .addComponent(transportTypeWarning))))
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(cancel)
                            .addComponent(submit)))
                    .addContainerGap(80, Short.MAX_VALUE))
            );
            setBackground(Color.CYAN);
	}


	 /**	Creating background image */
	@Override
	public void paintComponent (Graphics g){
		g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), this);
	}


	 /** Helper method to populate the JComboBox */
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

	/** Helper method to check if the field taht requires digit is correct */
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
			if(carrierField.getText().equals("")){
				carrierWarning.setText("*Plese input Carrier name");
			} else {carrierWarning.setText("");}

			if(destinationField.getSelectedIndex()==-1){
				destinationWarning.setText("*Please select a destination");
			} else {destinationWarning.setText("");}

			if(originCityField.getSelectedIndex()==-1) {
				originCityWarning.setText("*Please select origin");
			} else {originCityWarning.setText("");}

			/*Parcel Event*/
			/*if(transportTypeField.getValue().toString().equals("Parcel")){

				if(weightField.getText().equals("") ){
					weightLabel.setText("*Please input weight");
				}

				else {
					if(isDigit(weightField.getText()) == false) {
						weightLabel.setText("*Please input numbers");
					} else {weightLabel.setText("");}
				}

				if(volumeField.getText().equals("")){
					volumeLabel.setText("*Please input volume");
				}
				else {
					if(isDigit(volumeField.getText()) == false) {
						volumeLabel.setText("*Please input numbers");
					} else {volumeLabel.setText("");}
				}


				if((!carrierField.getText().equals("")) && (isDigit(volumeField.getText()) == true) && (isDigit(weightField.getText()) == true) && (transportTypeField.getValue().toString().equals("Parcel")) && destinationField.getSelectedIndex()>-1){
					Parcel p = new Parcel(KPSUserInterface.clock.getCurrentDate(), destinationField.getSelectedItem().toString().trim(), originCityField.getSelectedItem().toString().trim(),
							weightField.getText().trim(), volumeField.getText().trim(), (priorityField.getSelectedIndex() +1));


					System.out.println(p.toString());
					XMLWorker.addMail(p);
					ClerkGUI.dashBoard.addItem("Parcel", p.getData());
				}
			}*/

			/*Mail Event*/
			/*else if(transportTypeField.getValue().toString().equals("Mail")){
				if( (!carrierField.getText().equals("")) && (destinationField.getSelectedIndex()>-1) && (originCityField.getSelectedIndex()>-1) ){
					Mail m = new Mail(KPSUserInterface.clock.getCurrentDate(), destinationField.getSelectedItem().toString().trim(), originCityField.getSelectedItem().toString().trim(),
							(priorityField.getSelectedIndex()+1));

					System.out.println(m.toString());
					XMLWorker.addMail(m);
					ClerkGUI.dashBoard.addItem("Mail", m.getData());

				}
			}*/
		}

		/*=============================== Cancel back to DashBoard ==========================================*/
		else if(e.getActionCommand().equals("cancel")){

		}

	}
	
}
