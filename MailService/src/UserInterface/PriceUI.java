package UserInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import service.Route;
import file.XMLWorker;

public class PriceUI extends JPanel {

	private JLabel title;
	private JScrollPane tableScrollPane;
	private JTable priceTable;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public PriceUI(){
		setBackground(Color.WHITE);
		title = new JLabel("Customer Price");
		title.setFont(new Font("Verdana", Font.BOLD, 36));

		
		//Setting the columns for price table
		String[] columnTitle = new String[]{"Origin", "Destination", "Priority", "NZD/gram", "NZD/cm3", "NZD/Mail" };

		ArrayList<Route> routes = XMLWorker.getAllRoutes();
		String[][] datas = new String[routes.size()][6]; 
		for(int i=0; i<routes.size(); i++){
			datas[i] = new String[] {routes.get(i).getOrigin(), routes.get(i).getDestination(), routes.get(i).getPriority(), 
					"" + df.format(routes.get(i).getWeightCost()*1.3), "" + df.format(routes.get(i).getVolumeCost()*1.3), "" + df.format(routes.get(i).getMailCost()*1.3)};
		}
		
		

		priceTable = new JTable();//{
//			public boolean isCellEditable(int rowIndex, int colIndex) {
//				return false;  
//			}
//		};
		
		priceTable.setModel(new DefaultTableModel(datas, columnTitle));
		tableScrollPane = new JScrollPane(priceTable);
		
		tableScrollPane = new JScrollPane(priceTable);
		tableScrollPane.setBackground(Color.WHITE);

		priceTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		priceTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 14));
		priceTable.getTableHeader().setResizingAllowed(false);
		priceTable.getTableHeader().setReorderingAllowed(false);
		priceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		priceTable.setRowHeight(30);

		priceTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		priceTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		priceTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		priceTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		priceTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		priceTable.getColumnModel().getColumn(5).setPreferredWidth(50);


		JButton button = new JButton("Change Price");
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(getParent(), "Update price details?", "Price Update", JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == JOptionPane.YES_OPTION){
					JOptionPane.showConfirmDialog(getParent(), "Customer Price updated", "Confirm", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
        
        
		
       GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap(80, Short.MAX_VALUE)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(title)
//                    .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap(80, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(45, 45, 45)
//                .addComponent(title)
//                .addGap(20, 20, 20)
//                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(33, 33, 33))
//        );
		
        
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(80, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
//                                .addComponent(numberMailEvents, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button))
                            .addComponent(tableScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(80, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(title)
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                        .addComponent(numberMailEvents, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button))
                    .addGap(35, 35, 35)
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33))
            );
        

	}
	
}
