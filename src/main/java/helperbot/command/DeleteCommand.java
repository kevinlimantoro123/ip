package helperbot.command;

import java.io.IOException;

import helperbot.Ui.Ui;
import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        try {
            this.index = index;
        } catch (NumberFormatException e) {
            this.index = -1;
        }
    }

    /**
     * Executes the command to delete a task.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws IOException If an I/O error occurs.
     */
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
            ui.printResponse("Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                    + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Please enter a valid task number");
        }
    }
}
