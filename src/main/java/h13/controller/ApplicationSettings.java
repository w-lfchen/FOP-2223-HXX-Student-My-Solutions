package h13.controller;

import h13.model.HighscoreEntry;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Data class that contains the settings of the application.
 * Also handles saving and loading of the settings from the filesystem.
 */
public final class ApplicationSettings {
    // --Constructors-- //

    /**
     * Overrides the default constructor.
     */
    private ApplicationSettings() {
        throw new IllegalStateException("Cannot instantiate ApplicationSettings");
    }

    // --Variables, Getters and Setters-- //
    /**
     * Whether the game should be played in fullscreen mode.
     */
    private static final BooleanProperty fullscreen = new SimpleBooleanProperty(false);

    /**
     * Gets the {@link #fullscreen}-property.
     *
     * @return The {@link #fullscreen}-property.
     * @see #fullscreen
     */
    public static BooleanProperty fullscreenProperty() {
        return fullscreen;
    }

    /**
     * Enemy Shooting Delay in milliseconds.
     */
    private static final DoubleProperty enemyShootingDelay = new SimpleDoubleProperty(1000);

    /**
     * Gets the {@link #enemyShootingDelay}-property.
     *
     * @return The {@link #enemyShootingDelay}-property.
     * @see #enemyShootingDelay
     */
    public static DoubleProperty enemyShootingDelayProperty() {
        return enemyShootingDelay;
    }

    /**
     * The probability that an enemy shoots each frame.
     */
    public static final DoubleProperty enemyShootingProbability = new SimpleDoubleProperty(0.0005);

    /**
     * Gets the {@link #enemyShootingProbability}-property.
     *
     * @return The {@link #enemyShootingProbability}-property.
     * @see #enemyShootingProbability
     */
    public static DoubleProperty enemyShootingProbabilityProperty() {
        return enemyShootingProbability;
    }

    /**
     * Whether to load the textures of sprites from the filesystem.
     */
    private static final BooleanProperty loadTextures = new SimpleBooleanProperty(true);

    /**
     * Gets the {@link #loadTextures}-property.
     *
     * @return The {@link #loadTextures}-property.
     * @see #loadTextures
     */
    public static BooleanProperty loadTexturesProperty() {
        return loadTextures;
    }

    /**
     * Whether to load the background images from the filesystem.
     */
    private static final BooleanProperty loadBackground = new SimpleBooleanProperty(true);

    /**
     * Gets the {@link #loadBackground}-property.
     *
     * @return The {@link #loadBackground}-property.
     * @see #loadBackground
     */
    public static BooleanProperty loadBackgroundProperty() {
        return loadBackground;
    }

    /**
     * Whether to enable instant shooting.
     */
    private static final BooleanProperty instantShooting = new SimpleBooleanProperty(false);

    /**
     * Gets the {@link #instantShooting}-property.
     *
     * @return The {@link #instantShooting}-property.
     * @see #instantShooting
     */
    public static BooleanProperty instantShootingProperty() {
        return instantShooting;
    }

    /**
     * Whether to enable horizontal enemy movement.
     */
    private static final BooleanProperty enemyHorizontalMovement = new SimpleBooleanProperty(true);

    /**
     * Gets the {@link #enemyHorizontalMovement}-property.
     *
     * @return The {@link #enemyHorizontalMovement}-property.
     * @see #enemyHorizontalMovement
     */
    public static BooleanProperty enemyHorizontalMovementProperty() {
        return enemyHorizontalMovement;
    }

    /**
     * Whether to enable vertical enemy movement.
     */
    private static final BooleanProperty enemyVerticalMovement = new SimpleBooleanProperty(true);

    /**
     * Gets the {@link #enemyVerticalMovement}-property.
     *
     * @return The {@link #enemyVerticalMovement}-property.
     * @see #enemyVerticalMovement
     */
    public static BooleanProperty enemyVerticalMovementProperty() {
        return enemyVerticalMovement;
    }

    /**
     * The list of highscore entries.
     */
    private static final ObservableList<HighscoreEntry> highscores = FXCollections.observableArrayList(
        //new HighscoreEntry("Player 1", new Date().toString(), 100) // Example entry
    );

    /**
     * Gets the {@link #highscores} list.
     *
     * @return The {@link #highscores} list.
     */
    public static ObservableList<HighscoreEntry> getHighscores() {
        return highscores;
    }
}
