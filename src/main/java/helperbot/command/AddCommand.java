package helperbot.command;

import java.io.IOException;

import helperbot.task.Deadline;
import helperbot.task.Event;
import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;
import helperbot.task.Todo;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand implements Command {
    private final String input;

    /**
     * Constructs an AddCommand with the specified input.
     *
     * @param input The input string.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a task.
     *
     * @param storage The storage handler.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException {
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        String description = parts.length > 1 ? parts[1] : "";

        Task newTask = createTask(taskType, description);
        if (newTask == null) {
            return "Error: Invalid command format/I don't recognize this command";
        }
        taskList.addTask(newTask);
        storage.saveToFile(taskList.getTaskList());
        return "Got it. I've added this task:\n" + newTask + "\nNow you have "
                + taskList.size() + (taskList.size() > 1 ? " tasks in the list." : " task in the list");
    }
    private Task createTask(String taskType, String description) {
        switch (taskType) {
        case "todo":
            return new Todo(description);
        case "deadline":
            return createDeadline(description);
        case "event":
            return createEvent(description);
        default:
            return null;
        }
    }
    private Task createDeadline(String description) {
        String[] parts = description.split(" /by ");
        if (parts.length < 2) {
            return null;
        }
        return new Deadline(parts[0], parts[1]);
    }
    private Task createEvent(String description) {
        String[] parts = description.split(" /from ");
        if (parts.length < 2) {
            return null;
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            return null;
        }
        return new Event(parts[0], times[0], times[1]);
    }
}
