package h13.controller.scene;

/**
 * An interface for a {@linkplain ControlledScene controlled scene}.
 *
 * @see javafx.scene.Scene
 * @see SceneController
 */
public interface ControlledScene<SC extends SceneController> {
    /**
     * Gets the {@link SceneController} which is responsible for controlling the scene.
     *
     * @return The {@link SceneController}.
     */
    SC getController();
}
