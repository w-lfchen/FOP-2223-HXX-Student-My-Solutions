package h13.model.gameplay;

/**
 * An interface that defines the methods that an Updatable object must implement.
 * An Updatable Object is an object that needs to update its state every frame.
 */
public interface Updatable {
    /**
     * Updates the object.
     *
     * @param elapsedTime The time elapsed since the last update in seconds.
     */
    void update(final double elapsedTime);
}
