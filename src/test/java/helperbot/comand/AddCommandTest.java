package helperbot.comand;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import helperbot.command.AddCommand;
import helperbot.task.Deadline;
import helperbot.task.Event;
import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;
import helperbot.task.Todo;

/**
 * Represents a test class for AddCommand.
 */
public class AddCommandTest {
    /**
     * Tests the add command.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void testAddTodo() throws IOException {
        List<Task> taskList = new ArrayList<>();
        Storage storage = new Storage("data/test.txt");

        TaskList tasks = new TaskList(taskList);
        String input = "todo return book /p 1";
        Todo newTodo = new Todo("return book", 1);

        AddCommand addCommand = new AddCommand(input);
        String result = addCommand.execute(tasks, storage);

        String expected = "Got it. I've added this task:\n" + newTodo + "\nNow you have 1 task in the list.";
        assertEquals(expected, result);
    }

    /**
     * Tests the add command with a deadline.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void testAddDeadline() throws IOException {
        List<Task> taskList = new ArrayList<>();
        Storage storage = new Storage("data/test.txt");

        TaskList tasks = new TaskList(taskList);
        String input = "deadline return book /by 14-01-2025 1645 /p 1";
        Deadline newDeadline = new Deadline("return book", "14-01-2025 1645", 1);

        AddCommand addCommand = new AddCommand(input);
        String result = addCommand.execute(tasks, storage);

        String expected = "Got it. I've added this task:\n" + newDeadline + "\nNow you have 1 task in the list.";
        assertEquals(expected, result);
    }

    /**
     * Tests the add command with an event.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void testAddEvent() throws IOException {
        List<Task> taskList = new ArrayList<>();
        Storage storage = new Storage("data/test.txt");

        TaskList tasks = new TaskList(taskList);
        String input = "event meeting /from Sunday /to Monday /p 1";
        Event newEvent = new Event("meeting", "Sunday", "Monday", 1);

        AddCommand addCommand = new AddCommand(input);
        String result = addCommand.execute(tasks, storage);

        String expected = "Got it. I've added this task:\n" + newEvent + "\nNow you have 1 task in the list.";
        assertEquals(expected, result);
    }
}
