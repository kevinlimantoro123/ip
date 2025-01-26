package helperbot.Ui;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a horizontal line.
     */
    public String printHorizontalLine() {
        String res = "";
        for (int i = 0; i < 40; i++) {
            res = res + "-";
        }
        return res;
    }

    /**
     * Reads the command from the user.
     *
     * @return Command from the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints an error message.
     *
     * @param message Error message to be printed
     */
    public void printError(String message) {
        System.out.println(message);
    }

}
