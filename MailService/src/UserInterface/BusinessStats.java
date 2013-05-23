package UserInterface;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

import static com.googlecode.charts4j.Color.*;

public class BusinessStats extends JPanel{

	private JLabel salesChart;
	
	public BusinessStats(){
		
		setBackground(Color.WHITE);
		JLabel salesChart = sales();

        
		add(salesChart);
	}
	
	public JLabel sales(){
        Slice s1 = Slice.newSlice(90, YELLOW, "Ms. Pac-Man");
        Slice s2 = Slice.newSlice(10, RED, "Red Lips");

        PieChart chart = GCharts.newPieChart(s1, s2);
        chart.setTitle("2D Pie Chart", BLACK, 16);
        chart.setSize(500, 200);

        chart.setTitle("A Better Web", BLACK, 16);
        chart.setSize(500, 200);
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
	
}
