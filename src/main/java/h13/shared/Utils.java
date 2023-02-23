package h13.shared;

import h13.model.gameplay.Direction;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import static h13.controller.GameConstants.ORIGINAL_GAME_BOUNDS;

/**
 * A {@link Utils} class containing utility methods.
 */
public class Utils {

    /**
     * Returns the closest position for the given {@link Bounds} that is within the game bounds.
     *
     * @param bounds The bounds to be clamped.
     * @return the clamped coordinate.
     * @see <a href="https://en.wikipedia.org/wiki/Clamping_(graphics)">Clamping_(graphics)</a>
     * @see h13.controller.GameConstants
     */
    public static Bounds clamp(final Bounds bounds) {
        // the x and y values of the clamped Bounds
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        // if the passed bound's values are outside the original game bounds, adjust the x and y values accordingly
        if (bounds.getMinX() < ORIGINAL_GAME_BOUNDS.getMinX()) {
            x = ORIGINAL_GAME_BOUNDS.getMinX();
        } else if (bounds.getMaxX() > ORIGINAL_GAME_BOUNDS.getMaxX()) {
            // since x is the minimum value, it is required to adjust for the bound's width
            x = ORIGINAL_GAME_BOUNDS.getMaxX() - bounds.getWidth();
        }
        if (bounds.getMinY() < ORIGINAL_GAME_BOUNDS.getMinY()) {
            y = ORIGINAL_GAME_BOUNDS.getMinY();
        } else if (bounds.getMaxY() > ORIGINAL_GAME_BOUNDS.getMaxY()) {
            // since y is the minimum value, it is required to adjust for the bound's height
            y = ORIGINAL_GAME_BOUNDS.getMaxY() - bounds.getHeight();
        }
        // the dimensions of the bounds remain unaltered
        return new BoundingBox(x, y, bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Returns the Moved Bounding Box for the given {@link Bounds}, {@link Direction}, velocity and time.
     *
     * @param bounds      The bounds to be moved.
     * @param velocity    The velocity of the movement.
     * @param direction   The direction of the movement.
     * @param elapsedTime The time elapsed since the last movement.
     * @return the moved bounds
     */
    public static Bounds getNextPosition(final Bounds bounds, final double velocity, final Direction direction, final double elapsedTime) {
        // p' = p + (dir * v * ∆t)
        // since dir is a two-dimensional vector, it is required to split it into it's x and y components, and then multiply those with the values
        double nextX = bounds.getMinX() + (direction.getX() * velocity * elapsedTime);
        double nextY = bounds.getMinY() + (direction.getY() * velocity * elapsedTime);
        return new BoundingBox(nextX, nextY, bounds.getWidth(), bounds.getHeight());
    }
}
