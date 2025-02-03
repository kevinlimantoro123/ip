package helperbot.parser;

import helperbot.command.AddCommand;
import helperbot.command.Command;
import helperbot.command.DeleteCommand;
import helperbot.command.ExitCommand;
import helperbot.command.FindCommand;
import helperbot.command.ListCommand;
import helperbot.command.MarkCommand;
import helperbot.command.UnmarkCommand;
import helperbot.task.Deadline;
import helperbot.task.Event;
import helperbot.task.Task;
import helperbot.task.Todo;

/**
 * Represents a parser to parse user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     */
    public static Command parse(String input) {
        assert input != null && !input.isEmpty() : "Input should not be null or empty";
        String[] str = input.split(" ");
        String command = str[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(str[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(str[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(str[1]));
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(input);
        default:
            return new AddCommand(input);
        }
    }

    /**
     * Parses the task and returns the corresponding task.
     *
     * @param task The task to be parsed.
     * @return The corresponding task.
     */
    public static Task parseTask(String task) {
        char type = task.charAt(1);
        boolean isDone = task.charAt(4) == 'X';
        String[] str;
        String description;
        if (isDone) {
            str = task.split(" ", 2);
            description = str[1];
        } else {
            str = task.split(" ", 3);
            description = str[2];
        }

        Task newTask;
        switch (type) {
        case 'T':
            newTask = new Todo(description);
            break;
        case 'D':
            try {
                String[] deadline = description.split("\\(by: ", 2);
                String deadlineDescription = deadline[0].trim();
                String date = deadline[1].substring(0, deadline[1].length() - 1);
                newTask = new Deadline(deadlineDescription, date);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                return null;
            }
            break;
        case 'E':
            try {
                String[] eventParts = description.split("\\(from: ", 2);
                String eventDescription = eventParts[0].trim();
                String[] eventTiming = eventParts[1].split("to: ");
                String from = eventTiming[0].trim();
                String to = eventTiming[1].substring(0, eventTiming[1].length() - 1);
                newTask = new Event(eventDescription, from, to);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                return null;
            }
            break;
        default:
            newTask = null;
            break;
        }
        if (newTask != null && isDone) {
            newTask.setDone(true);
        }
        return newTask;
    }
}
