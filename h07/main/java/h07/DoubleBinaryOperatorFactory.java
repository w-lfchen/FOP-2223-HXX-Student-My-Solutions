package h07;

import h07.doubleoperators.*;

import java.util.function.DoubleBinaryOperator;

import static java.lang.Math.sqrt;

/**
 * Class to build an operator with the buildOperator()-method
 */
public class DoubleBinaryOperatorFactory {

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in DoubleSumWithCoefficientsOp. If the object encapsulates
     * a PairOfDoubleCoefficients, the coefficients for the expression are taken
     * directly from said object.
     *
     * @param obj The object specifying the operation.
     * @return The lambda-expression.
     */
    private static DoubleBinaryOperator doubleSumWithCoefficientsOpAsLambda(Object obj) {
        if (obj instanceof PairOfDoubleCoefficients castedObj) { // this immediate downcast is an incredible feature, I will use it a lot.
            return (left, right) -> {
                return (left * castedObj.coeff1 + right * castedObj.coeff2);
            }; // IntelliJ makes this return grey to signal that the simple lambda form would be better. we have to ignore that.
        } else { // this else is unnecessary, just like the braces I put everywhere... I like them for added clarity.
            return null;
        }
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in EuclideanNorm.
     *
     * @return The lambda-expression
     */
    private static DoubleBinaryOperator euclideanNormAsLambda() {
        return (left, right) -> {
            return sqrt((left * left) + (right * right));
        }; // IntelliJ doesn't know that we are required to use standard form. Ignore the IDE.
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in DoubleMaxOfTwo. If the object encapsulates a Boolean and if
     * said Boolean encapsulates true, a lambda-expression is returned. If it encapsulates
     * false a method reference is returned.
     *
     * @param obj The object specifying the operation.
     * @return The lambda-expression.
     */
    private static DoubleBinaryOperator doubleMaxOfTwoAsLambda(Object obj) {
        if (obj instanceof Boolean bool) { // we cast to bool
            if (bool) { // I chose the names left and right to copy the applyAsDouble method parameters. x and y or anything else would work just as well.
                return (left, right) -> (left < right ? right : left); // ignore IDE
            } else {
                return Math::max; // this is neat.
            }
        } else {
            return null;
        }
    }

    /**
     * Returns a lambda-expression that is logically equivalent to the implementation
     * of applyAsDouble in ComposedDoubleBinaryOperator. If the object encapsulates
     * a TripleOfDoubleBinaryOperators, the operators for the expression are taken
     * directly from said object.
     *
     * @param obj The object specifying the operation.
     * @return The lambda-expression.
     */
    private static DoubleBinaryOperator composedDoubleBinaryOperatorAsLambda(Object obj) {
        if (obj instanceof TripleOfDoubleBinaryOperators castedObj) { // I could choose a better name, but I do not care.
            return (left, right) -> (castedObj.operator3.applyAsDouble(castedObj.operator1.applyAsDouble(left, right), castedObj.operator2.applyAsDouble(left, right)));
        } else { // there is not a lot to document/comment here.
            return null;
        }
    }

    /**
     * Returns an operator depending on the given input parameters.
     *
     * @param str  The type of the operator.
     * @param obj  The (optional) features of the operator.
     * @param bool The style of operator creation.
     * @return The operator.
     */
    public static Object buildOperator(String str, Object obj, boolean bool) {
        if (str.equals("Coeffs") || str.equals("Euclidean") || str.equals("Max") || str.equals("Composed")) { // I am uncertain whether we should use contains or equals here...
            return bool ? buildOperatorWithNew(str, obj) : buildOperatorWithLambda(str, obj);
        } else {
            return null;
        }
    }

    /**
     * Returns an operator that is created solely by using new.
     * mposed")){
     * if(bool){
     *
     * @param str The type of the operator.
     * @param obj The (optional) features of the operator.
     * @return The operator.
     */
    private static Object buildOperatorWithNew(String str, Object obj) {
        return switch (str) { // I love return switches.
            case "Coeffs" ->
                obj instanceof PairOfDoubleCoefficients castedObj ? new DoubleSumWithCoefficientsOp(castedObj.coeff1, castedObj.coeff2) : null;
            case "Euclidean" -> new EuclideanNorm();
            case "Max" -> new DoubleMaxOfTwo();
            case "Composed" ->
                obj instanceof TripleOfDoubleBinaryOperators castedObj ? new ComposedDoubleBinaryOperator(castedObj.operator1, castedObj.operator2, castedObj.operator3) : null;
            default -> null;
        }; // this default case returns something just for the sake of it and so that this project compiles. It should never be reached.
    }

    /**
     * Returns an operator that is created solely by using a lambda-expression.
     *
     * @param str The type of the operator.
     * @param obj The (optional) features of the operator.
     * @return The operator.
     */
    private static Object buildOperatorWithLambda(String str, Object obj) {
        return switch (str) { // ♡ return switches ♡
            case "Coeffs" -> doubleSumWithCoefficientsOpAsLambda(obj);
            case "Euclidean" -> euclideanNormAsLambda();
            case "Max" -> doubleMaxOfTwoAsLambda(obj);
            case "Composed" -> composedDoubleBinaryOperatorAsLambda(obj);
            default -> null; // once again, should not be reached
        };
    }
}
/* voluntary questions:
Controlled closure is happening. the lambda expression in buildOperatorWithLambda get objects that determine things for them.

the object might not be suited for the task, in the worst case this might lead to exceptions/errors.
I think that generics can help there.

instanceof also checks for subtypes.
String and Boolean are final.
If it is true, we have a guaranteed String/Boolean object.
 */
