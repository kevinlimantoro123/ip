package helperbot.command;

import java.io.IOException;

import helperbot.task.Storage;
import helperbot.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        storage.saveToFile(tasks.getTaskList());
        return "Bye! Hope to see you again soon!";
    }
}
