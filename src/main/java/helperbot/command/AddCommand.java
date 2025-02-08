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
     * @param taskList The task list.
     * @param storage The storage handler.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        String[] parts = input.split(" /p ");
        int priority = parts.length > 1 ? Integer.parseInt(parts[1].trim()) : 0;

        String taskPart = parts[0];
        String[] taskDetails = taskPart.split(" ", 2);
        String taskType = taskDetails[0];
        String description = taskDetails.length > 1 ? taskDetails[1] : "";
        Task newTask = createTask(taskType, description, priority);
        if (newTask == null) {
            return "Error: Invalid command format / I don't recognize this command";
        }
        taskList.addTask(newTask);
        storage.saveToFile(taskList.getTaskList());
        return "Got it. I've added this task:\n" + newTask + "\nNow you have "
                + taskList.size() + (taskList.size() > 1 ? " tasks in the list." : " task in the list.");
    }
    private Task createTask(String taskType, String description, int priority) {
        switch (taskType) {
        case "todo":
            return new Todo(description, priority);
        case "deadline":
            return createDeadline(description, priority);
        case "event":
            return createEvent(description, priority);
        default:
            return null;
        }
    }
    private Task createDeadline(String description, int priority) {
        try {
            String[] parts = description.split(" /by ");
            if (parts.length < 2) {
                System.out.println("Invalid deadline format: " + description);
                return null;
            }
            String deadlineDescription = parts[0].trim();
            String date = parts[1].split("\\(Priority: ")[0].trim();
            System.out.println("Parsed deadline: " + deadlineDescription + ", Date: " + date + ", Priority: " + priority);
            return new Deadline(deadlineDescription, date, priority);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing deadline: " + e.getMessage());
            return null;
        }
    }
    private Task createEvent(String description, int priority) {
        try {
            String[] parts = description.split(" /from ");
            if (parts.length < 2) {
                System.out.println("Invalid event format: " + description);
                return null;
            }
            String eventDescription = parts[0].trim();
            String[] times = parts[1].split(" /to ");
            if (times.length < 2) {
                System.out.println("Invalid event time format: " + description);
                return null;
            }
            String from = times[0].trim();
            String to = times[1].split("\\(Priority: ")[0].trim();
            System.out.println("Parsed event: " + eventDescription + ", From: " + from + ", To: " + to + ", Priority: " + priority);
            return new Event(eventDescription, from, to, priority);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing event: " + e.getMessage());
            return null;
        }
    }
}
