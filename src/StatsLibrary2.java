import java.math.BigInteger;
public class StatsLibrary2
{
    public double hyperGeometric(int K, int k, int N, int n)
    {
        StatsLibrary Statslib = new StatsLibrary();
        BigInteger numerator =  Statslib.combination(K, k).multiply(Statslib.combination(N-K, n-k));
        BigInteger denominator =  Statslib.combination(N, n);

        return numerator.doubleValue()/denominator.doubleValue();
    }
    public double poissonDistribution(double lambda, int y)
    {
        StatsLibrary Statslib = new StatsLibrary();
        BigInteger Y = BigInteger.valueOf(y);
        return ((Math.pow(lambda, y) / Statslib.factorial(Y).doubleValue())*Math.pow(Math.E, -lambda));
    }
    public double tchebysheffsTheorem(double k)
    {
        //1-(1/k^2)
        return (1-1/Math.pow(k, 2));
    }
}
