package helperbot.command;

import java.util.List;

import helperbot.Ui.Ui;
import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand implements Command{
    private String search;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param search The keyword to search for.
     */
    public FindCommand(String search) {
        this.search = search.toLowerCase();
    }

    /**
     * Executes the command to find tasks in the task list.
     *
     * @param taskList The task list to find tasks from.
     * @param ui The user interface to print messages.
     * @param storage The storage to save the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int counter = 1;
        List<Task> tasks = taskList.getTaskList();
        StringBuilder res = new StringBuilder("Here are the matching tasks in your list:\n");

        String[] input = search.split(" ");
        if (input.length > 2) {
            ui.printError("Please enter only one keyword to search for");
            return;
        }
        String keyword = input[1];

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                res.append(counter).append(". ").append(task.toString()).append("\n");
                counter++;
            }
        }

        if (counter == 1) {
            ui.printResponse("There are no matching tasks in your list.");
        } else {
            res.setLength(res.length() - 1);
            ui.printResponse(res.toString());
        }
    }
}
