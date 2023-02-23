package h02;

import fopbot.Robot;
import fopbot.World;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static fopbot.Direction.RIGHT;

/**
 * Main entry point in executing the program.
 */
public class Main {
    // Delay between each action in FopBot-World (world), for example:
    // Waits 1000ms between each .move() call
    public static final int DELAY = 10;

    // Generates random int between 4 (inclusive) and 10 (exclusive)
    public static int getRandomWorldSize() {
        return 4 + ThreadLocalRandom.current().nextInt(6);
    }

    // Name of file for patterns
    public static final String FILENAME = "ExamplePattern.txt";

    public static void main(String[] args) {
        // Get number of columns from method
        int numberOfColumns = getRandomWorldSize();

        // Get number of rows from method
        int numberOfRows = getRandomWorldSize();

        // Initialize World with specified number of columns and rows
        World.setSize(numberOfColumns, numberOfRows);

        // Set the internal delay of the world
        World.setDelay(DELAY);

        // Set the world visible
        World.setVisible(true);

        // Print out size of the world to the command line
        System.out.println("Size of world: " + numberOfColumns + "x" + numberOfRows);

        // Initialize new Main-object to call methods
        Main main = new Main();

        // Initialize a pattern provider for the .txt-file in resources
        PatternProvider patternProvider;
        try {
            patternProvider = new PatternProvider(FILENAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get the pattern from the .txt file
        boolean[][] testPattern = patternProvider.getPattern();

        // Call initializeRobotsPattern
        Robot[] allRobots = main.initializeRobotsPattern(testPattern, numberOfColumns, numberOfRows);

        main.letRobotsMarch(allRobots);
    }

    /**
     * Counts the number of robots in a pattern, given a specified world size.
     *
     * @param pattern           The pattern for the robots.
     * @param numberOfColumns   Number of columns in the world.
     * @param numberOfRows      Number of rows in the world.
     * @return                  Number of robots in the world.
     */
    public int countRobotsInPattern(boolean[][] pattern, int numberOfColumns, int numberOfRows) {
        int numberOfRobots = 0;
        for(int x = 0; x < pattern.length ; x++){
            for(int y = 0; y < pattern[x].length ; y++){
                if (pattern[x][y] && x < numberOfColumns && y < numberOfRows){
                    numberOfRobots++;
                }
            }
        }
        return numberOfRobots;
    }

    /**
     * Initialize allRobots array for given pattern and world size.
     *
     * @param pattern           The pattern for the robots.
     * @param numberOfColumns   Number of columns in world.
     * @param numberOfRows      Number of rows in world.
     * @return                  Correctly initialized allRobots array.
     */
    public Robot[] initializeRobotsPattern(boolean[][] pattern, int numberOfColumns, int numberOfRows) {
        Robot[] allRobots = new Robot[countRobotsInPattern(pattern, numberOfColumns, numberOfRows)];
        int index = 0;
        for(int x = 0; x < pattern.length ; x++){
            for(int y = 0; y < pattern[x].length ; y++){
                if (pattern[x][y] && x < numberOfColumns && y < numberOfRows){
                    allRobots[index] = new Robot(x,y,RIGHT, numberOfColumns-x);
                    index++;
                }
            }
        }
        return allRobots;
    }

    /**
     * Returns how many of the components of the given robot-array are null.
     *
     * @param allRobots   The Robot-array.
     * @return            True, if array contains robot.
     */
    public int numberOfNullRobots(Robot[] allRobots) {
        int numberOfNullBots = 0;
        for (Robot allRobot : allRobots) {
            if (allRobot == null) {
                numberOfNullBots++;
            }
        }
        return numberOfNullBots;
    }

    /**
     * Creates an array containing three (pseudo-) random int values from 0 (inclusive) to given parameter (exclusive).
     *
     * @param bound   The upper bound for the int values.
     * @return        The array.
     */
    public int[] generateThreeDistinctRandomIndices(int bound) {
        int[] intArray = new int[3];
        intArray[0] = ThreadLocalRandom.current().nextInt(bound);
        do{
            intArray[1] = ThreadLocalRandom.current().nextInt(bound);
        }while (intArray[1] == intArray[0]);
        do{
            intArray[2] = ThreadLocalRandom.current().nextInt(bound);
        }while(intArray[2] == intArray[0] || intArray[2] == intArray[1]);
        return intArray;
    }

    /**
     * Sorts the given 3 valued array from lowest to highest.
     *
     * @param array   The array to be sorted.
     */
    public void sortArray(int[] array) {
        int tmp;
        if (array[0]> array[1]){
            tmp = array [1];
            array[1] = array[0];
            array[0] = tmp;
        }
        if (array[1]> array[2]){
            tmp = array [2];
            array[2] = array[1];
            array[1] = tmp;
        }
        if (array[0]> array[1]){
            tmp = array [1];
            array[1] = array[0];
            array[0] = tmp;
        }
    }

    /**
     * Swaps three robots in given robot array.
     * Robot at index i will later be at index j.
     * Robot at index j will later be at index k.
     * Robot at index k will later be at index i.
     *
     * @param indices       Array containing indices i, j and k.
     * @param allRobots     Array containing the robots.
     */
    public void swapRobots(int[] indices, Robot[] allRobots) {
        Robot tmp;
        tmp = allRobots[indices[2]];
        allRobots[indices[2]] = allRobots[indices[1]];
        allRobots[indices[1]] = allRobots[indices[0]];
        allRobots[indices[0]] = tmp;
    }

    /**
     * Reduces the given robot array by the set amount and only keeps non-null components.
     *
     * @param robots    The array to be reduced.
     * @param reduceBy  The number of indices that are reduced.
     * @return          The reduced array.
     */
    public Robot[] reduceRobotArray(Robot[] robots, int reduceBy) {
        Robot[] reducedArray = new Robot[robots.length-reduceBy];
        int reducedIndex = 0;
        for (Robot robot : robots) {
            if (robot != null) {
                reducedArray[reducedIndex] = robot;
                reducedIndex++;
            }
        }
        return reducedArray;
    }

    /**
     * Lets all robots in the given array walk to the right while also putting down coins.
     * If robots leave the world they are set to null.
     * After the steps are made, if more than three robots exist, three of them change their index.
     * If 3 or more components of the array are null, the array is reduced by the amount of null components.
     *
     * @param allRobots   Array containing all the robots.
     */
    public void letRobotsMarch(Robot[] allRobots) {
        while(allRobots.length-numberOfNullRobots(allRobots) >=1){
            for(int i = 0;i< allRobots.length;i++){
                if (allRobots[i] != null){
                    if(allRobots[i].hasAnyCoins()){
                        allRobots[i].putCoin();
                    }
                    if (allRobots[i].isFrontClear()) {
                        allRobots[i].move();
                    } else {
                        allRobots[i] = null;
                    }
                }
            }
            if(allRobots.length >= 3){
                int[] indices = generateThreeDistinctRandomIndices(allRobots.length);
                sortArray(indices);
                swapRobots(indices, allRobots);
            }
            if(numberOfNullRobots(allRobots) >= 3){
                allRobots = reduceRobotArray(allRobots, numberOfNullRobots(allRobots));
            }
        }
    }
}
