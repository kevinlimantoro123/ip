package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.setTask(storage.loadTask());
        } catch (IOException e) {
            return "Error loading tasks from file: " + e.getMessage();
        }
        if (taskList.size() == 0) {
            return "You have no tasks in the list";
        } else {
            StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                res.append((i + 1)).append(". ").append(taskList.getTask(i).toString());
                if (i != taskList.size() - 1) {
                    res.append("\n");
                }
            }
            return res.toString();
        }
    }
}