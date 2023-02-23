package h13.controller.gamelogic;

import h13.controller.scene.game.GameController;
import h13.model.gameplay.EnemyMovement;
import h13.model.gameplay.sprites.Bullet;
import h13.model.gameplay.sprites.Enemy;

import static h13.controller.GameConstants.*;

/**
 * An {@link EnemyController} is responsible for instantiating and updating the {@linkplain Enemy enemies}.
 */
@SuppressWarnings("ClassCanBeRecord")
public final class EnemyController {

    // --Variables-- //

    /**
     * The {@link GameController}.
     */
    private final GameController gameController;

    // --Constructors-- //

    /**
     * Creates a new EnemyController.
     *
     * @param gameController The {@linkplain GameController game controller}.
     */
    public EnemyController(
        final GameController gameController) {
        this.gameController = gameController;
        nextLevel();
    }

    // --Getters and Setters-- //

    /**
     * Gets the value of {@link #gameController} field.
     *
     * @return The value of {@link #gameController} field.
     * @see #gameController
     */
    public GameController getGameController() {
        return gameController;
    }

    // --Utility Methods-- //


    /**
     * Checks whether all the {@linkplain  Enemy enemies} are dead.
     *
     * @return {@code true} if all the {@linkplain  Enemy enemies} are dead, {@code false} otherwise.
     * @see Enemy#isDead()
     */
    public boolean isDefeated() {
        // return depends on whether the gameState contains enemies that are alive, true if it doesn't, otherwise false
        return getGameController().getGameState().getAliveEnemies().isEmpty();
    }

    // --Other Methods-- //

    /**
     * Initialises the {@linkplain  Enemy enemies} for the next level, clearing the current enemies and adding new ones.
     * Also resets the {@link EnemyMovement} using {@link EnemyMovement#nextRound()}.
     */
    public void nextLevel() {
        // cleanup previous level
        getGameController().getGameState().getSprites().removeIf(s -> s instanceof Bullet b && b.getOwner() instanceof Enemy);

        // add new enemies
        final var padding = CHUNK_SIZE / 2 - SHIP_SIZE / 2;
        for (int i = 0; i < ENEMY_COLS; i++) {
            for (int j = 0; j < ENEMY_ROWS; j++) {
                final var enemy = new Enemy(
                    i,
                    j,
                    0,
                    (ENEMY_ROWS - j) * 10,
                    getGameController().getGameState()
                );

                enemy.setX(CHUNK_SIZE * i + padding);
                enemy.setY(CHUNK_SIZE * j + padding + HUD_HEIGHT);

                getGameController().getGameState().getSprites().add(enemy);
            }
        }
        // reset enemy movement
        getGameController().getGameState().getEnemyMovement().nextRound();
    }
}
