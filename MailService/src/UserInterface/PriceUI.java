package UserInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PriceUI extends JPanel {

	private JLabel title;
	private JButton addPrice;
	private JScrollPane tableScrollPane;
	private JTable priceTable;

	public PriceUI(){
		setBackground(Color.WHITE);
		title = new JLabel("Price");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		addPrice = new JButton("Add New Price");

		addPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//Setting the columns for price table
		String[] columnNames = new String[]{"Origin", "Destination", "Priority", "Price per gram", "Price per cm3" };

		
		

		priceTable = new JTable();
		priceTable.setBackground(Color.WHITE);


		tableScrollPane = new JScrollPane(priceTable);
		tableScrollPane.setBackground(Color.WHITE);



		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(60, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(title)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(addPrice)
										.addComponent(tableScrollPane)))
										.addContainerGap(60, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(39, 39, 39)
						.addComponent(title)
						.addGap(20, 20, 20)
						.addComponent(addPrice)
						.addGap(15, 15, 15)
						.addComponent(tableScrollPane)
						.addGap(47, 47, 47))
				);

		add(title);
		add(addPrice);
		add(tableScrollPane);
	}
	
	public void addPrice(){
		JPanel panel = new JPanel();
		
		
		
		
		
		
		
		
	}
}
