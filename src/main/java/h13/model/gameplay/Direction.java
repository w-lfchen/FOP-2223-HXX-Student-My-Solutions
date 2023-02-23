package h13.model.gameplay;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A {@link Direction} is a type of {@linkplain Enum} that represents an orthogonal direction on a 2D plane.
 *
 * @author Ruben Deisenroth
 */
public enum Direction {
    // --Enum Constants-- //

    /**
     * The direction of no movement.
     */
    NONE(0, 0),
    /**
     * The direction upwards or direction of view "north".
     */
    UP(0, -1),
    /**
     * The direction to the right or direction of view "east".
     */
    RIGHT(1, 0),
    /**
     * The direction downwards or direction of view "south".
     */
    DOWN(0, 1),
    /**
     * The direction to the left or direction of view "west".
     */
    LEFT(-1, 0),
    /**
     * The direction upwards and to the right or direction of view "north-east".
     */
    UP_RIGHT(1, -1),
    /**
     * The direction downwards and to the right or direction of view "south-east".
     */
    DOWN_RIGHT(1, 1),
    /**
     * The direction downwards and to the left or direction of view "south-west".
     */
    DOWN_LEFT(-1, 1),
    /**
     * The direction upwards and to the left or direction of view "north-west".
     */
    UP_LEFT(-1, -1);

    // -- Variables-- //

    /**
     * The x-component of the direction. It is meant to be multiplied with the velocity of a sprite to get the horizontal velocity.
     */
    final int x;
    /**
     * The y-component of the direction. It is meant to be multiplied with the velocity of a sprite to get the vertical velocity.
     */
    final int y;

    // --Constructors-- //

    /**
     * Creates a new {@link Direction} .
     *
     * @param x The x-component of the direction.
     * @param y The y-component of the direction.
     */
    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    // --Getters and Setters-- //

    /**
     * Returns the {@linkplain #x X-Component} of the direction.
     * It is meant to be multiplied with the velocity of a sprite to get the horizontal velocity.
     *
     * @return the X-Coordinate of the unit vector of this direction.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the {@linkplain #y Y-Component} of the direction.
     * It is meant to be multiplied with the velocity of a sprite to get the vertical velocity.
     *
     * @return the Y-Coordinate of the unit vector of this direction.
     */
    public int getY() {
        return y;
    }

    // --Utility Methods-- //

    /**
     * Checks whether this direction is a horizontal direction.
     *
     * @return {@code true} if this direction is a horizontal direction.
     */
    public boolean isHorizontal() {
        return y == 0;
    }

    /**
     * Checks whether this direction is a vertical direction.
     *
     * @return {@code true} if this direction is a vertical direction.
     */
    public boolean isVertical() {
        return x == 0;
    }

    /**
     * Checks whether this direction is an orthogonal direction.
     *
     * @return {@code true} if this direction is an orthogonal direction.
     */
    public boolean isOrthogonal() {
        return !equals(NONE) && (isHorizontal() || isVertical());
    }

    /**
     * Returns {@code true} if this direction is a diagonal direction.
     *
     * @return {@code true} if this direction is a diagonal direction.
     */
    public boolean isDiagonal() {
        return !equals(NONE) && !isOrthogonal();
    }

    /**
     * Calculates the length of a given vector of an arbitrary dimension.
     *
     * @param components the components of the vector.
     * @return the length of the vector.
     */
    private double vectorLength(final double... components) {
        return Math.sqrt(Arrays.stream(components).map(c -> Math.pow(c, 2)).sum());
    }

    /**
     * Returns the direction who best matches the given vector.
     *
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     * @return Returns the direction who best matches the given vector.
     */
    public Direction fromVector(final double x, final double y) {
        // convert to unit vector
        final var length = vectorLength(x, y);
        final var unitX = x / length;
        final var unitY = y / length;
        return Arrays
            .stream(values())
            .min(
                // Compare the distance of the unit vector of this direction to the given vector scaled to length 1.
                Comparator.comparingDouble(d -> {
                        // calculate unit vector of this direction
                        final var directionVectorLength = vectorLength(d.getX(), d.getY());
                        final var directionUnitX = d.getX() / directionVectorLength;
                        final var directionUnitY = d.getY() / directionVectorLength;
                        // calculate distance
                        return vectorLength(directionUnitX - unitX, directionUnitY - unitY);
                    }
                )
            )
            .orElse(NONE);
    }

    /**
     * Returns the direction opposite to this direction.
     *
     * @return the direction opposite to this direction.
     */
    public Direction getOpposite() {
        return fromVector(-x, -y);
    }

    /**
     * Checks whether the given direction is opposite to this direction.
     *
     * @param direction The direction to check.
     * @return {@code true} if the given direction is opposite to this direction.
     */
    public boolean isOpposite(final Direction direction) {
        return equals(direction.getOpposite());
    }

    /**
     * Combines this direction with the given direction.
     *
     * @param other The direction to combine with this direction.
     * @return The combined direction.
     */
    public Direction combine(final Direction other) {
        // add the x and y components of this direction and the other direction
        return fromVector(x + other.x, y + other.y);
    }

    /**
     * Returns the direction that is the result of rotating this direction by the given angle in degrees counterclockwise.
     *
     * @param angle The angle to rotate this direction by.
     * @return The direction that is the result of rotating this direction by the given angle in degrees counterclockwise.
     */
    public Direction rotate(final double angle) {
        // convert to radians
        final var radAngle = Math.toRadians(angle);
        final double cos = Math.cos(radAngle);
        final double sin = Math.sin(radAngle);
        return fromVector(x * cos - y * sin, x * sin + y * cos);
    }
}
