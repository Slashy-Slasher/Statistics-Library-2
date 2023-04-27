import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Helper class for finding Combinations in StatsLib2
 */
public class StatsLibrary
{
    /**
     * Adds up all the numbers in the ArrayList and return the average
     *
     * @param a
     * @return
     */
    public double mean(ArrayList<Integer> a)
    {
        double total = 0;
        for(int i = 0; i<a.size(); i++)
            total += a.get(i);
        //System.out.println(a);
        return (total/a.size());
    }
    /**
     * Takes an Arraylist of Integers
     * If even, take the middle two numbers and return the mean
     * If odd, return the middle number
     * @param a
     * @return returns the median
     */
    public double median(ArrayList<Integer> a)
    {
        double median = 0;
        ArrayList<Integer> middleTwo = new ArrayList<Integer>();
        if(a.size()%2 == 0)
        {
            middleTwo.add(a.get((a.size()/2)-1));
            middleTwo.add(a.get((a.size()/2)));
            System.out.println("The middle two numbers are: " + middleTwo);
            return(mean(middleTwo));
        }
        if(a.size()%2 == 1)
        {
            median = a.get(((int)(a.size()/2)));
        }
        return median;
    }

    /**
     * Finds the most common number in the array and returns it
     * If there is not a common number returns
     * @param a
     * @return
     */
    public Double mode(ArrayList<Integer> a)
    {
        Double mode;
        mode = null;
        int tally = 0;
        ArrayList<Integer> B = new ArrayList<Integer>();
        ArrayList<Integer> holder = new ArrayList<Integer>();

        for(int i = 0; i<a.size(); i++)
        {
            for(int j = 0; j<a.size(); j++)
            {
                if((a.get(i) == a.get(j))&&(i != j))
                {
                    if(!(B.contains(i)))
                    {
                        tally++;
                    }
                }
            }
            if(tally != 0)
            {
                B.add(a.get(i));
                holder.add(tally);
            }
            tally = 0;
        }
        for(int i = 0; i < B.size(); i++)
        {
            if(holder.get(i) == 0)
            {
                B.remove(i);
                holder.remove(i);
                i--;
            }
            sortAndTrim(B);
            sortAndTrim(holder);
            Collections.reverse(holder);
        }
        if(B.size() == 1)
        {
            mode = (double)B.get(0);
        }
        else if(B.size() > 1)
        {
            if(holder.get(0) == holder.get(1))
            {
                mode = null;
            }
            else
            {
                mode = (double)B.get(B.indexOf(Collections.max(holder)));
            }
        }
        return mode;
    }

    /**
     * Takes in an ArrayList and applies the standard deviation formula to it
     *
     * @param a
     * @return the result of the Standard Deviation
     */
    public double standardDeviation(ArrayList<Integer> a)
    {
        double placeholder = 0;
        for(int i = 0; i<a.size();i++)
        {
            placeholder += Math.pow(a.get(i) -  mean(a), 2);
        }
        return Math.sqrt(placeholder/a.size());
    }
    public ArrayList<Integer> ArrayListTrim()
    {
        ArrayList<Integer> set3 = new ArrayList<>();

        return set3;
    }

    /**
     * Tests to see if the result of the operation is identical to the expected result
     * Used for testing Set Operations like Union, Intersection, Compliment
     *
     * @param expected
     * @param result
     */
    public void testCase(String expected, String result)
    {
        String grade = "Failed-----------------";
        System.out.println("");
        System.out.println(expected + " vs " + result);
        System.out.println("");
        if(expected.equals(result))
            grade = "Pased+++++++++++++++++";

        System.out.println(grade);
        System.out.println("");
    }
    public ArrayList<Integer> sortAndTrim(ArrayList<Integer> A)
    {
        Collections.sort(A);
        for(int i = 1; i<A.size(); i++)
        {
            if(A.get(i) == A.get(i-1))
            {
                A.remove(i);
            }
        }
        return A;
    }

    public ArrayList<Integer>ArrayListClone(ArrayList<Integer> A)
    {
        ArrayList<Integer> B = new ArrayList<Integer>();
        for(int i = 0; i < A.size(); i++)
        {
            B.add(A.get(i));
        }
        return B;
    }

    /**
     * Unions the two parameters and returns them in a new array
     * @param A
     * @param B
     * @return
     */
    public ArrayList<Integer> union(ArrayList<Integer> A, ArrayList<Integer> B)
    {
        ArrayList<Integer> C = new ArrayList<Integer>();
        for(int i = 0; i<B.size(); i++)
        {
            C.add(B.get(i));
        }
        for(int i = 0; i<A.size(); i++)
        {
            C.add(A.get(i));
        }
        return sortAndTrim(C);
    }

