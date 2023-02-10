package h13.view.gui;

import h13.controller.scene.menu.SettingsController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Set;

/**
 * The {@link SettingsScene} is a {@link SubMenuScene} that displays the "Settings" menu.
 */
public class SettingsScene extends SubMenuScene<SettingsController, TabPane> {

    /**
     * Creates a new {@link SettingsScene}.
     */
    public SettingsScene() {
        super(new TabPane(), new SettingsController(), "Settings");
        init();
    }

    /**
     * Initialize the content of the scene.
     */
    private void init() {
        // get needed attributes
        TabPane root = getContentRoot();
        SettingsController controller = getController();

        // initialise all checkboxes, and set controller references
        CheckBox instantShooting = controller.instantShootingCheckBox = new CheckBox("Instant shooting");
        CheckBox fullscreen = controller.fullscreenCheckBox = new CheckBox("Fullscreen");
        CheckBox loadTextures = controller.loadTexturesCheckBox = new CheckBox("Load textures");
        CheckBox loadBackground = controller.loadBackgroundCheckBox = new CheckBox("Load background");

        // initialise the shooting delay slider, set attributes and controller reference
        Slider enemyShootingDelay = controller.enemyShootingDelaySlider = new Slider(0, 10000, 0);
        Label enemyShootingDelayLabel = new Label("Enemy shooting delay", enemyShootingDelay);
        enemyShootingDelayLabel.textProperty().bind(new SimpleStringProperty("Enemy shooting delay: ").concat(enemyShootingDelay.valueProperty().asString()).concat(" ms"));

        // initialise the shooting probability slider, set attributes and controller reference
        Slider enemyShootingProbability = controller.enemyShootingProbabilitySlider = new Slider(0, 1, 1);
        Label enemyShootingProbabilityLabel = new Label("Enemy shooting probability", enemyShootingProbability);
        enemyShootingProbabilityLabel.textProperty().bind(new SimpleStringProperty("Enemy shooting probability: ").concat(enemyShootingProbability.valueProperty().multiply(100).asString()).concat(" %"));

        // initialise boxes to store the checkboxes and sliders
        VBox graphicBox = new VBox(10, fullscreen, loadTextures, loadBackground);
        VBox gameSettingsBox = new VBox(10, instantShooting, enemyShootingDelayLabel, enemyShootingProbabilityLabel);

        // set attributes for all boxes
        Set<VBox> boxes = Set.of(graphicBox, gameSettingsBox);
        for (VBox box : boxes) {
            box.setAlignment(Pos.CENTER_LEFT);
            box.setPadding(new Insets(100));
        }
        // add tabs for the settings, and the boxes to the tabs
        root.getTabs().add(new Tab("Game Settings", gameSettingsBox));
        root.getTabs().add(new Tab("Graphics", graphicBox));
        // set attributes of all tabs
        for (Tab tab : root.getTabs()) {
            tab.setClosable(false);
        }
    }
}
