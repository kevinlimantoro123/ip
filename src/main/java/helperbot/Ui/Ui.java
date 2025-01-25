package helperbot.Ui;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " _    _      _       _            ____        _   \n"
                + "| |  | |    | |     | |          |  _ \\      | |  \n"
                + "| |__| | ___| |_ __ | | ___   _  | |_) | ___ | |_ \n"
                + "|  __  |/ _ \\ | '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
                + "| |  | |  __/ | |_) |   <| |_| | | |_) | (_) | |_ \n"
                + "|_|  |_|\\___|_| .__/|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
                + "              | |          __/ |                 \n"
                + "              |_|         |___/                  \n";
        System.out.println(logo);
        System.out.println("Hello! I'm helperbot.HelperBot\nWhat would you like to do?");
        printHorizontalLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line.
     */
    public void printHorizontalLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.println();
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

    /**
     * Prints a response message.
     *
     * @param message Response message to be printed
     */
    public void printResponse(String message) {
        System.out.println(message);
    }
}