package h13.view.gui;

import h13.controller.scene.ControlledScene;
import h13.controller.scene.SceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

/**
 * The {@link MenuScene} is a {@link Scene} that is controlled by a {@link SceneController} which displays a Menu.
 *
 * @param <SC> The type of the {@link SceneController}.
 */
public abstract class MenuScene<SC extends SceneController> extends Scene implements ControlledScene<SC> {

    /**
     * The SceneController that controls this {@link MenuScene}.
     */
    private final SC controller;

    /**
     * Creates a new {@link MenuScene}.
     * @param root The root Node of this Scene.
     * @param controller The SceneController that controls this {@link MenuScene}.
     */
    public MenuScene(final Region root, final SC controller) {
        super(root);
        this.controller = controller;
        root.setPrefSize(600, 460);
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
        // apply styles
        root.getStylesheets().add("h13/view.gui/menuStyles.css");
        root.getStylesheets().add("h13/view.gui/darkMode.css");
    }

    @Override
    public SC getController() {
        return controller;
    }
}
