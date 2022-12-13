package h00;

import fopbot.Robot;
import fopbot.World;

import static fopbot.Direction.*;
import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Code für Übungsblatt 00.
 *
 * @author Ruben Deisenroth
 */
public class Main {

    /**
     * Starts the Program.
     *
     * @param args the Launch arguments
     */
    public static void main(String... args) {
        // Welt mit Größe 5x5 erstellen
        final int worldSize = 5;
        World.setSize(worldSize, worldSize);

        // Die Schrittdauer auf 200ms setzen
        World.setDelay(200);

        // Die Welt anzeigen
        World.setVisible(true);

        // Den Code der Hausübung ausführen
        doExercise();
    }

    /**
     * Bewegt den Roboter entsprechend den Anforderungen von Übungsblatt 00.
     */
    public static void doExercise() {
        Robot robby = new Robot(4, 0, DOWN, 12);
        robby.turnLeft();
        robby.turnLeft();
        for(int i = 0; i<4; i++) {
            robby.move();
            robby.putCoin();
        }
        robby.turnLeft();
        for (int a = 0 ;a<4;a++){
            robby.move();
            robby.putCoin();
            robby.turnLeft();
            robby.move();
            robby.putCoin();
            for(int b = 0;b<3;b++){
                robby.turnLeft();
            }
        }
    }
}
