package helperbot.Ui;

/**
 * Represents the user interface for testing purposes.
 */
public class TestUi extends Ui {
    private String output;

    /**
     * Constructor for TestUi.
     *
     * @param response Output of the user interface
     */
    @Override
    public void printResponse(String response) {
        output = response;
    }

    /**
     * Prints an error message.
     *
     * @param error Error message to be printed
     */
    @Override
    public void printError(String error) {
        output = error;
    }

    /**
     * Returns the output of the user interface.
     *
     * @return Output of the user interface
     */
    public String getOutput() {
        return output;
    }
}