package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.TaskList;
import helperbot.Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to be executed.
 */
public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
