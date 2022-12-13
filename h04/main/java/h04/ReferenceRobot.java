package h04;

import fopbot.Direction;

/**
 * Represents a robot that saves the reference x- and y-coordinate, the reference direction
 * and the reference number of coins.
 */
public class ReferenceRobot {

    /**
     * The x-coordinate the reference robot has
     */
    private int refX;
    /**
     * The y-coordinate the reference robot has
     */
    private int refY;
    /**
     * The direction the reference robot has
     */
    private Direction refDirection;
    /**
     * The amount of coins the reference robot has
     */
    private int refNumberOfCoins;

    /**
     * Initializes a new reference robot.
     *
     * @param x the value of the x-coordinate of the robot
     * @param y the value of the y-coordinate of the robot
     * @param direction the value of the direction of the robot
     * @param numberOfCoins the amount of coins of the robot
     */
    public ReferenceRobot(int x, int y, Direction direction, int numberOfCoins) {
        refX = x;
        refY = y;
        refDirection = direction;
        refNumberOfCoins = numberOfCoins;
    }

    /**
     * This method returns the value of the x-coordinate reference.
     *
     * @return the x-coordinate reference value
     */
    public int getRefX() {
        return refX;
    }

    /**
     * This method sets the value of the x-coordinate reference.
     *
     * @param refX the value the x-coordinate should be set to
     */
    public void setRefX(int refX) {
        this.refX = refX;
    }

    /**
     * This method returns the value of the y-coordinate reference.
     *
     * @return the y-coordinate reference value
     */
    public int getRefY() {
        return refY;
    }

    /**
     * This method sets the value of the y-coordinate reference.
     *
     * @param refY the value the y-coordinate should be set to
     */
    public void setRefY(int refY) {
        this.refY = refY;
    }

    /**
     * This method returns the value of the direction reference.
     *
     * @return the direction reference value
     */
    public Direction getRefDirection() {
        return refDirection;
    }

    /**
     * This method sets the value of the direction reference.
     *
     * @param refDirection the value the direction should be set to
     */
    public void setRefDirection(Direction refDirection) {
        this.refDirection = refDirection;
    }

    /**
     * This method returns the value of the numberOfCoins reference.
     *
     * @return the numberOfCoins reference value
     */
    public int getRefNumberOfCoins() {
        return refNumberOfCoins;
    }

    /**
     * This method sets the value of the numberOfCoins reference.
     *
     * @param refNumberOfCoins the value the numberOfCoins should be set to
     */
    public void setRefNumberOfCoins(int refNumberOfCoins) {
        this.refNumberOfCoins = refNumberOfCoins;
    }
}
