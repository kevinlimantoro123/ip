package helperbot.parser;

import helperbot.task.Deadline;
import helperbot.task.Event;
import helperbot.task.Task;
import helperbot.task.Todo;

/**
 * Represents a parser to parse user input.
 */
public class Parser {
    /**
     * Parses the task and returns the corresponding task.
     *
     * @param task The task to be parsed.
     * @return The corresponding task.
     */
    public static Task parseTask(String task) {
        char taskType = task.charAt(1);
        boolean isDone = task.charAt(4) == 'X';
        String description = extractDescription(task, isDone);
        int priority = extractPriority(task);

        Task newTask = createTask(taskType, description, priority);
        if (newTask != null && isDone) {
            newTask.setDone(true);
        }
        return newTask;
    }
    private static int extractPriority(String task) {
        String[] parts = task.split("Priority: ");
        return parts.length > 1 ? Integer.parseInt(parts[1].replace(")", "")) : 0;
    }
    private static Task createTask(char type, String description, int priority) {
        switch (type) {
        case 'T':
            return new Todo(description, priority);
        case 'D':
            return createDeadline(description, priority);
        case 'E':
            return createEvent(description, priority);
        default:
            return null;
        }
    }
    private static String extractDescription(String task, boolean isDone) {
        String[] parts = task.split("\\(Priority: ");
        String descriptionPart = parts[0];
        System.out.println(descriptionPart);
        if (isDone) {
            return descriptionPart.split(" ", 2)[1];
        } else {
            return descriptionPart.split(" ", 3)[2];
        }
    }
    private static Task createDeadline(String description, int priority) {
        try {
            String[] parts = description.split(" \\(by: ");
            if (parts.length < 2) {
                System.out.println("Invalid deadline format: " + description);
                return null;
            }
            String deadlineDescription = parts[0].trim();
            String date = parts[1].split("\\)")[0].trim();
            return new Deadline(deadlineDescription, date, priority);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    private static Task createEvent(String description, int priority) {
        try {
            String[] parts = description.split("\\(from: ", 2);
            if (parts.length < 2) {
                return null;
            }
            String eventDescription = parts[0].trim();
            String[] times = parts[1].split("to: ");
            String from = times[0].trim();
            String to = times[1].split("\\)")[0].trim();
            return new Event(eventDescription, from, to, priority);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
