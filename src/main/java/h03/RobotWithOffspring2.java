package h03;

import fopbot.Direction;
import fopbot.Robot;

/**
 * roboter 2, jetzt mit Akku
 * @author Wölfchen
 */
public class RobotWithOffspring2 extends RobotWithOffspring{
    private int directionAccu;

    /**
     * RobotWithOffspring2 constructor, passes all values to the RobotWithOffspring constructor
     * @param numberOfColumnsOfWorld expects the number of columns in the world
     * @param numberOfRowsOfWorld expects the number of rows in the world
     * @param direction direction of newly created robot
     * @param numberOfCoins number of coins of the newly created robot
     */
    public RobotWithOffspring2(int numberOfColumnsOfWorld, int numberOfRowsOfWorld, Direction direction, int numberOfCoins){
        super(numberOfColumnsOfWorld,numberOfRowsOfWorld,direction,numberOfCoins);
    }

    /**
     * almost the same as the initOffspring of RobotWithOffspring, with the added feature of setting the directionAccu variable according to the direction of the offspring
     * @param direction the direction of the offspring
     * @param numberOfCoins the number of coins to give the offspring
     */
    public void initOffspring(Direction direction, int numberOfCoins){
        super.initOffspring(direction,numberOfCoins);
        switch (direction){
            case UP -> directionAccu = 0;
            case RIGHT-> directionAccu = 1;
            case DOWN-> directionAccu = 2;
            case LEFT-> directionAccu = 3;
        }
    }
    private Direction getDirectionFromAccu(){
        if(directionAccu >= 0){
            switch(directionAccu%4){
                case 0 : return Direction.UP;
                case 1 : return Direction.RIGHT;
                case 2 : return Direction.DOWN;
                case 3 : return Direction.LEFT;
            }
        }
        else{
            switch(directionAccu%4){
                case 0 : return Direction.UP;
                case -3: return Direction.RIGHT;
                case -2: return Direction.DOWN;
                case -1: return Direction.LEFT;
            }
        }
        return Direction.UP;
    }

    /**
     * returns the direction of the offspring based on the directionAccu value
     * @return the direction of the offspring, according to the directionAccu value
     */
    public Direction getDirectionOfOffspring(){
        return getDirectionFromAccu();
    }

    /**
     * adds to the direction in a similar manner to the method it is overriding, just with added directionAccu functionality this time
     * @param diff number of clockwise 90° turns for the robot to perform, negative values will result in counterclockwise turns
     */
    public void addToDirectionOfOffspring(int diff){
        if (offspringIsInitialized()){
            directionAccu += diff;
            Direction target = getDirectionFromAccu();
            while(offspring.getDirection() != target){
                offspring.turnLeft();
            }
        }
    }
}
