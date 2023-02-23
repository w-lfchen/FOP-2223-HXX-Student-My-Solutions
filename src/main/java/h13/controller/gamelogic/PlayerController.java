package h13.controller.gamelogic;

import h13.controller.scene.game.GameController;
import h13.model.gameplay.Direction;
import h13.model.gameplay.sprites.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static h13.controller.GameConstants.*;

/**
 * A {@link PlayerController} is responsible for instantiating and updating the {@linkplain Player players}.
 */
public class PlayerController {
    // --Variables-- //

    /**
     * The {@link Player} controlled by this {@link PlayerController}.
     */
    private final Player player;

    /**
     * The {@link GameController}.
     */
    private final GameController gameController;

    // --Constructors-- //

    /**
     * Creates a new {@link PlayerController}.
     *
     * @param gameController The game controller.
     */
    public PlayerController(final GameController gameController) {
        this.gameController = gameController;
        player = new Player(
            0,
            ORIGINAL_GAME_BOUNDS.getHeight() - SHIP_SIZE,
            PLAYER_VELOCITY,
            gameController.getGameState());
        getGameController().getGameState().getSprites().add(player);
        handleKeyboardInputs();
    }

    // --Getters and Setters-- //

    /**
     * Gets the value of {@link #player} field.
     *
     * @return The value of {@link #player} field.
     * @see #player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the value of {@link #gameController} field.
     *
     * @return The value of {@link #gameController} field.
     * @see #gameController
     */
    public GameController getGameController() {
        return gameController;
    }

    // --Methods-- //

    /**
     * Handles the given {@link KeyEvent} related to a player action.
     *
     * <ul>
     *     <li>The player can move with the arrow keys or the WASD keys.</li>
     *     <li>The player can shoot with the space bar.</li>
     * </ul>
     *
     * @param e A {@link KeyEvent} to handle which relates to a Player action.
     */
    private void playerKeyAction(final KeyEvent e) {
        // reset the values
        player.setKeepShooting(false);
        player.stop();
        // handle the event, map A to be equal to LEFT, same for D and RIGHT, then deal with each key as specified
        getGameController().getGameInputHandler().getKeysPressed().stream().map(c -> c == KeyCode.A ? KeyCode.LEFT : c == KeyCode.D ? KeyCode.RIGHT : c).distinct().forEach(x -> {
                switch (x) {
                    // combine the directions, if both are active, NONE is the result,
                    // otherwise it is the desired direction because the player directions has been set to NONE
                    case LEFT -> player.setDirection(player.getDirection().combine(Direction.LEFT));
                    case RIGHT -> player.setDirection(player.getDirection().combine(Direction.RIGHT));
                    case SPACE -> getPlayer().setKeepShooting(true);
                }
            }
        );
    }

    /**
     * Registers the keyboard inputs to handle the player actions.
     */
    private void handleKeyboardInputs() {
        final var gameInputHandler = getGameController().getGameInputHandler();
        gameInputHandler.addOnKeyPressed(this::playerKeyAction);
        gameInputHandler.addOnKeyReleased(this::playerKeyAction);
    }
}
