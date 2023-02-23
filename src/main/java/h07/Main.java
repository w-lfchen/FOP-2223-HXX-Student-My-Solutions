package h07;

import h07.arrayoperators.ReduceDoubleArray;

import static java.lang.Double.NaN;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello World!"); // test code after this
        double[] testArray = new double[]{5,4,2,NaN,56,-43.24,3234,2,4.35,-56}; // test code for H1.1, might remove some of this
        ReduceDoubleArray reduceDoubleArray = new ReduceDoubleArray((x) -> 0 > x || Double.isNaN(x));
        double[] result = reduceDoubleArray.applyAsDoubleArray(testArray);
        for(int i = 0;i < result.length;i++){
            System.out.println(result[i]);
            if(i == result.length-1)
                System.out.println(i+1);
        }
    }
}
