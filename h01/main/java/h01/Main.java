package h01;

/**
 * A class containing the entry point of this program.
 */
public class Main {

    /**
     * Executes this program.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Checkers checkers = new Checkers();
        checkers.initGame();
        checkers.runGame();
    }
}
