package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Chloe using FXML.
 */
public class Main extends Application {

    private final Chloe chloe = new Chloe();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setTitle("Chloe");
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setChloe(chloe);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

