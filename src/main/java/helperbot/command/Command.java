package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.TaskList;
import helperbot.Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to be executed.
 */
public interface Command {
    String execute(TaskList tasks, Storage storage) throws IOException;
}
