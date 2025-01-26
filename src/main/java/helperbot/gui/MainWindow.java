package helperbot.gui;


import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import helperbot.HelperBot;


/**
 * Controller for the main GUI. Provides the layout for the other controls.
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
    private Image userImage = new Image(
        this.getClass().getResourceAsStream("/images/user.png"));
    private Image helperBotImage = new Image(
        this.getClass().getResourceAsStream("/images/bot.png"));
    private HelperBot helperBot;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the HelperBot instance */
    public void setBot(HelperBot h) {
        helperBot = h;
        var dialogBox = new DialogBox(helperBot.showWelcome(), helperBotImage);
        dialogBox.flip();
        dialogContainer.getChildren().addAll(dialogBox);

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = helperBot.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getBotDialog(response, helperBotImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }


}
