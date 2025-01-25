package helperbot.command;

import java.io.IOException;

import helperbot.Ui.Ui;
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
    private String input;

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
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] parts = input.split(" ", 2);
        String taskType = parts[0];
        String description = parts.length > 1 ? parts[1] : "";

        Task newTask;
        switch (taskType) {
        case "todo" -> newTask = new Todo(description);
        case "deadline" -> {
            String[] deadlineParts = description.split(" /by ");
            if (deadlineParts.length < 2) {
                ui.printError("Error: Invalid deadline format.");
                return;
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
        }
        case "event" -> {
            String[] eventParts = description.split(" /from ");
            if (eventParts.length < 2) {
                ui.printError("Error: Invalid event format.");
                return;
            }
            String[] eventTimes = eventParts[1].split(" /to ");
            if (eventTimes.length < 2) {
                ui.printError("Error: Invalid event timing format.");
                return;
            }
            newTask = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
        }
        default -> {
            ui.printError("I don't recognize that command. Please use todo, deadline or event!");
            return;
        }
        }
        tasks.addTask(newTask);
        storage.saveToFile(tasks.getTaskList());
        ui.printResponse("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have "
                + tasks.size() + " tasks in the list.");
    }
}
