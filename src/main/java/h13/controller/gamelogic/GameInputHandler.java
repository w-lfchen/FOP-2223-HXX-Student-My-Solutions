package h13.controller.gamelogic;

import h13.view.gui.GameScene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A {@link GameInputHandler} is responsible for handling the input from the user on a {@link h13.view.gui.GameScene}.
 */
public class GameInputHandler {
    // --Variables-- //

    /**
     * The keys that were pressed during the last frame.
     * When a key is pressed, it is added to this list after the onKeyPressed event is handled.
     */
    private final Set<KeyCode> keysPressed = new HashSet<>();

    /**
     * A List of {@linkplain EventHandler event handlers} that are called when a key is pressed.
     */
    private final List<EventHandler<KeyEvent>> onKeyPressed = new ArrayList<>();

    /**
     * A List of event handlers that are called when a key is released.
     */

    private final List<EventHandler<KeyEvent>> onKeyReleased = new ArrayList<>();
    /**
     * A List of event handlers that are called when a key is typed.
     */
    private final List<EventHandler<KeyEvent>> onKeyTyped = new ArrayList<>();

    // --Constructors-- //

    /**
     * Creates a new {@link GameInputHandler}.
     *
     * @param scene The scene to handle input for.
     */
    public GameInputHandler(final GameScene scene) {
        handleKeyboardInputs(scene);
    }

    // --Getters and Setters-- //

    /**
     * Gets the value of {@link #keysPressed} field.
     *
     * @return the value of the {@link #keysPressed} field.
     * @see #keysPressed
     */
    public Set<KeyCode> getKeysPressed() {
        return keysPressed;
    }

    // --Methods-- //

    /**
     * Adds the given event handler to the {@link #onKeyPressed} list.
     *
     * @param eventHandler The key to add.
     * @see #onKeyPressed
     */
    public void addOnKeyPressed(final EventHandler<KeyEvent> eventHandler) {
        onKeyPressed.add(eventHandler);
    }

    /**
     * Adds the given event handler to the {@link #onKeyReleased} list.
     *
     * @param eventHandler The key to add.
     * @see #onKeyReleased
     */
    public void addOnKeyReleased(final EventHandler<KeyEvent> eventHandler) {
        onKeyReleased.add(eventHandler);
    }

    /**
     * Adds the given event handler to the {@link #onKeyTyped} list.
     *
     * @param eventHandler The key to add.
     * @see #onKeyTyped
     */
    public void addOnKeyTyped(final EventHandler<KeyEvent> eventHandler) {
        onKeyTyped.add(eventHandler);
    }

    /**
     * Set up the keyboard handlers for the given scene.
     *
     * @param scene The scene to handle input for.
     */
    private void handleKeyboardInputs(final GameScene scene) {
        scene.setOnKeyPressed(e -> {
            keysPressed.add(e.getCode());
            onKeyPressed.forEach(eventHandler -> eventHandler.handle(e));
        });
        scene.setOnKeyReleased(e -> {
            keysPressed.remove(e.getCode()); // remove the key from the list of pressed keys
            onKeyReleased.forEach(eventHandler -> eventHandler.handle(e));
        });
        scene.setOnKeyTyped(e -> onKeyTyped.forEach(eventHandler -> eventHandler.handle(e)));
    }
}
