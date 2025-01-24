package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;

import helperbot.Ui.Ui;

import java.io.IOException;
public class UnmarkCommand implements Command {
    private int index;

    public UnmarkCommand(int index) {
        try {
            this.index = index;
        } catch (NumberFormatException e) {
            this.index = -1;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (index == -1) {
            ui.printError("You did not specify a task number. Please include it!");
            return;
        }
        try {
            Task task = taskList.getTask(index - 1);
            if (!task.isDone()) {
                ui.printError("This task is NOT done!");
                return;
            }
            task.setDone(false);
            storage.saveToFile(taskList.getTaskList());
            task.setDone(false);
            ui.printResponse("Nice! I've unmarked this task:\n" + task.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Please enter a valid task number");
        }
    }
}
