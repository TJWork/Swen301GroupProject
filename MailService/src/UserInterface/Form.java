package UserInterface;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Form extends JPanel{
	JLabel CustomerName;
	JTextField name;

	JLabel mailType;
	JSpinner type;

	JLabel priorityType;
	JSpinner priority;

	JLabel location;
	JSpinner locationType;

	JLabel destinationAddress;
	JTextField desStrNo;
	JTextField desSuburb;
	JTextField desCity;
	JTextField desPostCode;
	JComboBox desCountry;

	JLabel originAddress;
	JTextField oriStrNo;
	JTextField oriSuburb;
	JTextField oriCity;
	JTextField oriPostCode;

	ArrayList<JTextField> textfields = new ArrayList<JTextField>();


	public Form(){
		CustomerName = new JLabel("Customer Name");
		name = new JTextField("Customer Name");
		textfields.add(name);

		mailType = new JLabel("Type of mail");
		type = new JSpinner(new SpinnerListModel(new String[] {"Parcel", "Mail"}));

		priorityType = new JLabel("Mail Type");
		priority = new JSpinner(new SpinnerListModel(new String[] {"International Air", "Domestic Air", "International Sea", "Domestic Land"}));

		destinationAddress = new JLabel("Destination Address");
		desStrNo = new JTextField("Street No.");
		desSuburb = new JTextField("Suburb");
		desCity = new JTextField("City");
		desPostCode = new JTextField("Post Code");
		desCountry = new JComboBox();
		textfields.add(desStrNo);
		textfields.add(desSuburb);
		textfields.add(desCity);
		textfields.add(desPostCode);

		ChangeListener listener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				update();
			}
		};

		priority.addChangeListener(listener);

		originAddress = new JLabel("Origin Address");
		oriStrNo = new JTextField("Street No.");
		oriSuburb = new JTextField("Suburb");
		oriCity = new JTextField("City");
		oriPostCode = new JTextField("Post Code");
		textfields.add(oriStrNo);
		textfields.add(oriSuburb);
		textfields.add(oriCity);
		textfields.add(oriPostCode);

		for(final JTextField tf: textfields){
			final String defaultText = tf.getText();
			tf.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e) {
					if(tf.getText().equals(defaultText))
						tf.setText("");
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(tf.getText().equals("")){
						tf.setText(defaultText);
					}
				}
			});
		}

		addCountries();

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(mailType)
                        .addComponent(CustomerName)
                        .addComponent(priorityType)
                        .addComponent(originAddress)
                        .addComponent(destinationAddress))
                    .addGap(41, 41, 41)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(type, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(priority, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                        .addComponent(oriStrNo, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(oriSuburb, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(oriCity, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(desStrNo, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(desSuburb, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(desCity, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(desCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(desPostCode, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addComponent(oriPostCode, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(143, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(87, 87, 87)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(CustomerName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mailType, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addComponent(type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(priorityType, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addComponent(priority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(originAddress)
                        .addComponent(oriStrNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(oriSuburb, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(oriCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(oriPostCode, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(desStrNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(destinationAddress))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(desSuburb, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(desCity, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(desPostCode, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(desCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(117, Short.MAX_VALUE))
            );

	}
	
	public void update(){
		if(priority.getValue().toString().equals("International Sea") || priority.getValue().toString().equals("International Air") ){
			desCountry.setSelectedIndex(-1);
			desCountry.setEnabled(true);

		}
		else {
			desCountry.setSelectedItem("New Zealand");
			desCountry.setEnabled(false);
		}
	}

	public void addCountries(){ 
		try{
			Scanner sc = new Scanner(new File("List_of_Countries.txt"));
			while(sc.hasNextLine()){
				desCountry.addItem(sc.nextLine());
			}
		} catch(FileNotFoundException e){ e.printStackTrace();}


	}

	public void resetField(){

	}

	public void logEvent(){

		System.out.println(name.getText());
		System.out.println(type.getValue().toString());
		System.out.println(priority.getValue().toString());
		System.out.println(desCountry.getSelectedItem().toString());
	}
}
