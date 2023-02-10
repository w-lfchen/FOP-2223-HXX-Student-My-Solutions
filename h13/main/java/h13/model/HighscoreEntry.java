package h13.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * A Data class that represents a "Highscore" entry.
 *
 * @see h13.view.gui.HighscoreScene
 */
public class HighscoreEntry {

    // --Variables-- //

    /**
     * The name of the player.
     */
    final SimpleStringProperty playerName;
    /**
     * The date of the highscore entry.
     */
    final SimpleStringProperty date;
    /**
     * The score that was achieved.
     */
    final SimpleIntegerProperty score;

    // --Constructors-- //

    /**
     * Creates a new HighscoreEntry.
     *
     * @param playerName The name of the player.
     * @param date       The date of the highscore entry.
     * @param score      The score that was achieved.
     */
    public HighscoreEntry(final String playerName, final String date, final int score) {
        this.playerName = new SimpleStringProperty(playerName);
        this.date = new SimpleStringProperty(date);
        this.score = new SimpleIntegerProperty(score);
    }

    // --Getters and Setters-- //

    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     * @see #playerName
     */
    public String getPlayerName() {
        return playerName.get();
    }

    /**
     * Sets the name of the player to the given value.
     *
     * @param playerName The new name of the player.
     * @see #playerName
     */
    public void setPlayerName(final String playerName) {
        this.playerName.set(playerName);
    }

    /**
     * Returns the date of the highscore entry.
     *
     * @return The date of the highscore entry.
     * @see #date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Sets the date of the highscore entry to the given value.
     *
     * @param date The new date of the highscore entry.
     * @see #date
     */
    public void setDate(final String date) {
        this.date.set(date);
    }

    /**
     * Returns the Score that was achieved.
     *
     * @return The score that was achieved.
     * @see #score
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Sets the score to the given value.
     *
     * @param score The new score.
     * @see #score
     */
    public void setScore(final int score) {
        this.score.set(score);
    }
}
