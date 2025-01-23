package helperbot.command;

import java.io.IOException;
import helperbot.task.*;
import helperbot.Ui.Ui;

public class AddCommand implements Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

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
                ui.printError("I don't recognize that helperbot.command. Please use todo, deadline or event!");
                return;
            }
        }

        tasks.addTask(newTask);
        storage.saveToFile(tasks.getTaskList());
        ui.printResponse("Got it. I've added this helperbot.task:\n" + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}