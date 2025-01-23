package helperbot.command;

import helperbot.task.*;
import helperbot.Ui.Ui;

import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
