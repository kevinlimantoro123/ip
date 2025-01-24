package helperbot.Ui;

public class TestUi extends Ui {
    private String output;

    @Override
    public void printResponse(String response) {
        output = response;
    }

    @Override
    public void printError(String error) {
        output = error;
    }

    public String getOutput() {
        return output;
    }
}