package helperbot.command;

import java.io.IOException;

import helperbot.task.Storage;
import helperbot.task.TaskList;
/**
 * Represents a command to be executed.
 */
public interface Command {
    String execute(TaskList tasks, Storage storage) throws IOException;
}
