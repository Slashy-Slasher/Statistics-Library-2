/**
 * @author Alexander
 * @date 4/25/23
 */
public class Tester
{
    /**
     * Entrance of the program
     * @param args input if the program was executed through commandline
     */
    public static void main(String[] args)
    {
        Tester Main = new Tester();
        Main.tester();
    }

    /**
     * This is the main class for demonstrating program functionality
     */
    public void tester()
    {
        //Please comment everything but what you want to run, running everything together may cause problems

        //testStatsLibrary2(5, 2, 15, 5, 2,5,1.45);
        //SaltPlotSmootherTest(-10, 10+1);
        //SaltPlotSmootherAPITest(-100, 100+1, 500, 2, 5);
        startGUI();

    }

    /**
     *  Test the StatsLibrary2 class and displays formatted text for the user to see
     * @param upperCaseK
     * @param k
     * @param upperCaseN
     * @param n
     * @param lambda
     * @param x
     * @param constant
     */
    public void testStatsLibrary2(int upperCaseK, int k, int upperCaseN, int n, double lambda, int x, double constant)
    {
        StatsLibrary2 Stats = new StatsLibrary2();
        System.out.printf("There is a probability of %.5f of %d successes within a population size of %d  \n",Stats.hyperGeometric(upperCaseK, k, upperCaseN, n), n, k);
        System.out.printf("There is a probability of %.5f of %.2f occurrences within the next %d time-units \n", Stats.poissonDistribution(lambda, x), lambda, x);
        System.out.printf("There is a probability of %.5f of all data values will lie within %2.2f deviations of the mean\n", Stats.tchebysheffsTheorem(constant), constant);
    }

    /**
     * Tests the non-api version of the SaltPlotSmoother, demonstrates functionality by creating 3 .csv files
     * @param lowerBound lower bound on the x-axis
     * @param upperBound Upper bound on the x-axis
     */
    public void SaltPlotSmootherTest(int lowerBound, int upperBound)
    {
        SaltPlotSmoother Sps = new SaltPlotSmoother();
        Sps.demo(lowerBound, upperBound);
    }

    /**
     * Tests the api version of the SaltPlotSmoother, demonstrates functionality by creating 3 .csv files as well as
     * 3 .png files to keep with the style of saving data post runtime
     * @param lowerBound lower bound on the x-axis
     * @param upperBound Upper bound on the x-axis
     * @param saltIntensity Intensity of the salting for the Y-Values
     * @param windowSize Size of the averaging window for smoothing, higher = more interesting results
     * @param grit Amount of times the smoother runs over the graph, higher = flatter result
     */
    public void SaltPlotSmootherAPITest(int lowerBound, int upperBound, int saltIntensity, int windowSize, int grit)
    {
        SaltPlotSmootherAPI SpsAPI = new SaltPlotSmootherAPI();
        SpsAPI.backEndTester(lowerBound, upperBound, saltIntensity, windowSize, grit);
        SpsAPI.frontEndTester(lowerBound, upperBound, "Standard Quadratic API.csv","Salted Quadratic API.csv","Smoothed Quadratic API.csv");
    }
    public void startGUI()
    {
        Controller controller = new Controller();
        controller.manipulator();
    }

}
