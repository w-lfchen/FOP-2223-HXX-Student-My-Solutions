package h13.view.gui;

import h13.controller.scene.menu.HighscoreController;
import h13.model.HighscoreEntry;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * The {@link HighscoreScene} is a {@link SubMenuScene} that displays the table of highscores.
 */
public class HighscoreScene extends SubMenuScene<HighscoreController, TableView<HighscoreEntry>> {

    /**
     * Creates a new {@link HighscoreScene}.
     */
    public HighscoreScene() {
        super(new TableView<>(), new HighscoreController(), "Highscores");
        init();
    }

    /**
     * Initialize the content of the scene.
     */
    private void init() {
        final var table = getController().highscoreTableView = getContentRoot();
        table.setPrefSize(200, 200);
        table.getColumns().add(getController().playerTableColumn = new TableColumn<>("Player"));
        table.getColumns().add(getController().dateTableColumn = new TableColumn<>("Date"));
        table.getColumns().add(getController().scoreTableColumn = new TableColumn<>("Score"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
