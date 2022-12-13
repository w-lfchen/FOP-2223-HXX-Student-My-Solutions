package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import fopbot.World;

import java.util.concurrent.ThreadLocalRandom;
import org.tudalgo.algoutils.student.Student;

import static fopbot.Direction.*;
import static org.tudalgo.algoutils.student.io.PropertyUtils.getIntProperty;

/**
 * {@link Checkers} is a simplified version of Checkers, implemented in FOPBot.
 */
public class Checkers {

    /**
     * The number of rows in the game board.
     */
    public static final int NUMBER_OF_ROWS = getIntProperty("checkers.properties", "NUMBER_OF_ROWS");

    /**
     * The number of columns in the game board.
     */
    public static final int NUMBER_OF_COLUMNS = getIntProperty("checkers.properties", "NUMBER_OF_COLUMNS");

    /**
     * The minimum initial number of coins for a black stone.
     */
    public static final int MIN_NUMBER_OF_COINS = getIntProperty("checkers.properties", "MIN_NUMBER_OF_COINS");

    /**
     * The maximum initial number of coins for a black stone.
     */
    public static final int MAX_NUMBER_OF_COINS = getIntProperty("checkers.properties", "MAX_NUMBER_OF_COINS");

    /**
     * The current state of the game.
     * At the start of the game, the state of the game is set to {@link GameState#RUNNING}.
     * After the game has finished, the state of the game is set to {@link GameState#BLACK_WIN} or {@link GameState#WHITE_WIN}.
     */
    private GameState gameState = GameState.RUNNING;


    /**
     * The robot of the white team.
     */
    private Robot whiteStone;

    /**
     * The robots of the black team.
     */
    private Robot blackStone0, blackStone1, blackStone2, blackStone3, blackStone4;

