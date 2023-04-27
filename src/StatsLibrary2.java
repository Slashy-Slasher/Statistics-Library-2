import java.math.BigInteger;

/**
 * @author Alexander Mortillite
 * This is the StatsLibrary Class, it is used for rapidly solving different types of non-calculus based
 * statistics problems, Featuring the Hyper Geometric Formula, Poisson Distribution and ChebySheff's Theorem
 */
public class StatsLibrary2
{
    /**
     *
     *
     * @param K         input for K
     * @param k         input for k
     * @param N         input for N
     * @param n         input for n
     * @return          returns double containing answer
     */
    public double hyperGeometric(int K, int k, int N, int n)
    {
        StatsLibrary StatsLib = new StatsLibrary();
        BigInteger numerator =  StatsLib.combination(K, k).multiply(StatsLib.combination(N-K, n-k));
        BigInteger denominator =  StatsLib.combination(N, n);

        return numerator.doubleValue()/denominator.doubleValue();
    }

    /**
     *
     * @param lambda    Input for Lambda
     * @param y         Input for y
     * @return          returns double containing answer
     */
    public double poissonDistribution(double lambda, int y)
    {
        StatsLibrary StatsLib = new StatsLibrary();
        BigInteger Y = BigInteger.valueOf(y);
        return ((Math.pow(lambda, y) / StatsLib.factorial(Y).doubleValue())*Math.pow(Math.E, -lambda));
    }

    /**
     *
     * @param k         Input for k
     * @return          returns double containing answer
     */
    public double chebysheffsTheorem(double k)
    {
        return (1-1/Math.pow(k, 2));
    }
}
