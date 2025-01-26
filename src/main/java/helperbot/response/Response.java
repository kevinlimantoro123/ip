package helperbot.response;

import java.io.IOException;
import helperbot.command.AddCommand;
import helperbot.command.DeleteCommand;
import helperbot.command.ExitCommand;
import helperbot.command.MarkCommand;
import helperbot.command.UnmarkCommand;
import helperbot.command.FindCommand;
import helperbot.command.ListCommand;
import helperbot.task.TaskList;
import helperbot.task.Storage;
public class Response {
    private final Storage storage = new Storage("data/tasks.txt");

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
                String searchTerm = input.split(" ", 2)[1].trim();
                return new FindCommand(searchTerm).execute(taskList, storage);
            case "bye":
                return new ExitCommand().execute(taskList, storage);
            default:
                return new AddCommand(input).execute(taskList, storage);
            }
        } catch (IOException e) {
            return "Error executing command: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Error: Invalid number format.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error: Missing argument.";
        }
    }
}
