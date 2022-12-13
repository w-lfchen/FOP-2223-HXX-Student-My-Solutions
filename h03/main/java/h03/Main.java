package h03;


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
        sandboxTests();
    }

    private static void sandboxTests() {
        World.setSize(10, 10);
        World.setDelay(500);
        World.setVisible(true);
        TwinRobots h = new TwinRobots(World.getHeight(), World.getWidth());
        Robot gfh = h.getRobotByIndex(1);
    }
}
