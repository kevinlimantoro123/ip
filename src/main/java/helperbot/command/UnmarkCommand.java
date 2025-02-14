package helperbot.command;

import java.io.IOException;

import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;

/**
 * Represents a command to unmark a task that has been marked done.
 */
public class UnmarkCommand implements Command {
    private int index;

    /**
     * Constructor for UnmarkCommand.
     * Sets index to -2 if given an invalid index
     *
     * @param index index of task to be unmarked
     */
    public UnmarkCommand(int index) {
        try {
            this.index = index - 1;
        } catch (NumberFormatException e) {
            this.index = -2;
        }
    }

    /**
     * Unmarks a task if task has not been marked done.
     * Prints an error message if given an invalid index.
     *
     * @param taskList list of tasks
     * @param storage storage
     * @throws IOException if there is an error saving to file
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException {
        if (index == -2) {
            return "You did not specify a task number. Please include it!";
        }
        try {
            Task task = taskList.getTask(index);
            if (!task.isDone()) {
                return "This task is NOT done!";
            }
            task.setDone(false);
            storage.saveToFile(taskList.getTaskList());
            return "Nice! I've unmarked this task:\n" + task;
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid task number";
        }
    }
}
