package h04;
import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // Create a world of size 5x5
        final int worldSize = 5;
        World.setSize(worldSize, worldSize);

        // Sets delay to 200ms
        World.setDelay(200);

        // Show the world
        World.setVisible(true);

        // Create a Robot
        Robot robot = new Robot(1, 1, Direction.UP, 1);

    }
}
