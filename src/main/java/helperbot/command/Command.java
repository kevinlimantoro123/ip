package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.TaskList;
import helperbot.Ui.Ui;

import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
