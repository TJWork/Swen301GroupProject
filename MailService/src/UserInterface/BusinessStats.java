package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import service.Mail;
import service.Parcel;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;
import com.googlecode.charts4j.Slice;
import com.googlecode.charts4j.Color.*;

import file.XMLWorker;

import static com.googlecode.charts4j.Color.*;

public class BusinessStats extends JPanel{

	private JLabel title;
	private JLabel mailEvents;
	private JLabel expenditureOverTime;
	private JLabel expenditure;
	private JLabel revenue;
	private ArrayList<Mail> mail = XMLWorker.getMail(new String[]{null, null, null, null, null});
	private ArrayList<Parcel> parcel = XMLWorker.getParcels(new String[] {null, null, null, null, null, null, null});

	public BusinessStats(){
		title = new JLabel("Business Statistics");
		title.setFont(new Font("Verdana", Font.BOLD, 36));
		
		setBackground(Color.WHITE);
		mailEvents = mailEvents();
		expenditureOverTime = expenditureOverTime();
		expenditure = getTotalExpenditure();
		revenue = getTotalRevenue();
		
		add(mailEvents);
		add(expenditureOverTime);
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
	
	public JLabel expenditureOverTime(){
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormatForChart = new SimpleDateFormat("MM/yy");
		
		ArrayList<Double> costValues = new ArrayList<Double>();
		ArrayList<String> dates = new ArrayList<String>();
		
		double maxMonthlyExpenditure = 0;
		
		for(int i = 0; i < 12; i++){
			Calendar cal = Calendar.getInstance();
			
			cal.add(Calendar.MONTH, -(i));
			String startDate = dateformat.format(cal.getTime());
			
			
			cal.add(Calendar.MONTH, (1));
			String endDate = dateformat.format(cal.getTime());
			dates.add(dateFormatForChart.format(cal.getTime()));
			
			ArrayList<Mail> mailOfMonth = XMLWorker.getMailBetweenDates(mail, startDate, endDate);
			ArrayList<Mail> parcelOfMonth = XMLWorker.getMailBetweenDates(parcel, startDate, endDate);
			
			
			double monthSales = 0;
			
			for(Mail m : mailOfMonth){
				monthSales = monthSales + m.getCost();
			}
			for(Mail m : parcelOfMonth){
				monthSales = monthSales + m.getCost();
			}
			
			costValues.add(monthSales);
			if(monthSales>maxMonthlyExpenditure)
				maxMonthlyExpenditure = monthSales;
			
		}
		
		Data d = DataUtil.scale(costValues);
		
		Line line = Plots.newLine(d);
		line.setColor(BLUE);
		line.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
		line.addShapeMarkers(Shape.DIAMOND, BLACK, 8);
		
		LineChart chart = GCharts.newLineChart(line);
		
		
		AxisLabels xAxis = AxisLabelsFactory.newAxisLabels(dates);
		chart.addXAxisLabels(xAxis);
		
		AxisLabels yAxis = AxisLabelsFactory.newNumericRangeAxisLabels(0, maxMonthlyExpenditure);
		chart.addYAxisLabels(yAxis);
		
		chart.setTitle("KPS Expenditure Per Month", BLACK, 16);
        chart.setSize(400, 230);
        
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
