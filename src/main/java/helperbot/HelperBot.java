package helperbot;

import helperbot.Ui.Ui;
import helperbot.task.Storage;
import helperbot.task.TaskList;
import helperbot.response.Response;

import java.io.IOException;

/**
 * The main class of the HelperBot program.
 */
public class HelperBot {
    private final Storage storage = new Storage("data/tasks.txt");
    private TaskList tasks;
    private final Response response = new Response();
    private final Ui ui = new Ui();

    /**
     * Constructor for the HelperBot class.
     */
    public HelperBot() {
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (IOException e) {
            ui.printError("Error loading tasks from file: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        String logo = " _    _      _       _            ____        _   \n"
            + "| |  | |    | |     | |          |  _ \\      | |  \n"
            + "| |__| | ___| |_ __ | | ___   _  | |_) | ___ | |_ \n"
            + "|  __  |/ _ \\ | '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
            + "| |  | |  __/ | |_) |   <| |_| | | |_) | (_) | |_ \n"
            + "|_|  |_|\\___|_| .__/|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
            + "              | |          __/ |                 \n"
            + "              |_|         |___/                  \n";
        return logo + "\nHello! I'm helperbot\nWhat would you like to do?\n"
            + ui.printHorizontalLine();
    }

    public String getResponse(String input) {
        try {
            tasks = new TaskList(storage.loadTask());
            return response.getResponse(input, tasks);
        } catch (IOException e) {
            return "Error loading tasks from file: " + e.getMessage();
        }
    }
}