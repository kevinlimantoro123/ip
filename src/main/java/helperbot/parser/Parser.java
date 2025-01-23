package helperbot.parser;

import helperbot.command.*;
import helperbot.task.*;

public class Parser {
    public static Command parse(String input) {
        String[] str = input.split(" ");
        String command = str[0];

        switch (command) {
            case "list" -> {
                return new ListCommand();
            }
            case "mark" -> {
                return new MarkCommand(Integer.parseInt(str[1]));
            }
            case "unmark" -> {
                return new UnmarkCommand(Integer.parseInt(str[1]));
            }
            case "delete" -> {
                return new DeleteCommand(Integer.parseInt(str[1]));
            }
            case "bye" -> {
                return new ExitCommand();
            }
            default -> {
                return new AddCommand(input);
            }
        }
    }

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
            case 'T' -> {
                newTask = new Todo(description);
            }
            case 'D' -> {
                try {
                    String[] deadline = description.split("\\(by: ", 2);
                    String deadlineDescription = deadline[0].trim();
                    String date = deadline[1].substring(0, deadline[1].length() - 1);
                    newTask = new Deadline(deadlineDescription, date);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            case 'E' -> {
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
            }
            default -> {
                newTask = null;
            }
        }
        if (newTask != null && isDone) {
            newTask.setDone(true);
        }
        return newTask;
    }
}

