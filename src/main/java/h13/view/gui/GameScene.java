package h13.view.gui;

import h13.controller.scene.ControlledScene;
import h13.controller.scene.game.GameController;
import javafx.beans.InvalidationListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import static h13.controller.GameConstants.ASPECT_RATIO;
import static h13.controller.GameConstants.ORIGINAL_GAME_BOUNDS;

/**
 * A {@link GameScene} is a {@link Scene} that contains the {@link GameBoard} and is controlled by a {@link GameController}.
 */
public class GameScene extends Scene implements ControlledScene<GameController> {

    // --Variables-- //

    /**
     * A Typesafe reference to the root Node of this Scene.
     */
    private final Group root;

    /**
     * The {@link GameBoard} of this Scene.
     * This is the only Node that is added to the root Node.
     *
     * @see GameBoard
     */
    private GameBoard gameBoard;
    /**
     * The {@link GameController} that controls this {@link GameScene}.
     *
     * @see GameController
     */
    private GameController gameController;

    // --Constructors-- //

    /**
     * Creates a new GameScene.
     */
    public GameScene() {
        super(new Group(), Color.BLACK);
        // Typesafe reference to the root group of the scene.
        root = (Group) getRoot();
        init();
    }

    // --Getters and Setters-- //

    /**
     * Gets the value of {@link #gameBoard} field.
     *
     * @return The value of {@link #gameBoard} field.
     * @see #gameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public GameController getController() {
        return gameController;
    }

    // --Methods-- //

    /**
     * Initializes the GameScene.
     */
    protected void init() {
        // Game Board
        initGameBoard();

        // Game Controller
        gameController = new GameController(this);
    }

    /**
     * Initializes the GameBoard, binding its size to the size of the Scene.
     */
    private void initGameBoard() {
        gameBoard = new GameBoard(ORIGINAL_GAME_BOUNDS.getWidth(), ORIGINAL_GAME_BOUNDS.getHeight(), this);

        // call the method once to set the properties for the first time
        setGameBoardProperties();
        // create a new listener that calls the setProperties method if triggered
        InvalidationListener sizeListener = observable -> setGameBoardProperties();
        // add said listener to the properties of the GameScene
        this.widthProperty().addListener(sizeListener);
        this.heightProperty().addListener(sizeListener);

        root.getChildren().add(gameBoard);
    }

    private void setGameBoardProperties() {
        // initialise variables
        double nextHeight = this.getHeight();
        double nextWidth = nextHeight * ASPECT_RATIO;
        // since height has been adjusted to fit, swap that around if width doesn't
        if (nextWidth > this.getWidth()) {
            nextWidth = this.getWidth();
            nextHeight = nextWidth / ASPECT_RATIO;
        }
        // filter out edge case due to javafx being weird
        if (!(nextWidth == 0 || nextHeight == 0)) {
            // Size (correctly calculated values)
            gameBoard.widthProperty().set(nextWidth);
            gameBoard.heightProperty().set(nextHeight);
        }
        // Positioning (middle)
        gameBoard.setTranslateX((this.getWidth() - gameBoard.getWidth()) / 2 - gameBoard.getLayoutX());
        gameBoard.setTranslateY((this.getHeight() - gameBoard.getHeight()) / 2 - gameBoard.getLayoutY());
    }
}
