import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("Hello! I'm HelperBot\nWhat would you like to do?");
        printHorizontalLine();
    }

    public void printGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printResponse(String message) {
        System.out.println(message);
    }
}