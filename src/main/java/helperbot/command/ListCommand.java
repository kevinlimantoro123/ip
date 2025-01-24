package helperbot.command;

import helperbot.task.Storage;
import helperbot.task.TaskList;

import helperbot.Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.setTask(storage.loadTask());
        } catch (IOException e) {
            ui.printError("Error loading tasks from file: " + e.getMessage());
            return;
        }
        if (taskList.size() == 0) {
            ui.printResponse("You have no tasks in the list");
        } else {
            StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                res.append((i + 1)).append(". ").append(taskList.getTask(i).toString());
                if (i != taskList.size() - 1) {
                    res.append("\n");
                }
            }
            ui.printResponse(res.toString());
        }
    }
}