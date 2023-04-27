import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//The JFreeCharts components are inside the SaltPlotSmootherLibrary class

/**
 * USING APACHE COMMONS MATH 3.6.1 && JFREECHART- 1.0.19 && jcommon- 1.0.23
 *
 * @author Alexander Mortillite
 * @date 4/20/2023
 * @sources https://commons.apache.org/proper/commons-math/userguide/stat.html
 */
public class SaltPlotSmootherAPI
{
    /**
     * Tester Method to show the capabilities of this program to output data to .csvs
     * @param lowerBound lower bound of the x-coordinates
     * @param upperBound upper bound of the x-coordinates
     */
    public void backEndTester(int lowerBound, int upperBound, int saltAmount, int windowSize, int grit)
    {
        standardCsv("Standard Quadratic API.csv", lowerBound, upperBound);
        saltCSV("Standard Quadratic API.csv","Quadratic", lowerBound, upperBound, saltAmount);
        smoothCSV("Salted Quadratic API.csv", "Quadratic", windowSize, grit, lowerBound, upperBound);
    }

    /**
     * This tester method shows the program's ability to graph different .csv files using jfreechart
     * In this example I use a Standard Graph, its salted form then subsequently its smoothed form. It also
     * demonstrates some usage of my csvCleaner Helper method.
     * @param standard This is filepath of standard graph, could be anything technically
     * @param salted This is filepath of salted graph, could be anything technically
     * @param smoothed This is filepath of smoothed graph, could be anything technically
     */
    public void frontEndTester(int start, int end, String standard, String salted, String smoothed)
    {
        SaltPlotSmootherLibrary SpsL = new SaltPlotSmootherLibrary();
        ArrayList<Double> arr = new ArrayList<>(SpsL.csvCleaner(standard));
        SpsL.createChartFile(standard,start, end, arr);
        arr.clear();
        arr = SpsL.csvCleaner(salted);
        SpsL.createChartFile(salted,start, end, arr);
        arr.clear();
        arr = SpsL.csvCleaner(smoothed);
        SpsL.createChartFile(smoothed, start, end, arr);
    }

    /**
     * Method that generates the Standard graph of the quadratic equation, nothing fancy
     * @param inputX Lower bound of the X-axis
     * @param inputZ Upper bound of the X-axis
     */
    public void standardCsv(String outputPathname, int inputX, int inputZ)
    {
        try
        {
            FileWriter myWriter = new FileWriter(outputPathname);
            myWriter.write("x,y,"+"\n");
            double y = 0;
            for (int i = inputX-1; i < inputZ-1; i++)
            {
                y = Math.pow(i, 2) + (2 * i) + 1;
                myWriter.write((i+1)+ "," + y + "\n");
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

    /**
     * Method that generates the Salted graph based on a file location
     * @param pathname Pathname of any file that wishes to be salted
     * @param lowerBound Lower bound of the x-axis
     * @param upperBound upper bound of the x-axis
     * @param x amount of variance in the salting, higher values = larger variance
     */
    public void saltCSV(String pathname, String outputName, int lowerBound, int upperBound, int x)
    {
        SaltPlotSmootherLibrary SpsL = new SaltPlotSmootherLibrary();
        ArrayList<Double> arr = new ArrayList<>(SpsL.csvCleaner(pathname));
        double y;
        for(int i = 0; i<arr.size(); i++)
        {
            double random;
            random = Math.random();
            if(random > (.50))
            {
                y = (int)(Math.random()*x)+arr.get(i);
            }
            else
            {
                y = (int)(-1 * Math.random()*x)+arr.get(i);
            }
            arr.set(i, y);
        }
        SpsL.csvCreator(lowerBound, upperBound, arr,"Salted " + outputName + " API.csv");
    }

    /**
     * This method smooths the contents of a .csv file given the location,the windowSize, the grit, the lowerBound and the upperBound
     * @param pathname   takes in this pathname for y-values
     * @param windowSize size of the averaging window, higher values introduce strange results
     * @param grit       Number of times that the program with run through the smoothing function
     * @param lowerBound Lower x-axis bound
     * @param upperBound Upper x-axis bound
     */
    public void smoothCSV(String pathname, String outputName, int windowSize, int grit, int lowerBound, int upperBound)
    {
        SaltPlotSmootherLibrary SpsL = new SaltPlotSmootherLibrary();
        ArrayList<Double> arr;
        ArrayList<Double> arr2 = new ArrayList<>();
        arr = SpsL.csvCleaner(pathname);

        DescriptiveStatistics stats = new DescriptiveStatistics();
        stats.setWindowSize(windowSize);

        for(int i = 0; i < grit; i++)
        {
            for(int j = 0; j < arr.size(); j++)
            {
                stats.addValue(arr.get(j));
                arr2.add(stats.getMean());
            }
            arr.clear();
            arr.addAll(arr2);
            arr2.clear();
        }
        SpsL.csvCreator(lowerBound, upperBound, arr, "Smoothed "+outputName+" API.csv");
    }
}
