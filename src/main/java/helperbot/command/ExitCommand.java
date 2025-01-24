package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.TaskList;

import helperbot.Ui.Ui;

import java.io.IOException;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveToFile(tasks.getTaskList());
        ui.printGoodbye();
    }
}
