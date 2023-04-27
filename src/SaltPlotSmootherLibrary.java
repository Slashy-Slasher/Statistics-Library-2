import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alexander
 * @date 4/24/2023
 * @sources  https://zetcode.com/java/jfreechart/
 */
public class SaltPlotSmootherLibrary
{
    /**
     * This method is used to create JFreeCharts during runtime using their API
     * @param title This string value dictates the name of the Chart created
     * @param arr this arr is used for the y values of the chart
     */
    public void createChartFile(String title, int start, int end, ArrayList<Double> arr)
    {
        XYSeries series = new XYSeries("My Series");

        for(int i = 0; i < arr.size(); i++)
        {
            series.add(start+i, arr.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title, // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );
        // display the chart in a frame
        ChartFrame frame = new ChartFrame(title, chart);
        frame.pack();
        frame.setVisible(true);

        File file = new File(title+".png");
        try
        {
            FileOutputStream fos = new FileOutputStream(file);

            ChartUtilities.writeChartAsPNG(fos,chart, frame.getWidth(), frame.getHeight());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is a helper method that I made that will fill a double type arrayList with values from the given .csv file
     * @param pathname The location of the file you want to read
     * @return a type double arraylist that contains all the cleaned values in the csv file
     */
    public ArrayList<Double> csvCleaner(String pathname)
    {
        ArrayList<Double> csvScrapper = new ArrayList<>();
        ArrayList<String> csvHolder = new ArrayList<>();
        String[] csvhol = {};
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File(pathname));
        }
        catch (FileNotFoundException e) {}

        sc.useDelimiter(","); //sets the delimiter pattern

        while (sc.hasNext())  //returns a boolean value
        {
            csvhol = sc.nextLine().split(",");

            for(int i = 0; i<csvhol.length; i++)
            {
                //System.out.println(csvhol[i]);
                csvHolder.add(csvhol[i]);
            }
        }
        sc.close();  //closes the scanner
        for(int i = 2; i<csvHolder.size(); i++)
        {
            if(i % 2 == 1)
            {
                //System.out.println("Saved the double to the list");
                csvScrapper.add(Double.parseDouble(csvHolder.get(i)));
            }
        }
        return csvScrapper;
    }

    /**
     * This method is a helper method that turns an arraylist of type double into a .csv, this goes along with the
     * .csv cleaner method to allow for the quick transfer of data from memory to storage.
     * @param start start of the array
     * @param end end of the array
     * @param column Represents the Y column of the .csv, takes in the values of the array
     * @param name pathname of the new file to be created
     */
    public void csvCreator(int start, int end, ArrayList<Double> column, String name)
    {
        double y = 0;
        try
        {
            File myObj = new File(name);
            if (myObj.createNewFile())
            {
                System.out.println("File created: " + myObj.getName());
            }
            else
            {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try
        {
            FileWriter myWriter = new FileWriter(name);
            myWriter.write("x,y," + "\n");
            int x = 0;
            for (int i = start; i < end; i++)
            {           //Fix this
                y = column.get(x);
                myWriter.write(i + "," + y + "\n");
                x++;
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
