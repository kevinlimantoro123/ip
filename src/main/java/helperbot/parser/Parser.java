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

        Task newTask = createTask(taskType, description);
        if (newTask != null && isDone) {
            newTask.setDone(true);
        }
        return newTask;
    }
    private static Task createTask(char type, String description) {
        switch (type) {
        case 'T':
            return new Todo(description);
        case 'D':
            return createDeadline(description);
        case 'E':
            return createEvent(description);
        default:
            return null;
        }
    }
    private static int parseIndex(String[] str) {
        assert str.length > 1 : "Command requires an index";
        return Integer.parseInt(str[1]);
    }
    private static String extractDescription(String task, boolean isDone) {
        String[] string;
        if (isDone) {
            string = task.split(" ", 2);
            return string[1];
        } else {
            string = task.split(" ", 3);
            return string[2];
        }
    }
    private static Task createDeadline(String description) {
        try {
            String[] parts = description.split("\\(by: ", 2);
            String deadlineDescription = parts[0].trim();
            String date = parts[1].substring(0, parts[1].length() - 1);
            return new Deadline(deadlineDescription, date);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    private static Task createEvent(String description) {
        try {
            String[] parts = description.split("\\(from: ", 2);
            String eventDescription = parts[0].trim();
            String[] times = parts[1].split("to: ");
            String from = times[0].trim();
            String to = times[1].substring(0, times[1].length() - 1);
            return new Event(eventDescription, from, to);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
