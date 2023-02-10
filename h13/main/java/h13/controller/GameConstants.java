package h13.controller;


import h13.model.gameplay.Direction;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

/**
 * The {@link GameConstants} class contains all the constants that are used in the game.
 */
public final class GameConstants {
    /**
     * The {@link Bounds} of the original game screen.
     */
    public static Bounds ORIGINAL_GAME_BOUNDS = new BoundingBox(
        0,
        0,
        256,
        224
    );

    /**
     * The aspect ratio
     */
    public static double ASPECT_RATIO = ORIGINAL_GAME_BOUNDS.getWidth() / ORIGINAL_GAME_BOUNDS.getHeight();
    
    /**
     * The amount of enemy rows.
     */
    public static int ENEMY_ROWS = 5;

    /**
     * The amount of enemy columns.
     */
    public static int ENEMY_COLS = 11;

    /**
     * The initial movement direction of the enemies.
     */
    public static Direction INITIAL_ENEMY_MOVEMENT_DIRECTION = Direction.RIGHT;

    /**
     * The initial movement speed of the enemies.
     */
    public static double INITIAL_ENEMY_MOVEMENT_VELOCITY = 10;

    /**
     * The amount of speed that the enemies gain per iteration.
     */
    public static double ENEMY_MOVEMENT_SPEED_INCREASE = 0.3;

    /**
     * The relative amount of padding between the enemies.
     */
    public static double SHIP_PADDING = 0.01;

    /**
     * The relative horizontal movement distance.
     */
    public static double HORIZONTAL_ENEMY_MOVE_DISTANCE = 0.1;
    
    /**
     * The vertical movement distance.
     */
    public static double VERTICAL_ENEMY_MOVE_DISTANCE = .03 * ORIGINAL_GAME_BOUNDS.getHeight();

    /**
     * The amount of Pixels that the enemies move horizontally each movement iteration.
     */
    public static double HORIZONTAL_ENEMY_MOVE_SPACE = ORIGINAL_GAME_BOUNDS.getWidth() * (1 - HORIZONTAL_ENEMY_MOVE_DISTANCE);

    /**
     * The Space (including padding) each enemy has.
     */
    public static double CHUNK_SIZE = HORIZONTAL_ENEMY_MOVE_SPACE / ENEMY_COLS;

    /**
     * The relative ship width.
     */
    public static double RELATIVE_SHIP_SIZE = (1d - HORIZONTAL_ENEMY_MOVE_DISTANCE) / ENEMY_COLS - 2 * SHIP_PADDING;

    /**
     * The ship width.
     */
    public static double SHIP_SIZE = ORIGINAL_GAME_BOUNDS.getWidth() * RELATIVE_SHIP_SIZE;

    /**
     * The Bullet width.
     */
    public static double BULLET_WIDTH = 1;

    /**
     * The Bullet height.
     */
    public static double BULLET_HEIGHT = 5;

    /**
     * The probability that the enemy will shoot a bullet each frame.
     */
    public static double ENEMY_SHOOTING_PROBABILITY = 0.0005;

    /**
     * The Player's velocity (in pixels per second).
     */
    public static double PLAYER_VELOCITY = 0.9 * ORIGINAL_GAME_BOUNDS.getWidth();

    /**
     * The bullet velocity (in pixels per second).
     */
    public static double BULLET_VELOCITY = 0.75 * ORIGINAL_GAME_BOUNDS.getWidth();

    /**
     * The HUD font path.
     */
    public static String HUD_FONT_PATH = "/h13/fonts/PressStart2P-Regular.ttf";

    /**
     * The HUD font size.
     */
    public static double HUD_FONT_SIZE = 0.045 * ORIGINAL_GAME_BOUNDS.getHeight();

    /**
     * HUD Padding.
     */
    public static double HUD_PADDING = 0.02 * ORIGINAL_GAME_BOUNDS.getHeight();

    /**
     * HUD height.
     */
    public static double HUD_HEIGHT = HUD_FONT_SIZE + 2 * HUD_PADDING;

    /**
     * The HUD text color.
     */
    public static Color HUD_TEXT_COLOR = Color.WHITE;

    /**
     * The HUD font.
     */
    public static javafx.scene.text.Font HUD_FONT = javafx.scene.text.Font.loadFont(GameConstants.class.getResourceAsStream(GameConstants.HUD_FONT_PATH), HUD_FONT_SIZE);

    /**
     * The HUD font.
     */
    public static javafx.scene.text.Font TITLE_FONT = javafx.scene.text.Font.loadFont(GameConstants.class.getResourceAsStream(GameConstants.HUD_FONT_PATH), 30);

    /**
     * Border width.
     */
    public static double BORDER_WIDTH = 2;

    /**
     * Border Color.
     */
    public static javafx.scene.paint.Color BORDER_COLOR = javafx.scene.paint.Color.PALEGREEN;
}
