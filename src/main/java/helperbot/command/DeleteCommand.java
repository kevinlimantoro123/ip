package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;

import helperbot.Ui.Ui;

import java.io.IOException;
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
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
            taskList.deleteTask(index - 1);
            storage.saveToFile(taskList.getTaskList());
            ui.printResponse("Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Please enter a valid task number");
        }
    }
}