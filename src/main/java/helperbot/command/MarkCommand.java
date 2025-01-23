package helperbot.command;

import helperbot.task.*;
import helperbot.Ui.Ui;

import java.io.IOException;
public class MarkCommand implements Command {
    private int index;

    public MarkCommand(int index) {
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
            if (task.isDone()) {
                ui.printError("This task is ALREADY done!");
                return;
            }
            task.setDone(true);
            storage.saveToFile(taskList.getTaskList());
            ui.printResponse("Nice! I've marked this task:\n" + task.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Please enter a valid task number");
        }
    }
}
