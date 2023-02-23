package h13.view.gui;

import h13.controller.GameConstants;
import h13.controller.scene.menu.MainMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The {@link MainMenuScene} is a {@link MenuScene} that displays the main menu of the game.
 */
public class MainMenuScene extends MenuScene<MainMenuController> {

    /**
     * A Typesafe reference to the root Node of this Scene.
     */
    private final BorderPane root;

    /**
     * Creates a new {@link MainMenuScene}.
     */
    public MainMenuScene() {
        super(new BorderPane(), new MainMenuController());
        // Typesafe reference to the root group of the scene.
        root = (BorderPane) getRoot();
        init();
    }

    /**
     * Initialize the content of the scene.
     */
    private void init() {
        final Label titleLabel = new Label("Space Invaders");
        titleLabel.setFont(GameConstants.TITLE_FONT);
        titleLabel.setPadding(new Insets(20, 20, 20, 20));
        // StarWars-like perspective effect
//        PerspectiveTransform pt = new PerspectiveTransform(
//            10.0f,
//            10.0f,
//            210.0f,
//            40.0f,
//            210.0f,
//            60.0f,
//            10.0f,
//            90.0f
//        );
//        label.setEffect(pt);
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        final var buttonsVbox = new VBox();
        buttonsVbox.setPrefSize(200, 100);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setSpacing(10);
        root.setCenter(buttonsVbox);

        final var startGameButton = new Button("Start Game");
        startGameButton.setOnAction(getController()::loadGameScene);
        buttonsVbox.getChildren().add(startGameButton);

        final var highScoreButton = new Button("Highscores");
        highScoreButton.setOnAction(getController()::loadHighscoreScene);
        buttonsVbox.getChildren().add(highScoreButton);

        final var settingsButton = new Button("Settings");
        settingsButton.setOnAction(getController()::loadSettingsScene);
        buttonsVbox.getChildren().add(settingsButton);

        final var aboutButton = new Button("About");
        aboutButton.setOnAction(getController()::loadAboutScene);
        buttonsVbox.getChildren().add(aboutButton);

        final var quitButton = new Button("Quit");
        quitButton.setOnAction(getController()::quit);
        buttonsVbox.getChildren().add(quitButton);

        buttonsVbox.getChildren().stream()
            .filter(Button.class::isInstance)
            .map(Button.class::cast)
            .forEach(button -> {
                button.setPrefSize(200, 50);
                //button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            });
    }
}
