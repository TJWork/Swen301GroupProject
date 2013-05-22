package UserInterface;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.googlecode.charts4j.*;

public class BusinessStats extends JPanel{

	public BusinessStats(){
		setBackground(Color.WHITE);

		try {
			add(totalSales());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public JLabel totalSales() throws Exception{
		 // EXAMPLE CODE START
        Plot plot = Plots.newPlot(Data.newData(0, 66.6, 33.3, 100));
        LineChart chart = GCharts.newLineChart(plot);
  
        URL url = new URL(chart.toURLString());
        BufferedImage bimg = ImageIO.read(url);
        Icon icon = new ImageIcon(bimg);

        JLabel label = new JLabel(icon);
        
        return label;

	}
	
}
