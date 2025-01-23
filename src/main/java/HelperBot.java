import java.io.IOException;

public class HelperBot {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
                ui.printError("Error executing command: " + e.getMessage());
            }
            ui.printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        new HelperBot("data/tasks.txt").run();
    }
}