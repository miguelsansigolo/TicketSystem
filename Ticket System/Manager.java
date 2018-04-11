import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Manager extends ApplicationFrame {
   final DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
   
   public Manager( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Score",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel(barChart);        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane(chartPanel); 
      pack();
      RefineryUtilities.centerFrameOnScreen(this);
      setVisible(true);
   }
   
   private CategoryDataset createDataset() {
      final String total = "Total Tickets";
      final String open = "Open Tickets";
      final String closed = "Closed Tickets";
      final String cost = "Cost Tickets";                
      return dataset; 
   }

}