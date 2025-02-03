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
        assert input != null && !input.isEmpty() : "Input cannot be null or empty";
    }

    /**
     * Executes the command to add a task.
     *
     * @param storage The storage handler.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        String description = parts.length > 1 ? parts[1] : "";

        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ");
            if (deadlineParts.length < 2) {
                return "Error: Invalid deadline format.";
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = description.split(" /from ");
            if (eventParts.length < 2) {
                return "Error: Invalid deadline format.";
            }
            String[] eventTimes = eventParts[1].split(" /to ");
            if (eventTimes.length < 2) {
                return "Error: Invalid deadline format.";
            }
            newTask = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
            break;
        default:
            return "Error: Invalid deadline format.";
        }
        taskList.addTask(newTask);
        storage.saveToFile(taskList.getTaskList());
        return "Got it. I've added this task:\n" + newTask + "\nNow you have "
                + taskList.size() + (taskList.size() > 1 ? " tasks in the list." : " task in the list");
    }
}
