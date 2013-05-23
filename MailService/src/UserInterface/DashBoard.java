package UserInterface;

import javax.swing.*;
import javax.swing.table.*;

import service.*;
import file.XMLWorker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author Sam
 * A Dashboard showing all the mails events occurred to the business.
 */
public class DashBoard extends JPanel implements ActionListener{
	private JLabel title;
	private JScrollPane tableScrollPane;
	private JTable mailEventsTable;
	private JLabel expenditure;
	private JLabel revenue;
	private JLabel numberMailEvents;
	private String[] columnTitle;

	public DashBoard(String s){

		setBackground(Color.WHITE);
		title = new JLabel("DashBoard");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		
		expenditure = new JLabel("Expenditure");
		expenditure.setFont(new Font("Verdana", Font.BOLD, 16));
		expenditure.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.GRAY));
		
		revenue = new JLabel("Revenue");
		revenue.setFont(new Font("Verdana", Font.BOLD, 16));
		revenue.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.GRAY));
		
		numberMailEvents = new JLabel("Mail Events");
		numberMailEvents.setFont(new Font("Verdana", Font.BOLD, 16));
		numberMailEvents.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.GRAY));

		columnTitle = new String[] {"Date", "Type", "Destination", "Origin", "Priority", "Price"};

		ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null, null});
		ArrayList<Parcel> parcel = XMLWorker.getParcels(new String[] {null, null, null, null, null, null, null});

		String[][] datas = new String[mail.size()+parcel.size()][6];

		for(int i=0; i<mail.size(); i++){
			String[] str = mail.get(i).getData();
			datas[i] = new String[] {str[0], "Mail", str[1], str[2], getPriority(str[4]), str[3]} ;
		}
		for(int i=mail.size(); i<datas.length; i++){
			String[] str = parcel.get(i-mail.size()).getData();
			datas[i] = new String[] {str[0], "Parcel", str[1], str[2], getPriority(str[6]), str[5]} ;
		}

		numberMailEvents.setText("<html><font face=Verdana color=black size=5>Mail Events</font><br> "
									+ "<font face=Verdana color=black size=6>" + datas.length + "</font></html>");

		mailEventsTable = new JTable(new DefaultTableModel(datas,columnTitle));
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

        JButton button = new JButton("Add New Mail Event");//("AddNew_Normal", "AddNew_Pressed", "AddNew_Hover", "");
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Form f = new Form();
				f.setSize(600, 670);
				f.setResizable(false);
				f.setVisible(true);
			}
		});
        
        if(s.equals("Manager")){
        	button.setVisible(false);
        }

        GroupLayout layout = new GroupLayout(this);

        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE)
                    .addComponent(button)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(expenditure, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                            .addGap(167, 167, 167)
                            .addComponent(revenue, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                            .addGap(168, 168, 168)
                            .addComponent(numberMailEvents, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))))
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
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

	}

	public void addItem(String type, String[] str){
		DefaultTableModel tm = (DefaultTableModel) mailEventsTable.getModel();
		String[] temp ;
		if(type.equals("Parcel"))
			//"Date", "Type", "Destination", "Origin", "Priority", "Price"
			temp = new String[] {str[0], "Parcel", str[1], str[2], getPriority(str[5]), str[4]} ;

		else temp =  new String[] {str[0], "Mail", str[1], str[2], getPriority(str[4]), str[3]} ;

		tm.addRow(temp);
		JTable newTable = new JTable(tm);
		mailEventsTable = newTable;
		
		numberMailEvents.setText("<html><font face=Verdana color=black size=5>Mail Events</font><br> "
				+ "<font face=Verdana color=black size=6>" + mailEventsTable.getRowCount() + "</font></html>");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
