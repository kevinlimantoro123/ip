package helperbot.command;

import java.util.List;

import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand implements Command {
    private final String search;

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
     * @param storage The storage to save the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        boolean found = false;
        List<Task> tasks = taskList.getTaskList();
        StringBuilder res = new StringBuilder("Here are the matching tasks in your list:\n");

        String[] input = search.split(" ");
        if (input.length != 1) {
            return "Please enter only one keyword to search for";
        }
        String keyword = input[0];

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                res.append(i + 1).append(". ").append(task).append("\n");
                found = true;
            }
        }

        if (!found) {
            return "There are no matching tasks in your list.";
        } else {
            res.setLength(res.length() - 1);
            return res.toString();
        }
    }
}
