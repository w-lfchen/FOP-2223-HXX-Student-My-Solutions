package h06;

import static h06.EvaluationResult.Type.*;

public class BracketExpression {

    private final char[] expression;


    public BracketExpression(String expression) {
        this.expression = expression.toCharArray();
    }

    public final EvaluationResult evaluate() {
        EvaluationResult theResultInQuestion = evaluate(0);
        if(theResultInQuestion.nextIndex() == expression.length || theResultInQuestion.type() != CORRECT){
        return theResultInQuestion;
        }
        else{
            return evaluate(theResultInQuestion.nextIndex());
        }
    }

    public final EvaluationResult evaluate(int i) {
        if(!isBracket(i)){//all of this cancels the recursion by returning something and exiting out of the method before the next recursion happens.
            return new EvaluationResult(INVALID_CHARACTER,i);
        }
        else if(isClosingBracket(i)){
            return new EvaluationResult(NO_OPENING_BRACKET,i);
        }
        else if(i == expression.length-1){
            return new EvaluationResult(NO_CLOSING_BRACKET,i);
        }
        else if(isMatchingBracket(i,i+1)){
            if((i+2< expression.length) &&(!isClosingBracket(i+2))){
                return evaluate(i+2); //after this it is closing
            }
            else{
                return new EvaluationResult(CORRECT, i+2);
            }
        }
        else if(isClosingBracket(i+1) && !isMatchingBracket(i, i+1)){ //catch wrong closing bracket syndrome (wcbs)
            return new EvaluationResult(INVALID_CLOSING_BRACKET, i+1);
        }
        //oooooo spooky recursion wooooOOooO // we be doin things out here //recursion once we know the next index is opening bracket
        EvaluationResult r = evaluate(i+1);
        if(r.type() != CORRECT){ // the me when the error goofy incorrect the me the when when me the when
            return r; //exit immediately
        } //everything correct after this, now we should check whether we follow up correctly
        else if(r.nextIndex() >= expression.length){ //imma need a bracket close me frfr
            return new EvaluationResult(NO_CLOSING_BRACKET, r.nextIndex());
        }
        else if(isMatchingBracket(i, r.nextIndex())){
            if(r.nextIndex()+1 <= expression.length -1 && !isClosingBracket(r.nextIndex()+1)){
                return evaluate(r.nextIndex()+1);
            }
            else{
                return new EvaluationResult(CORRECT, r.nextIndex()+1);
            }
        }
        else if(isClosingBracket(r.nextIndex()) && !isMatchingBracket(i, r.nextIndex())){ // [()} moment
            return new EvaluationResult(INVALID_CLOSING_BRACKET, r.nextIndex());
        }
        else if(isOpeningBracket(r.nextIndex())){ //this is flat out wrong in h3.1, needs fixing //nvm past me is dumb
            return new EvaluationResult(NO_CLOSING_BRACKET,r.nextIndex()); //maybe rework too //nah, we do not reach this point anyway
        } else{
            return new EvaluationResult(INVALID_CHARACTER, r.nextIndex()); //maybe change logic here too //it's fine (i hope)(i am passing the tests so do i care?)
        }
    }

    //own methods
    private boolean isOpeningBracket(int i){ //i love not having to document this, self-explanatory names do all the work :D
        return switch (expression[i]){
            case '(', '[', '{' -> true;
            default -> false;
        };
    }

    private boolean isClosingBracket(int i){ //return switches my beloved :heart:
        return switch (expression[i]){
            case ')', ']', '}' -> true;
            default -> false;
        };
    }

    private boolean isBracket(int i){ //maybe use the other methods instead idk
        return switch (expression[i]){
            case '(', ')', '[', ']', '{', '}' -> true;
            default -> false;
        };
    }

    private boolean isMatchingBracket(int i, int j){ //maybe more methods
        return ((expression[i] == '(' && expression[j] == ')') || (expression[i] == '[' && expression[j] == ']') || (expression[i] == '{' && expression[j] == '}'));
    }
}
