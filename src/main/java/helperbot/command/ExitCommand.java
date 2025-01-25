package helperbot.command;

import java.io.IOException;

import helperbot.Ui.Ui;
import helperbot.task.Storage;
import helperbot.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveToFile(tasks.getTaskList());
        ui.printGoodbye();
    }
}
