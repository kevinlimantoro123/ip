package helperbot;

import java.io.IOException;

import helperbot.Ui.Ui;
import helperbot.command.Command;
import helperbot.command.ExitCommand;
import helperbot.parser.Parser;
import helperbot.task.Storage;
import helperbot.task.TaskList;


/**
 * The main class of the HelperBot program.
 */
public class HelperBot {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the HelperBot class.
     * @param filePath The file path to the file where the tasks are stored.
     */
    public HelperBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (IOException e) {
            ui.printError("Error loading tasks from file: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the HelperBot program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.printHorizontalLine();
            Command command = Parser.parse(fullCommand);
            try {
                command.execute(tasks, ui, storage);
                isExit = command instanceof ExitCommand;
            } catch (IOException e) {
                ui.printError("Error executing helperbot.command: " + e.getMessage());
            }
            ui.printHorizontalLine();
        }
    }

    /**
     * The main method of the HelperBot program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new HelperBot("data/tasks.txt").run();
    }
}