    /**
     * Intersects the two parameters and returns them in a new array
     * @param set1
     * @param set2
     * @return
     */
    public ArrayList<Integer> intersection(ArrayList<Integer> set1, ArrayList<Integer> set2)
    {
        ArrayList<Integer> set3 = new ArrayList<Integer>();
        for(Integer i = 0; i < set1.size(); i++)
        {
            for(Integer j = 0; j<set2.size();j++)
            {
                //System.out.println(set1.get(i) + " vs " + set2.get(j));
                if(set1.get(i) == set2.get(j))
                {
                    set3.add(set1.get(i));
                    j = set2.size();
                }
            }
        }
        return set3;
    }

    /**
     * Complements the two parameters and returns them in a new array
     * @param A
     * @param B
     * @return
     */
    public ArrayList<Integer> complement(ArrayList<Integer> A, ArrayList<Integer> B)
    {
        ArrayList<Integer> C = new ArrayList<Integer>();

        for(int i = 0; i<B.size(); i++)
        {
            C.add(B.get(i));
        }
        for(int i = 0; i<A.size(); i++)
        {
            C.add(A.get(i));
        }
        Collections.sort(C);

        for(int i = 1; i<C.size(); i++)
        {
            if(C.get(i) == C.get(i-1))
            {
                C.remove(i);
                C.remove(i-1);
            }
        }
        //System.out.println("UNION OF A AND S" + C);
        return sortAndTrim(C);
    }

    /**
     * Performs the Combination function on two Integers
     * @param N
     * @param R
     * @return The combination of N and R
     */
    public BigInteger combination(int N, int R) {
        BigInteger r = BigInteger.valueOf(R);
        BigInteger n = BigInteger.valueOf(N);

        return (factorial(n).divide((factorial(r).multiply(factorial(n.subtract(r))))));
    }

    /**
     * Performs a Permutation function on two Integers
     * @param N
     * @param R
     * @return The Permutation of N and R
     */
    public BigInteger permutation(int N, int R) {
        BigInteger r = BigInteger.valueOf(R);
        BigInteger n = BigInteger.valueOf(N);

        return (factorial(n).divide(factorial(n.subtract(r))));
    }

    /**
     * Performs a Factorial Operation on x
     * @param x
     * @return the factorial of x
     */
    public BigInteger factorial(BigInteger x) {
        BigInteger y = BigInteger.valueOf(1);   // y = 1
        for (int i = 0; i < x.intValue() - 1; i++)
            y = y.multiply(x.subtract(BigInteger.valueOf(i)));
        return y;
    }

    /**
     * Performs a binomial Distribution with the parameters of n, y and p
     *
     * @param n
     * @param y
     * @param p
     * @return The Binomial Distribution
     */
    public double binomialDistribution(int n, int y, double p) {
        return (combination(n, y).doubleValue() * Math.pow(p, y) * Math.pow((1 - p), n - y));
    }

    /**
     * Performs an Enhanced binomial Distribution with the parameters of n, y and p
     * But also gives the option to set a range of numbers and Inverse the Percentage
     * @param n
     * @param y
     * @param p
     * @param x
     * @param inversed
     * @return
     */
    public double binomialDistribution(int n, int y, double p, int x, boolean inversed) {

        double combinedProb = 0;
        for (int i = 0; i < x + 1; i++) {
            combinedProb += combination(n, y + i).doubleValue() * Math.pow(p, y + i) * Math.pow((1 - p), n - (y + i));
            //System.out.println(n +"C"+ (y+i) + " times " + (p) +" to the "+ (y+i) + " times " + (1-p) +" to the "+ (n-(y+i)));
            //System.out.println(i+y+1 + ": combined prob: " + combination(n,y+i).doubleValue()*Math.pow(p,y+i)*Math.pow((1-p), n-(y+i)));
        }

        if (inversed)
            combinedProb = (1 - combinedProb);
        return (combinedProb);
    }

    /**
     *  Performs an Enhanced Geometric Distribution with the parameters of n, y and p
     * 	 But also gives the option to set a range of numbers and Inverse the Percentage
     * @param y
     * @param p
     * @param x
     * @param inversed
     * @return
     */
    public static double geometricDistribution(int y, double p, int x, boolean inversed)
    {
        double combinedProb = 0;

        combinedProb = Math.pow((1-p),(y))*(p);
        return combinedProb;
    }

}