package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.Mail;
import service.Parcel;

import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

import file.XMLWorker;

import static com.googlecode.charts4j.Color.*;

public class BusinessStats extends JPanel{

	private JLabel title;
	private JLabel mailEvents;
	private JLabel expenditure;
	private JLabel revenue;
	private ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null, null});
	private ArrayList<Parcel> parcel = XMLWorker.getParcels(new String[] {null, null, null, null, null, null, null});

	public BusinessStats(){
		title = new JLabel("Business Statistics");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		
		setBackground(Color.WHITE);
		mailEvents = mailEvents();
		expenditure = getTotalExpenditure();
		revenue = getTotalRevenue();
		
        
		add(mailEvents);
		add(expenditure);
		add(revenue);
	}
	
	public JLabel mailEvents(){
	
		int total = mail.size()+parcel.size();
		float mailP = mail.size()*100/total;
		float parcelP = parcel.size()*100/total;
		
        Slice s1 = Slice.newSlice((int)parcelP, BLUE, "Parcel");
        Slice s2 = Slice.newSlice((int)mailP, AQUA, "Mail");

        PieChart chart = GCharts.newPieChart(s1, s2);
        chart.setTitle("KPS Mail Events", BLACK, 16);
        chart.setSize(400, 230);

        chart.setThreeD(true);
        
        URL url;
        Icon icon = null;
        BufferedImage bimg;
		try {
			url = new URL(chart.toURLString());
			bimg = ImageIO.read(url);
	        icon = new ImageIcon(bimg);


			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        JLabel label = new JLabel(icon);
        
        return label;
	}
	
	public JLabel getTotalExpenditure(){
		double total=0;
		total = XMLWorker.getTotalSales()/1.3;
		
		JLabel label = new JLabel("Total Expenditure: " + total);
		return label;
	}
	
	public JLabel getTotalRevenue(){
		double total=0;
		total = XMLWorker.getTotalSales()*1.3;
		
		JLabel label = new JLabel("Total Revenue: " + total);
		return label;
	}
	
}
