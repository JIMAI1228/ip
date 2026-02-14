package duke.ui;

import duke.Chloe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Chloe chloe;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jimai.png"));
    private final Image chloeImage = new Image(this.getClass().getResourceAsStream("/images/Chloe.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Chloe instance.
     *
     * @param c the Chloe instance
     */
    public void setChloe(Chloe c) {
        chloe = c;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Chloe.\nWhat can I do for you?", chloeImage)
        );
    }

    /**
     * Handles user input and displays Chloe's response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chloe.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, chloeImage)
        );

        userInput.clear();

        if (chloe.isExit()) {
            Platform.exit();
        }
    }
}
