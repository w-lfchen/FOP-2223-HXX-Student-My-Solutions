package h03;

import fopbot.Direction;

/**
 * the robots are up to something
 * @author Wölfchen
 */
public class TwinRobots {
    RobotWithOffspring[] robots;

    /**
     * constructor for TwinRobots, also creates the array robots with length 2 and inserts a RobotWithOffspring on index 0 and a RobotWithOffspring2 on index 1
     * those both then execute initOffspring, with the parameters Left and 0
     * @param numberOfColumnsOfWorld expects the number of columns in the world
     * @param numberOfRowsOfWorld expects the number of rows in the world
     */
    public TwinRobots(int numberOfColumnsOfWorld, int numberOfRowsOfWorld){
        robots = new RobotWithOffspring[2];
        robots[0] = new RobotWithOffspring(numberOfColumnsOfWorld,numberOfRowsOfWorld, Direction.RIGHT,0);
        robots[1] = new RobotWithOffspring2(numberOfColumnsOfWorld,numberOfRowsOfWorld, Direction.UP,0);
        robots[0].initOffspring(Direction.LEFT,0);
        robots[1].initOffspring(Direction.LEFT,0);
    }

    /**
     * returns the RobotWithOffspring at the given index
     * @param index the index of the array robots to be returned
     * @return the RobotWithOffspring at the given index
     */
    public RobotWithOffspring getRobotByIndex(int index){
        return robots[index];
    }

    /**
     * adds to the direction of both offsprings in the same manner the addToDirectionOfOffspring methods do, by invoking them
     * @param diff number of clockwise 90° degree turns for both offsprings to perform, negative values will result in counterclockwise spinning
     */
    public void addToDirectionOfBothOffsprings(int diff){
        for(RobotWithOffspring rob :robots){
            rob.addToDirectionOfOffspring(diff);
        }
    }
}
