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
		
		

		priceTable = new JTable(){
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;  
			}
		};
		
		priceTable.setModel(new DefaultTableModel(datas, columnTitle));
		tableScrollPane = new JScrollPane(priceTable);
		
		tableScrollPane = new JScrollPane(priceTable);
		tableScrollPane.setBackground(Color.WHITE);

		priceTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		priceTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 16));
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


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(title)
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 822, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(title)
                .addGap(65, 65, 65)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
		

	}
	
	public void addPrice(){
		JPanel panel = new JPanel();
		
		
		
		
		
		
		
		
	}
}
