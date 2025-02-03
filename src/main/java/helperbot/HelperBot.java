package helperbot;

import java.io.IOException;

import helperbot.response.Response;
import helperbot.task.Storage;
import helperbot.task.TaskList;

/**
 * The main class of the HelperBot program.
 */
public class HelperBot {
    private final Storage storage = new Storage("data/tasks.txt");
    private TaskList tasks;
    private final Response response = new Response();

    /**
     * Constructor for the HelperBot class.
     */
    public HelperBot() {
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        String logo = """
             _    _      _       _            ____        _  \s
            | |  | |    | |     | |          |  _ \\      | | \s
            | |__| | ___| |_ __ | | ___   _  | |_) | ___ | |_\s
            |  __  |/ _ \\ | '_ \\| |/ / | | | |  _ < / _ \\| __|
            | |  | |  __/ | |_) |   <| |_| | | |_) | (_) | |_\s
            |_|  |_|\\___|_| .__/|_|\\_\\\\__, | |____/ \\___/ \\__|
                          | |          __/ |                \s
                          |_|         |___/                 \s
            """;
        return logo + "\nHello! I'm helperbot\nWhat would you like to do?\n"
            + printHorizontalLine();
    }

    /**
     * Gets the response based on the user input.
     *
     * @param input the user input
     * @return the response from helperBot
     */
    public String getResponse(String input) {
        try {
            tasks = new TaskList(storage.loadTask());
            return response.getResponse(input, tasks);
        } catch (IOException e) {
            return "Error loading tasks from file: " + e.getMessage();
        }
    }

    /**
     * Prints a horizontal line.
     */
    public String printHorizontalLine() {
        return "-".repeat(40);
    }
}
