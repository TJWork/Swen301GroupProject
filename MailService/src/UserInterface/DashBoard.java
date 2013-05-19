package UserInterface;

import javax.swing.*;
import service.*;
import file.XMLWorker;

import java.awt.*;
import java.util.*;

public class DashBoard extends JPanel{


	private JLabel title;
	private JScrollPane tableScrollPane;
	private JTable mailEventsTable;
	private JLabel expenditure;
	private JLabel revenue;
	private JLabel numberMailEvents;
	
	public DashBoard(){
	
		setBackground(Color.WHITE);
		title = new JLabel("DashBoard");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		expenditure = new JLabel("Expenditure");
		expenditure.setFont(new Font("Verdana", Font.BOLD, 16));
		revenue = new JLabel("Revenue");
		revenue.setFont(new Font("Verdana", Font.BOLD, 16));
		numberMailEvents = new JLabel("Mail Events");
		numberMailEvents.setFont(new Font("Verdana", Font.BOLD, 16));
		
		String[] columnTitle = new String[] {"Date", "Type", "Destination", "Origin", "Priority", "Price"};
		
		ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null});
		ArrayList<Parcel> parcel = XMLWorker.getParcels(new String[] {null, null, null, null, null, null});
		
		String[][] datas = new String[mail.size()+parcel.size()][6];
		
		for(int i=0; i<mail.size(); i++){
			String[] str = mail.get(i).getData();
			datas[i] = new String[] {str[0], "Mail", str[1], str[2], getPriority(str[3]), str[4]} ;
		}
		for(int i=mail.size(); i<datas.length; i++){
			String[] str = parcel.get(i-mail.size()).getData();
			datas[i] = new String[] {str[0], "Parcel", str[1], str[2], getPriority(str[5]), str[6]} ;
		}
		
		numberMailEvents.setText("<html><font face=Verdana color=black size=5>Mail Events</font><br> " 
									+ "<font face=Verdana color=black size=6>" + datas.length + "</font></html>");
		
		mailEventsTable = new JTable(datas,columnTitle);
		
		mailEventsTable.setFont(new Font("Verdana", Font.PLAIN, 14));
		mailEventsTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 16));
		mailEventsTable.getTableHeader().setResizingAllowed(false);
		mailEventsTable.getTableHeader().setReorderingAllowed(false);
		mailEventsTable.setEnabled(false);
		
		mailEventsTable.setRowHeight(30);
		
		mailEventsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		mailEventsTable.getColumnModel().getColumn(1).setPreferredWidth(45);
		mailEventsTable.getColumnModel().getColumn(2).setPreferredWidth(230);
		mailEventsTable.getColumnModel().getColumn(3).setPreferredWidth(230);
		mailEventsTable.getColumnModel().getColumn(4).setPreferredWidth(145);
		mailEventsTable.getColumnModel().getColumn(5).setPreferredWidth(69);

		
		tableScrollPane = new JScrollPane(mailEventsTable);
		
		
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(80, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(title)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(expenditure, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(revenue, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numberMailEvents, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                        .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(80, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(title)
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(expenditure, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addComponent(revenue, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addComponent(numberMailEvents, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
                    .addGap(45, 45, 45))
            );
				
	}
	
	/**
	 * Helper method for getting the type of freight by priority 
	 */
	private String getPriority(String value){
		double d = Double.parseDouble(value);
		int priority = (int) d;
		switch (priority) {
		case 1: return "International Air";
		case 2: return "International Sea";
		case 3: return "Domestic Air";
		case 4: return "Domestic Land";
		}
		return "";
	}
}
