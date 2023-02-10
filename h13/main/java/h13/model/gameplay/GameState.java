package h13.model.gameplay;

import h13.model.gameplay.sprites.Enemy;
import h13.model.gameplay.sprites.Sprite;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@link GameState} class is responsible for keeping track of the current state of the game.
 */
public class GameState {

    // --Variables-- //
    /**
     * The {@linkplain Sprite Sprites} that are present in the game.
     * This is a set because there is no guarantee that the sprites are not duplicated.
     * This is not a problem because the set is used to remove duplicates.
     *
     * @see #getSprites()
     */
    private final Set<Sprite> sprites = new HashSet<>();

    /**
     * The {@linkplain Sprite Sprites} that should be added in the next update iteration.
     */
    private final Set<Sprite> toAdd = new HashSet<>();

    /**
     * @see EnemyMovement
     */
    private final EnemyMovement enemyMovement = new EnemyMovement(this);


    // --Getters and Setters-- //

    /**
     * Gets the value of {@link #sprites} field.
     *
     * @return The value of {@link #sprites} field.
     * @see #sprites
     */
    public Set<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Gets the value of {@link #toAdd} field.
     *
     * @return The value of {@link #toAdd} field.
     * @see #toAdd
     */
    public Set<Sprite> getToAdd() {
        return toAdd;
    }

    /**
     * Gets a {@link Set} of all {@linkplain Enemy enemies}.
     *
     * @return A {@link Set} of all {@linkplain Enemy enemies}.
     */
    public Set<Enemy> getEnemies() {
        return getSprites().stream()
            .filter(Enemy.class::isInstance)
            .map(Enemy.class::cast)
            .collect(Collectors.toSet());
    }

    /**
     * Gets all the {@link Enemy}s where {@link Enemy#isAlive()} returns true.
     *
     * @return The {@link Enemy}s where {@link Enemy#isAlive()} returns true.
     * @see Enemy#isAlive()
     */
    public Set<Enemy> getAliveEnemies() {
        return getSprites().stream()
            .filter(s -> s instanceof Enemy e && e.isAlive())
            .map(Enemy.class::cast)
            .collect(Collectors.toSet());
    }

    /**
     * Gets the value of {@link #enemyMovement} field.
     *
     * @return The value of {@link #enemyMovement} field.
     * @see #enemyMovement
     */
    public EnemyMovement getEnemyMovement() {
        return enemyMovement;
    }
}
