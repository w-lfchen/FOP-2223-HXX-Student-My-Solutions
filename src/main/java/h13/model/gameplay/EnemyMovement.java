package h13.model.gameplay;

import h13.model.gameplay.sprites.Enemy;
import h13.shared.Utils;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import static h13.controller.GameConstants.*;
import static h13.model.gameplay.Direction.*;

/**
 * The {@link EnemyMovement} class is responsible for moving the {@linkplain h13.model.gameplay.sprites.Enemy enemies} in a grid.
 */
public class EnemyMovement implements Updatable {

    // --Variables-- //

    /**
     * The current movement direction
     */
    private Direction direction;

    /**
     * The current movement speed
     */
    private double velocity = INITIAL_ENEMY_MOVEMENT_VELOCITY;

    /**
     * The Next y-coordinate to reach
     */
    private double yTarget = 0;

    /**
     * The current {@link GameState}
     */
    private final GameState gameState;

    // --Constructors-- //

    /**
     * Creates a new EnemyMovement.
     *
     * @param gameState The enemy controller.
     */
    public EnemyMovement(final GameState gameState) {
        this.gameState = gameState;
        nextRound();
    }

    // --Getters and Setters-- //

    /**
     * Gets the current {@link #velocity}.
     *
     * @return The current {@link #velocity}.
     * @see #velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Sets the current {@link #velocity} to the given value.
     *
     * @param velocity The new {@link #velocity}.
     * @see #velocity
     */
    public void setVelocity(final double velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the current {@link #direction}.
     *
     * @return The current {@link #direction}.
     * @see #direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the current {@link #direction} to the given value.
     *
     * @param direction The new {@link #direction}.
     * @see #direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Checks whether the bottom was reached.
     *
     * @return {@code true} if the bottom was reached, {@code false} otherwise.
     */
    public boolean bottomWasReached() {
        return getEnemyBounds().getMaxY() >= ORIGINAL_GAME_BOUNDS.getMaxY();
    }

    /**
     * Gets the enemy controller.
     *
     * @return The enemy controller.
     */
    public GameState getGameState() {
        return gameState;
    }

    // --Utility Methods-- //

    /**
     * Creates a BoundingBox around all alive enemies.
     *
     * @return The BoundingBox.
     */
    public Bounds getEnemyBounds() {
        // catch edge case of there being no alive enemies
        if (getGameState().getAliveEnemies().isEmpty()) return new BoundingBox(0, 0, 0, 0);
        // initialise the points that are needed to describe the bounds with their respective opposite values so that they will always be adjusted
        // it can be assumed that all enemies are within the game bounds
        double minX = ORIGINAL_GAME_BOUNDS.getMaxX();
        double maxX = ORIGINAL_GAME_BOUNDS.getMinX();
        double minY = ORIGINAL_GAME_BOUNDS.getMaxY();
        double maxY = ORIGINAL_GAME_BOUNDS.getMinY();
        for (Enemy enemy : gameState.getAliveEnemies()) {
            Bounds bounds = enemy.getBounds();
            // if the value of the enemy is bigger, increase the resulting bound's value
            minX = Math.min(bounds.getMinX(), minX);
            maxX = Math.max(bounds.getMaxX(), maxX);
            minY = Math.min(bounds.getMinY(), minY);
            maxY = Math.max(bounds.getMaxY(), maxY);
        }
        // construct a BoundingBox out of the calculated values
        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }

    /**
     * Checks whether the target Position of the current movement iteration is reached.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     * @return {@code true} if the target Position of the current movement iteration is reached, {@code false} otherwise.
     */
    public boolean targetReached(final Bounds enemyBounds) {
        // if the current movement is downwards, check for yTarget
        if (direction.isVertical()) return enemyBounds.getMinY() >= yTarget;
        // else return whether the respective edge is touched, make sure to touch it by clamping first
        Bounds clampedBounds = Utils.clamp(enemyBounds);
        if (direction == LEFT) return clampedBounds.getMinX() == ORIGINAL_GAME_BOUNDS.getMinX();
        else return clampedBounds.getMaxX() == ORIGINAL_GAME_BOUNDS.getMaxX();
    }

    // --Movement-- //

    @Override
    public void update(final double elapsedTime) {
        // do nothing if the game is over
        if (bottomWasReached()) return;
        // get the bounds
        Bounds enemyBounds = getEnemyBounds();
        Bounds nextBounds = Utils.clamp(Utils.getNextPosition(enemyBounds, velocity, direction, elapsedTime));
        // if the next movement step of the current iteration reaches the destination, initialise the next iteration
        if (targetReached(nextBounds)) nextMovement(nextBounds);
        // update positions
        updatePositions(nextBounds.getMinX() - enemyBounds.getMinX(), nextBounds.getMinY() - enemyBounds.getMinY());
    }

    /**
     * Updates the positions of all alive enemies.
     *
     * @param deltaX The deltaX.
     * @param deltaY The deltaY.
     */
    public void updatePositions(final double deltaX, final double deltaY) {
        // iterate over all alive enemies and set their postions
        for (Enemy enemy : gameState.getAliveEnemies()) {
            enemy.setX(enemy.getX() + deltaX);
            enemy.setY(enemy.getY() + deltaY);
        }
    }

    /**
     * Starts the next movement iteration.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     */
    public void nextMovement(final Bounds enemyBounds) {
        // always increase speed
        velocity += ENEMY_MOVEMENT_SPEED_INCREASE;
        // set next direction
        if (direction.isHorizontal())
            setDirection(DOWN);
        else if (direction.isVertical()) {
            double minX = enemyBounds.getMinX();
            double maxX = enemyBounds.getMaxX();
            // should the bounds be outside the original game bounds on either side, move in the other direction regardless of consequences
            if (minX <= ORIGINAL_GAME_BOUNDS.getMinX()) direction = RIGHT;
            else if (maxX >= ORIGINAL_GAME_BOUNDS.getMaxX()) direction = LEFT;
                // if the enemyBounds is not out of bounds, decide which direction to go next based on the distance to the respective edge
                // move away from the edge that is closer to the enemyBounds
            else
                direction = minX - ORIGINAL_GAME_BOUNDS.getMinX() < ORIGINAL_GAME_BOUNDS.getMaxX() - maxX ? RIGHT : LEFT;
        }
        // adjust yTarget if necessary
        if (direction.isVertical()) yTarget += VERTICAL_ENEMY_MOVE_DISTANCE;
        // update enemy directions
        for (Enemy enemy : gameState.getAliveEnemies()) enemy.setDirection(direction);
    }

    /**
     * Prepares the next round of enemies.
     * Uses {@link h13.controller.GameConstants#INITIAL_ENEMY_MOVEMENT_DIRECTION} and {@link h13.controller.GameConstants#INITIAL_ENEMY_MOVEMENT_VELOCITY} to set the initial values.
     */
    public void nextRound() {
        direction = INITIAL_ENEMY_MOVEMENT_DIRECTION;
        yTarget = HUD_HEIGHT;
    }
}
/* voluntary question:
the gameController handles the removal of dead enemies and sprites.
 */
