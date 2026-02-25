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
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = chloe.getResponse(input);

        boolean isError = isLikelyError(response);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                isError
                        ? DialogBox.getErrorDialog(response, chloeImage)
                        : DialogBox.getDukeDialog(response, chloeImage)
        );

        userInput.clear();

        if (chloe.isExit()) {
            Platform.exit();
        }
    }

    /**
     * Heuristically determines if a response is an error message.
     *
     * @param response the response text from Chloe
     * @return true if the response is likely an error message
     */
    private boolean isLikelyError(String response) {
        if (response == null) {
            return false;
        }
        String s = response.toLowerCase();
        return s.contains("invalid")
                || s.contains("error")
                || s.contains("oops")
                || s.contains("failed")
                || s.contains("please use")
                || s.contains("cannot")
                || s.contains("don't");
    }
}
