package helperbot.gui;

import java.io.IOException;

import helperbot.HelperBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI application for HelperBot using FXML.
 */
public class Main extends Application {
    /**
     * A GUI application for HelperBot using FXML.
     */
    private final HelperBot helperBot = new HelperBot();

    /**
     * Starts the GUI application.
     *
     * @param stage The stage for the GUI application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("HelperBot ChatBot");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(helperBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
