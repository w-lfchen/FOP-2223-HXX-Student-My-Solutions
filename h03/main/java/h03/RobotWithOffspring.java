package h03;

import fopbot.Direction;
import fopbot.Robot;

/**
 * the class that extends Robot to add reproductive capabilities
 * @author Wölfchen
 */
public class RobotWithOffspring extends Robot {
    private final int numberOfColumnsOfWorld;
    private final int numberOfRowsOfWorld;
    /**
     * offspring attribute, basically a child robot that we learn inheritance with
     */
    protected Robot offspring;

    /**
     * constructor for the RobotWithOffspring class that sets the attributes that correspond to the parameters. The robot gets placed in the middle of the world if possible, if not the index is rounded down.
     * @param numberOfColumnsOfWorld expects the number of columns of the world the robot is put in
     * @param numberOfRowsOfWorld expects the number of rows of the world the robot is put in
     * @param direction the direction of the newly created robot
     * @param numberOfCoins number of coins to give the robot
     */
    public RobotWithOffspring(int numberOfColumnsOfWorld, int numberOfRowsOfWorld, Direction direction,int numberOfCoins){
        super(numberOfColumnsOfWorld/2,numberOfRowsOfWorld/2,direction, numberOfCoins);
        this.numberOfColumnsOfWorld = numberOfColumnsOfWorld;
        this.numberOfRowsOfWorld = numberOfRowsOfWorld;
    }

    /**
     * creates offspring, probably through advanced cell division, offspring is of class Robot. Offspring is spawned at the same coordinates the robot creating it is on
     * @param direction the direction of the offspring
     * @param numberOfCoins the number of coins to give the offspring
     */
    public void initOffspring(Direction direction, int numberOfCoins){
        offspring = new Robot(this.getX(),this.getY(),direction,numberOfCoins);
    }

    /**
     * returns X value of offspring
     * @return X value of offspring
     */
    public int getXOfOffspring(){
        return offspring.getX();
    }

    /**
     * returns Y value of offspring
     * @return Y value of offspring
     */
    public int getYOfOffspring(){
        return offspring.getY();
    }

    /**
     * returns direction of offspring
     * @return direction of offspring
     */
    public Direction getDirectionOfOffspring(){
        return offspring.getDirection();
    }

    /**
     * returns number of coins of offspring
     * @return number of coins of offspring
     */
    public int getNumberOfCoinsOfOffspring(){
        return offspring.getNumberOfCoins();
    }

    /**
     * checks whether offspring is initialised
     * @return true if offspring is initialised, false if not
     */
    public boolean offspringIsInitialized(){
        if(offspring == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * moves the offspring a given amount of fields on the X axis, if the number is too high or too low, it will be put next to the border
     * @param diff amount to add, negative amounts subtract
     */
    public void addToXOfOffspring(int diff){
        if(offspringIsInitialized()){
            int target = getXOfOffspring()+diff;
            if(target >= 0 && target < numberOfColumnsOfWorld){
                offspring.setX(target);
            }
            if(target < 0){
                offspring.setX(0);
            }
            if(target >= numberOfColumnsOfWorld){
                offspring.setX(numberOfColumnsOfWorld-1);
            }
        }
    }
    /**
     * moves the offspring a given amount of fields on the Y axis, if the number is too high or too low, it will be put next to the border
     * @param diff amount to add, negative amounts subtract
     */
    public void addToYOfOffspring(int diff){
        if(offspringIsInitialized()){
            int target = getYOfOffspring()+diff;
            if(target >= 0 && target < numberOfRowsOfWorld){
                offspring.setY(target);
            }
            if(target < 0){
                offspring.setY(0);
            }
            if(target >= numberOfRowsOfWorld){
                offspring.setY(numberOfRowsOfWorld-1);
            }
        }
    }

    /**
     * spins the robot by a desired amount. it turns clockwise for positive values, negative ones will turn it counterclockwise.
     * @param diff number of clockwise 90° turns for the robot to perform
     */
    public void addToDirectionOfOffspring(int diff){
        if(offspringIsInitialized()){
            if(diff >= 0){
                switch(diff%4){
                    case 0: break;
                    case 1: offspring.turnLeft();
                            offspring.turnLeft();
                            offspring.turnLeft();
                            break;
                    case 2: offspring.turnLeft();
                            offspring.turnLeft();
                            break;
                    case 3: offspring.turnLeft();
                            break;
                }
            }
            else{
                switch(diff%4){
                    case 0: break;
                    case -3: offspring.turnLeft();
                        offspring.turnLeft();
                        offspring.turnLeft();
                        break;
                    case -2: offspring.turnLeft();
                        offspring.turnLeft();
                        break;
                    case -1: offspring.turnLeft();
                        break;
                }
            }
        }
    }

    /**
     * Adds/subtracts coins from the offspring. Will not allow the count to go below 0.
     * @param diff the number of coins to be added, negative values will subtract coins
     */
    public void addToNumberOfCoinsOfOffspring(int diff){
        if(offspringIsInitialized()){
            int target = getNumberOfCoinsOfOffspring()+diff;
            if(target > 0){
                offspring.setNumberOfCoins(target);
            }
            else{
                offspring.setNumberOfCoins(0);
            }
        }
    }
}
