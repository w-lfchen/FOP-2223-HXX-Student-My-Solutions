package h04;

import fopbot.Direction;

/**
 * an interface for setting a state to reference, optimised to work with robots. implements methods that aid in these activities
 */
public interface RobotWithReferenceState {
    /**
     * a method is needed to set a state to reference, of course
     */
    void setCurrentStateAsReferenceState();

    /**
     * since we are optimising for having this interface implemented by robots, having a way to track their position is quite nice.
     * @return the difference in the x value of the position. how this is implemented will depend on the class implementing this interface
     */
    int getDiffX();
    /**
     * since we are optimising for having this interface implemented by robots, having a way to track their position is quite nice.
     * @return the difference in the y value of the position. how this is implemented will depend on the class implementing this interface
     */
    int getDiffY();
    /**
     * since we are optimising for having this interface implemented by robots, having a way to track their rotation is quite nice.
     * @return the difference in the rotation of the robot. how this is implemented will depend on the class implementing this interface, however it should be noted that it will be returned as a Direction value.
     */
    Direction getDiffDirection();
    /**
     * since we are optimising for having this interface implemented by robots, having a way to track their number of coins is quite nice.
     * @return the difference in the amount of coins the robot has compared to the reference state. how this is implemented will depend on the class implementing this interface
     */
    int getDiffNumberOfCoins();

}
