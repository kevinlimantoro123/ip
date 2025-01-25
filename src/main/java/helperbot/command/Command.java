package helperbot.command;

import java.io.IOException;

import helperbot.Ui.Ui;
import helperbot.task.Storage;
import helperbot.task.TaskList;

/**
 * Represents a command to be executed.
 */
public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
