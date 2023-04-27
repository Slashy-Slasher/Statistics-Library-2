import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//Sources:
//https://www.w3schools.com/java/java_files_create.asp
//https://www.javatpoint.com/how-to-read-csv-file-in-java

/**
 * @author Alexander Mortillite
 * @Date 4/14/2023
 *
 * This program is designed to intake two parameters from the user and create .csv files after performing various
 * actions to the graph created by the upper and lower bounds
 *
 *
 */
public class SaltPlotSmoother
{
    //f(y) = x^2+2x+1
    SaltPlotSmootherLibrary SpsL = new SaltPlotSmootherLibrary();

    /**
     * This demo class is a quick demonstration of what the code can do, it creates a .csv file using the formula,
     * salts the values, by converting that .csv back into an array, then adding the noise. Finally the graph Smoother
     * smooths the array and once again spits out a .csv file
     * @param lowerBound lowerBound of the graph
     * @param UpperBound upperBound of the graph
     */
    public void demo(int lowerBound, int UpperBound)
    {
        createCSV(lowerBound, UpperBound);                          //Creates the base csv that is referenced by the salter
        saltCSV("Standard Quadratic.csv", lowerBound, UpperBound, 50);                      //lowerBound UpperBound and the level of randomization present in the salting
        graphSmoother("Salted Quadratic.csv", lowerBound, UpperBound,2,3);//Reads the values of the salted graph then runs a smoothing alg
    }

    /**
     * This method sets up a .csv of data-points of the quadratic formula, based on the two upper and lower bounds given
     * @param lowerBound    lowerBound of the graph
     * @param upperBound    upperBound of the graph
     */
    public void createCSV(int lowerBound, int upperBound)
    {
        try
        {
            FileWriter myWriter = new FileWriter("Standard Quadratic.csv");
            myWriter.write("x,y,"+"\n");
            double y = 0;

            for (int i = lowerBound-1; i < upperBound-1; i++)
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
     *
     * @param pathname      Location of the .csv to be salted
     * @param lowerBound    lowerBound of the graph
     * @param UpperBound    upperBound of the graph
     * @param x             salting intensity value
     */
    public void saltCSV(String pathname, int lowerBound, int UpperBound, int x)
    {
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
        SpsL.csvCreator(lowerBound, UpperBound, arr,"Salted Quadratic.csv");
    }


    /**
     * This method smooths the data in a given .csv file
     * @param lowerBound        lowerBound of the graph
     * @param UpperBound        upperBound of the graph
     * @param smoothingSteps    Size of the smoothing "Window"
     * @param grit              Number of time the smoother will be run
     */
    public void graphSmoother(String pathname, int lowerBound, int UpperBound, int smoothingSteps, int grit)
    {
        ArrayList<Double> cleanedList;
        ArrayList<Double> smoothedList = new ArrayList<>();
        cleanedList = SpsL.csvCleaner(pathname);
        double total = 0;
        boolean flag = false;
        int tempSteps = 0;

        for(int k = 0; k < grit; k++)
        {
            for(int i = 0; i < cleanedList.size(); i++)
            {

                    if(i == 0)     //Case at the beginning of the loop, will only scan right for average
                    {
                        for(int j = 0; j < smoothingSteps; j++)
                        {
                            total += cleanedList.get(i+j);
                            tempSteps++;

                        }
                        flag = true;

                    }
                    else if(i == cleanedList.size()-1)//Case at the UpperBound of the loop, will only scan to the left for average
                    {
                        for(int j = smoothingSteps; j > 0; j--)
                        {
                            total += cleanedList.get(i-j);
                            tempSteps++;

                        }
                        flag = true;
                    }
                    if (!flag)        //If neither case occurs, will scan left and right for the average
                    {
                        try
                        {
                            for(int j = 0; j < smoothingSteps; j++)
                            {
                                total += cleanedList.get(i+j);
                                tempSteps++;
                            }
                        }
                        catch (Exception e) {}
                        try
                        {
                            for(int j = smoothingSteps+1; j > 0; j--)
                            {
                                total += cleanedList.get(i-j);
                                tempSteps++;
                            }

                        }
                        catch (Exception e) {}

                    }
                smoothedList.add(total/tempSteps);
                tempSteps = 0;
                total = 0;
                flag = false;
            }
            cleanedList.clear();
            cleanedList.addAll(smoothedList);
            if(k != grit -1)
            {
                smoothedList.clear();
            }
        }
        SpsL.csvCreator(lowerBound, UpperBound, smoothedList, "Smoothed Quadratic.csv");

    }
}