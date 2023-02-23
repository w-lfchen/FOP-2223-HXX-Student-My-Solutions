package h13.controller.scene;

import javafx.scene.Scene;
import org.jetbrains.annotations.Nullable;

/**
 * Data class for a {@link Scene} and its {@link SceneController}.
 *
 * @param scene      The {@link Scene}.
 * @param controller The {@link SceneController}. Can be null if no initialisation is required.
 */
public record SceneAndController(Scene scene, @Nullable SceneController controller) {

    // --Getters and Setters-- //

    /**
     * Gets the {@link Scene}.
     *
     * @return The {@link Scene}.
     * @see #scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the {@link SceneController}.
     *
     * @return The {@link SceneController}.
     * @see #controller
     */
    public @Nullable SceneController getController() {
        return controller;
    }

    // --Utility Methods-- //

    /**
     * Creates a new {@link SceneAndController} with the given {@link Scene}.
     * If the {@link Scene} is a {@link ControlledScene}, the {@link SceneController} is retrieved from it.
     * Otherwise, the {@link SceneController} will be null.
     *
     * @param scene The {@link Scene}.
     * @return The new {@link SceneAndController}.
     */
    public static SceneAndController fromScene(final Scene scene) {
        return new SceneAndController(scene, scene instanceof ControlledScene<?> cs ? cs.getController() : null);
    }
}
