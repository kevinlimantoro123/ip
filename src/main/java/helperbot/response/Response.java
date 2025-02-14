package helperbot.response;

import java.io.IOException;

import helperbot.command.AddCommand;
import helperbot.command.DeleteCommand;
import helperbot.command.ExitCommand;
import helperbot.command.FindCommand;
import helperbot.command.ListCommand;
import helperbot.command.MarkCommand;
import helperbot.command.UnmarkCommand;
import helperbot.exceptions.HelperBotException;
import helperbot.task.Storage;
import helperbot.task.TaskList;

/**
 * Represents a response to user input.
 */
public class Response {
    private final Storage storage = new Storage("data/tasks.txt");

    /**
     * Returns the response to the user input.
     *
     * @param input The user input.
     * @param taskList The list of tasks.
     * @return The response to the user input.
     */
    public String getResponse(String input, TaskList taskList) {
        String command = input.split(" ", 2)[0].trim();
        try {
            switch (command) {
            case "list":
                return new ListCommand().execute(taskList, storage);
            case "mark":
                int markIndex = Integer.parseInt(input.split(" ", 2)[1].trim());
                return new MarkCommand(markIndex).execute(taskList, storage);
            case "unmark":
                int unmarkIndex = Integer.parseInt(input.split(" ", 2)[1].trim());
                return new UnmarkCommand(unmarkIndex).execute(taskList, storage);
            case "delete":
                int deleteIndex = Integer.parseInt(input.split(" ", 2)[1].trim());
                return new DeleteCommand(deleteIndex).execute(taskList, storage);
            case "find":
                String search = input.split(" ", 2)[1].trim();
                return new FindCommand(search).execute(taskList, storage);
            case "bye":
                return new ExitCommand().execute(taskList, storage);
            default:
                return new AddCommand(input).execute(taskList, storage);
            }
        } catch (HelperBotException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error executing command: " + e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error: Missing argument.";
        }
    }
}
