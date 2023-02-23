package h04;

import fopbot.Direction;

/**
 * this class is a lot like RobotWithCoinTypesAndRefStateOne. It differs in the way it stores the reference state, that being a class made specifically for that purpose, ReferenceRobot.
 * in order to achieve this, it creates its own refRobot object in the constructor.
 */
public class RobotWithCoinTypesAndRefStateTwo extends RobotWithCoinTypes implements RobotWithReferenceState{

    private ReferenceRobot refRobot;

    /**
     * calls the RobotWithCoinTypes with all parameters. also creates a new ReferenceRobot object and assigns it to refRobot with the given values. numberOfCoins is a sum of all three types.
     * @param x the x value the robot should be placed on
     * @param y the y value the robot should be placed on
     * @param direction direction the robot should face upon being created
     * @param numberOfSilverCoins amount of silver coins to give the robot
     * @param numberOfBrassCoins amount of brass coins to give the robot
     * @param numberOfCopperCoins amount of copper coins to give the robot
     */
    public RobotWithCoinTypesAndRefStateTwo(int x, int y, Direction direction, int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins) {
        super(x, y, direction, numberOfSilverCoins, numberOfBrassCoins, numberOfCopperCoins);
        refRobot = new ReferenceRobot(x, y, direction, numberOfSilverCoins + numberOfBrassCoins + numberOfCopperCoins);
    }

    /**
     * update the reference state by updating the refRobot's values via its set-methods
     */
    @Override
    public void setCurrentStateAsReferenceState() {
        refRobot.setRefX(super.getX());
        refRobot.setRefY(super.getY());
        refRobot.setRefDirection(super.getDirection());
        refRobot.setRefNumberOfCoins(super.getNumberOfCoins());
    }
    /**
     * returns the difference in x coordinate between current state and reference state. might be negative.
     * @return the difference in x coordinate between current state and reference state. might be negative.
     */
    @Override
    public int getDiffX() {
        return getX()-refRobot.getRefX();
    }
    /**
     * returns the difference in y coordinate between current state and reference state. might be negative.
     * @return the difference in y coordinate between current state and reference state. might be negative.
     */
    @Override
    public int getDiffY() {
        return getY()-refRobot.getRefY();
    }
    /**
     * returns the difference in direction between current state and reference state. is calculated as an integer and the transformed in the returned Direction value, with UP being no difference, DOWN translating
     * to opposite direction and LEFT and RIGHT being rotated 90° counterclockwise or clockwise, respectively. this is done via a switch statement and modulo, with the default state being UP, this should never matter though.
     * @return the difference in direction between current state and reference state as a Direction value.
     */
    @Override
    public Direction getDiffDirection() {
        int diff = (getDirection().ordinal()-refRobot.getRefDirection().ordinal())%4;
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
        return getNumberOfCoins()-refRobot.getRefNumberOfCoins();
    }
}
