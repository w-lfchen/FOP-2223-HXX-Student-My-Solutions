package h04;

import fopbot.Direction;
import fopbot.Robot;

/**
 * this robot extends the RobotWithCoinTypes and adds reference state functionality by implementing the RobotWithReferenceState interface.
 */
public class RobotWithCoinTypesAndRefStateOne extends RobotWithCoinTypes implements RobotWithReferenceState{

    private int refX;
    private int refY;
    private Direction refDirection;
    private int refNumberOfCoins;

    /**
     * constructor. calls the RobotWithCoinTypes constructor with all parameters and saves the parameters as the first reference state. the numberOfCoins value is a sum of all three types.
     * @param x the x value the robot should be placed on
     * @param y the y value the robot should be placed on
     * @param direction direction the robot should face upon being created
     * @param numberOfSilverCoins amount of silver coins to give the robot
     * @param numberOfBrassCoins amount of brass coins to give the robot
     * @param numberOfCopperCoins amount of copper coins to give the robot
     */
    public RobotWithCoinTypesAndRefStateOne(int x, int y, Direction direction, int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins){
        super(x, y, direction, numberOfSilverCoins, numberOfBrassCoins, numberOfCopperCoins);
        this.refX = x;
        this.refY = y;
        this.refDirection = direction;
        this.refNumberOfCoins = numberOfSilverCoins  + numberOfBrassCoins + numberOfCopperCoins;
    }

    /**
     * updates the reference state variables to the current state. implemented as part of the RobotWithReferenceState interface.
     */
    public void setCurrentStateAsReferenceState(){
        refX = super.getX();
        refY = super.getY();
        refDirection = super.getDirection();
        refNumberOfCoins = super.getNumberOfCoins();
    }

    /**
     * returns the difference in x coordinate between current state and reference state. might be negative.
     * @return the difference in x coordinate between current state and reference state. might be negative.
     */
    @Override
    public int getDiffX() {
        return this.getX()-refX;
    }

    /**
     * returns the difference in y coordinate between current state and reference state. might be negative.
     * @return the difference in y coordinate between current state and reference state. might be negative.
     */
    @Override
    public int getDiffY() {
        return this.getY()-refY;
    }

    /**
     * returns the difference in direction between current state and reference state. is calculated as an integer and the transformed in the returned Direction value, with UP being no difference, DOWN translating
     * to opposite direction and LEFT and RIGHT being rotated 90° counterclockwise or clockwise, respectively. this is done via a switch statement and modulo, with the default state being UP, this should never matter though.
     * @return the difference in direction between current state and reference state as a Direction value.
     */
    @Override
    public Direction getDiffDirection() {
        int diff = (this.getDirection().ordinal()-refDirection.ordinal())%4;
        return switch (diff){
            case -3 -> Direction.RIGHT;
            case -2 -> Direction.DOWN;
            case -1 -> Direction.LEFT;
            case  0 -> Direction.UP;
            case  1 -> Direction.RIGHT;
            case  2 -> Direction.DOWN;
            case  3 -> Direction.LEFT;
            default -> Direction.UP;
        };
    }

    /**
     * returns the difference in amount of coins between current state and reference state. might be negative.
     * @return the difference in amount of coins between current state and reference state. might be negative.
     */
    @Override
    public int getDiffNumberOfCoins() {
        return this.getNumberOfCoins()-refNumberOfCoins;
    }
}
