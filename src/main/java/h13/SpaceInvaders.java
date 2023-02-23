package h13;

import h13.controller.scene.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;
import org.tudalgo.algoutils.student.Student;

/**
 * The main class of the application "Space Invaders".
 */
public class SpaceInvaders extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        //Student.setCrashEnabled(false);
        stage.setMinWidth(450);
        stage.setMinHeight(400);
        SceneSwitcher.loadScene(SceneSwitcher.SceneType.MAIN_MENU, stage);
    }

    /**
     * The main method of the application.
     *
     * @param args The launch arguments of the application.
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
