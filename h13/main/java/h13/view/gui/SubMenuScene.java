package h13.view.gui;

import h13.controller.GameConstants;
import h13.controller.scene.SceneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * The {@link SubMenuScene} is a {@link MenuScene} that displays a sub menu.
 *
 * @param <SC> The type of the {@link SceneController} that controls this {@link SubMenuScene}.
 * @param <CR> The type of the Content Root {@link Node} of this {@link SubMenuScene}.
 */
public abstract class SubMenuScene<SC extends SceneController, CR extends Node> extends MenuScene<SC> {
    /**
     * A Typesafe reference to the root Node of this Scene.
     */
    private final BorderPane root;

    /**
     * A Typesafe reference to the content root Node of this Scene.
     */
    private final CR contentRoot;

    /**
     * Creates a new {@link SubMenuScene}.
     *
     * @param contentRoot The content root Node of this Scene.
     * @param controller  The SceneController that controls this {@link SubMenuScene}.
     * @param title       The title of this {@link SubMenuScene}.
     */
    public SubMenuScene(final CR contentRoot, final SC controller, final String title) {
        super(new BorderPane(), controller);
        // Typesafe reference to the root group of the scene.
        root = (BorderPane) getRoot();
        this.contentRoot = contentRoot;
        init(title);
    }

    /**
     * Initialize the content of the scene.
     */
    private void init(final String title) {
        final Label label = new Label(title);
        label.setFont(GameConstants.TITLE_FONT);
        label.setPadding(new Insets(20, 20, 20, 20));
        root.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);

        root.setCenter(contentRoot);

        final var button = new Button("Go Back");
        button.setFont(Font.font(27));
        button.setPrefSize(600, 72);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(getController()::loadMainMenuScene);
        button.setPadding(new Insets(20, 20, 20, 20));
        //root.setPadding(new Insets(20,20,20,20));
        root.setBottom(button);
        BorderPane.setAlignment(button, Pos.CENTER);
    }

    /**
     * Returns the value of the {@link #contentRoot} field.
     *
     * @return The value of the {@link #contentRoot} field.
     * @see #contentRoot
     */
    public CR getContentRoot() {
        return contentRoot;
    }
}
