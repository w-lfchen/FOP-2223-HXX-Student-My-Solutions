package h03;

import fopbot.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class H3_2_UnitTest {
    /*
     * Beispiel für einen JUnit-Test
     *
     * assertEquals überprüft, ob der erste aktuale Parameterwert gleich dem zweiten
     * aktualen Parameterwert ist. Ist dies nicht der Fall, schlägt der JUnit-Test
     * fehl.
     */

    /**
     * some tests
     */
    @Test
    void testRobotWithOffspringTwins() {
        World.setSize(10, 10);
        World.setDelay(0);

        TwinRobots twins = new TwinRobots(10, 10);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
        twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(3);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(-8);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(5);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(2147483647);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(-2147483647);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(-2147483648);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(-2147483648);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(2147483647);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(-2147483647);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(2);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(0);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(1);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());

        twins.addToDirectionOfBothOffsprings(-2);

        assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
            twins.getRobotByIndex(1).getDirectionOfOffspring());
    }
}
