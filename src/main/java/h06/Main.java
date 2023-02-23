package h06;

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
        /*System.out.println("Hello World!");
        int a = 5;
        int b = -3;
        double[] array = {1, 2, 3, 4, 5, 2};

        //System.out.println(StrangeFunctions.strangeFunction1(a,b));
        //System.out.println(StrangeFunctions.strangeFunction2(a,b));

        StrangeFunctions.transformArray1(array);
        for(int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }
        StrangeFunctions.transformArray2(array);
        for(int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }*/
        //BracketExpression h = new BracketExpression("((({[]})))sdsafsa");
        BracketExpression h = new BracketExpression("()");
        EvaluationResult result = h.evaluate();
        System.out.printf("%s %s %n",result.type(), result.nextIndex());
        //test code does test code things
    }
}