    /**
     * Runs the initialization of the game.
     * The initialization of the game consists of the initialization of the world and all stones.
     */
    public void initGame() {
        Student.setCrashEnabled(false);
        // initialize the world
        World.setSize(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
        // initialize all stones
        initWhiteStone();
        initBlackStones();
        //World.setDelay(500);
    }
    /**
     * Runs the game. After the game has finished, the winner of the game will be printed to the console.
     */
    public void runGame() {
        World.setVisible(true);
        while (isRunning()) {
            doBlackTeamActions();
            doWhiteTeamActions();
            updateGameState();
        }
        System.out.printf("Final State: %s%n", gameState);
    }

    /**
     * Returns {@code true} if the game is running, {@code false} otherwise.
     *
     * @return if the game is running
     */
    public boolean isRunning() {
        return gameState == GameState.RUNNING;
    }

    /**
     * Runs the initialization of the white stone.
     */
    public void initWhiteStone() {
        int whiteX;
        int whiteY;
        do {
            whiteX = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_COLUMNS);
            whiteY = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_ROWS);
        }while((whiteX+whiteY)%2 == 0); //gerade oder ungerade
        Direction randomDirection = getRandomDirection();
        whiteStone = new Robot(whiteX, whiteY, randomDirection, 0, RobotFamily.SQUARE_WHITE);
    }

    /**
     * Runs the initialization of all black stones.
     */
    public void initBlackStones() {
        int whiteX = whiteStone.getX();
        int whiteY = whiteStone.getY();
        int blackX;
        int blackY;
        do {
            blackX = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_COLUMNS);
            blackY = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_ROWS);
        }while((blackX+blackY)%2 == 0 || (whiteX == blackX && whiteY == blackY));
        Direction randomDirection = getRandomDirection();
        int numberOfCoins = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS+1);
        blackStone0 = new Robot(blackX, blackY, randomDirection, numberOfCoins, RobotFamily.SQUARE_BLACK);
        do {
            blackX = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_COLUMNS);
            blackY = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_ROWS);
        }while((blackX+blackY)%2 == 0 || (whiteX == blackX && whiteY == blackY));
        randomDirection = getRandomDirection();
        numberOfCoins = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS+1);
        blackStone1 = new Robot(blackX, blackY, randomDirection, numberOfCoins, RobotFamily.SQUARE_BLACK);
        do {
            blackX = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_COLUMNS);
            blackY = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_ROWS);
        }while((blackX+blackY)%2 == 0 || (whiteX == blackX && whiteY == blackY));
        randomDirection =getRandomDirection();
        numberOfCoins = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS+1);
        blackStone2 = new Robot(blackX, blackY, randomDirection, numberOfCoins, RobotFamily.SQUARE_BLACK);
        do {
            blackX = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_COLUMNS);
            blackY = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_ROWS);
        }while((blackX+blackY)%2 == 0 || (whiteX == blackX && whiteY == blackY));
        randomDirection = getRandomDirection();
        numberOfCoins = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS+1);
        blackStone3 = new Robot(blackX, blackY, randomDirection, numberOfCoins, RobotFamily.SQUARE_BLACK);
        do {
            blackX = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_COLUMNS);
            blackY = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_ROWS);
        }while((blackX+blackY)%2 == 0 || (whiteX == blackX && whiteY == blackY));
        randomDirection = getRandomDirection();
        numberOfCoins = ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_COINS,MAX_NUMBER_OF_COINS+1);
        blackStone4 = new Robot(blackX, blackY, randomDirection, numberOfCoins, RobotFamily.SQUARE_BLACK);
    } //could be optimised, could also get sleep, wrote this code while being scared of arrays

    /**
     * Runs the action of the black team.
     */
    public void doBlackTeamActions() {
        Robot chosenStone = blackStone0;
        int chosenStoneValue;
        do {
            chosenStoneValue = ThreadLocalRandom.current().nextInt(0, 5);
            if (chosenStoneValue == 0) {
                chosenStone = blackStone0;
            }
            if (chosenStoneValue == 1) {
                chosenStone = blackStone1;
            }
            if (chosenStoneValue == 2) {
                chosenStone = blackStone2;
            }
            if (chosenStoneValue == 3) {
                chosenStone = blackStone3;
            }
            if (chosenStoneValue == 4) {
                chosenStone = blackStone4;
            }
        }while(!chosenStone.hasAnyCoins() || chosenStone.isTurnedOff());
        chosenStone.putCoin();

        int[] chosenField = new int[2];
        int fieldIndex = 0;
        if(chosenStone.isFacingUp()){
            do{
                if(fieldIndex%4 == 0){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 1){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 2){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                if(fieldIndex%4 == 3){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                fieldIndex+=1;
            }while(!isFieldClear(chosenField));
        }
        if(chosenStone.isFacingLeft()){
            do{
                if(fieldIndex%4 == 3){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 0){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 1){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                if(fieldIndex%4 == 2){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                fieldIndex+=1;
            }while(!isFieldClear(chosenField));
        }
        if(chosenStone.isFacingDown()){
            do{
                if(fieldIndex%4 == 2){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 3){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 0){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                if(fieldIndex%4 == 1){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                fieldIndex+=1;
            }while(!isFieldClear(chosenField));
        }
        if(chosenStone.isFacingRight()){
            do{
                if(fieldIndex%4 == 1){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 2){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()+1);
                }
                if(fieldIndex%4 == 3){
                    chosenField[0] = (chosenStone.getX()-1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                if(fieldIndex%4 == 0){
                    chosenField[0] = (chosenStone.getX()+1);
                    chosenField[1] = (chosenStone.getY()-1);
                }
                fieldIndex+=1;
            }while(!isFieldClear(chosenField));
        }
        fieldIndex--; //kompensation für schleife, die do-while-schleifen lassen sich wahrscheinlich mit for machen, was diese anweisung überflüssig macht...
                        //es ist zu spät in der nacht, um das zu überarbeiten
        if (fieldIndex%4 == 0){
            chosenStone.move();
            chosenStone.turnLeft();
            chosenStone.turnLeft();
            chosenStone.turnLeft();
            chosenStone.move();
        }
        if (fieldIndex%4 == 1){
            chosenStone.move();
            chosenStone.turnLeft();
            chosenStone.move();
        }
        if (fieldIndex%4 == 2){
            chosenStone.turnLeft();
            chosenStone.move();
            chosenStone.turnLeft();
            chosenStone.move();
        }
        if (fieldIndex%4 == 3){
            chosenStone.turnLeft();
            chosenStone.turnLeft();
            chosenStone.move();
            chosenStone.turnLeft();
            chosenStone.move();
        }
    }
    /**
     * Runs the action of the white team.
     */
    public void doWhiteTeamActions(){
        Robot[] robots = new Robot[]{blackStone0,blackStone1,blackStone2,blackStone3,blackStone4};
        int[][] diagonals = new int[2][NUMBER_OF_COLUMNS]; //0 ist / 1 ist \ diagonale
        int[] chosenField = new int[2];
        int offsetX;
        int offsetY;
        boolean didWhiteMove = false;
        boolean robotLine = false;
        for(int direction = 0;direction<diagonals.length;direction++){
            for(int x =0; x< diagonals[direction].length;x++){
                if(direction==0){
                    diagonals[direction][x] = x-whiteStone.getX()+ whiteStone.getY(); //allgemeine tangentengleichung ha geholfen <3
                }
                else{
                    diagonals[direction][x] = whiteStone.getX()-x + whiteStone.getY();
                }
            }
        }
        for (int[] diagonal : diagonals) {
            for (int x = 0; x < diagonal.length; x++) {
                for (Robot rob : robots) {
                    if (rob.getX() == x && rob.getY() == diagonal[x] && rob.isTurnedOn() && !didWhiteMove) {
                        if (rob.getX() > whiteStone.getX()) {
                            offsetX = 1;
                        } else {
                            offsetX = -1;
                        }
                        if (rob.getY() > whiteStone.getY()) {
                            offsetY = 1;
                        } else {
                            offsetY = -1;
                        }
                        chosenField[0] = x + offsetX;
                        chosenField[1] = diagonal[x] + offsetY;
                        if (rob.getX() < whiteStone.getX()) {
                            for (int b = rob.getX() + 1; b <= whiteStone.getX(); b++) {
                                for (Robot lineCheck : robots) {
                                    if (b == lineCheck.getX() && diagonal[b] == lineCheck.getY() && lineCheck.isTurnedOn()) {
                                        robotLine = true;
                                    }
                                }
                            }
                        }
                        if (rob.getX() > whiteStone.getX()) {
                            for (int b = rob.getX() - 1; b >= whiteStone.getX(); b--) {
                                for (Robot lineCheck : robots) {
                                    if (b == lineCheck.getX() && diagonal[b] == lineCheck.getY() && lineCheck.isTurnedOn()) {
                                        robotLine = true;
                                    }
                                }
                            }
                        } //could be optimised, but is more readable this way
                        if (isFieldClearOfBlackStones(chosenField) && isFieldClear(chosenField) && !robotLine) {
                            whiteStone.setX(chosenField[0]);
                            whiteStone.setY(chosenField[1]);
                            rob.turnOff();
                            didWhiteMove = true;
                        }
                    }
                }
            }
        }
    }
    /**
     * Checks if a team has won the game and, if so, updates the game state to {@link GameState#BLACK_WIN} or {@link GameState#WHITE_WIN}.
     */
    public void updateGameState() {
        if(blackStone0.isTurnedOff() && blackStone1.isTurnedOff() && blackStone2.isTurnedOff() && blackStone3.isTurnedOff() && blackStone4.isTurnedOff()){
            gameState = GameState.WHITE_WIN;
        }
        boolean black0Clear = false; // arrays are a thing
        boolean black1Clear = false; // so are things that are not fopbot
        boolean black2Clear = false; // maybe I should get my priorities in order
        boolean black3Clear = false;
        boolean black4Clear = false;
        boolean atLeast1Alive = false;
        if (blackStone0.isTurnedOff() || !blackStone0.hasAnyCoins()){
            black0Clear = true;
        }
        if (blackStone1.isTurnedOff() || !blackStone1.hasAnyCoins()){
            black1Clear = true;
        }
        if (blackStone2.isTurnedOff() || !blackStone2.hasAnyCoins()){
            black2Clear = true;
        }
        if (blackStone3.isTurnedOff() || !blackStone3.hasAnyCoins()){
            black3Clear = true;
        }
        if (blackStone4.isTurnedOff() || !blackStone4.hasAnyCoins()){
            black4Clear = true;
        }
        if(blackStone0.isTurnedOn()|| blackStone1.isTurnedOn()|| blackStone2.isTurnedOn() || blackStone3.isTurnedOn()|| blackStone4.isTurnedOn()){
            atLeast1Alive =true;
        }
        if(black0Clear && black1Clear && black2Clear && black3Clear && black4Clear && atLeast1Alive){
            gameState = GameState.BLACK_WIN;
        }
    }


    //eigene methoden
    public boolean isFieldClear(int[] chosenField){
        int[] whiteField = new int[2]; //nötig?
        whiteField[0] = whiteStone.getX(); //0 ist X
        whiteField[1] = whiteStone.getY(); //1 ist Y
        return !((chosenField[0] == whiteField[0] && chosenField[1] == whiteField[1]) || chosenField[0] < 0 || chosenField[1] < 0 || chosenField[0] > (NUMBER_OF_COLUMNS-1) || chosenField[1] > (NUMBER_OF_ROWS-1));
    }
    public boolean isFieldClearOfBlackStones(int[] chosenField){
        Robot[] robots = new Robot[]{blackStone0,blackStone1,blackStone2,blackStone3,blackStone4}; //neues wissen macht dinge leichter
        for(Robot robot: robots){
            if(robot.getX() == chosenField[0] && robot.getY() == chosenField[1] && robot.isTurnedOn()){
                return false;
            }

        }
        return true;
    }
    public Direction getRandomDirection(){ //self-explanatory, cant be asked to do switch statements
        int randomDirectionValue = ThreadLocalRandom.current().nextInt(0, 4);
        Direction randomDirection = UP;
        if(randomDirectionValue == 1){
            randomDirection = RIGHT;
        }
        if(randomDirectionValue == 2){
            randomDirection = DOWN;
        }
        if(randomDirectionValue == 3){
            randomDirection = LEFT;
        }
        return randomDirection;
    }
}
