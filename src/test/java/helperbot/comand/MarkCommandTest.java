package helperbot.comand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import helperbot.command.MarkCommand;
import helperbot.task.Storage;
import helperbot.task.Task;
import helperbot.task.TaskList;
import helperbot.task.Todo;

/**
 * Represents a test class for MarkCommand.
 */
public class MarkCommandTest {
    /**
     * Tests the mark command.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void testMarkTodo() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1)), 1));
        }

        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("data/test.txt");

        String expected = "[T][X] todo 3 (Priority: 1)";

        MarkCommand markCommand = new MarkCommand(3);
        markCommand.execute(tasks, storage);

        assertTrue(tasks.getTask(2).isDone());
        assertEquals(expected, tasks.getTask(2).toString());
    }

    /**
     * Tests the mark command with an out-of-bounds task number.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void testMarkOutOfBounds() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1)), 1));
        }

        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("data/test.txt");

        String expected = "Please enter a valid task number";

        MarkCommand markCommand = new MarkCommand(10);
        String actual = markCommand.execute(tasks, storage);

        assertEquals(expected, actual);
        for (Task task : tasks.getTaskList()) {
            assertFalse(task.isDone());
        }
    }

    /**
     * Tests the mark command with an already done task.
     *
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void testMarkAlreadyDone() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1)), 1));
        }

        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("data/test.txt");

        MarkCommand markCommand = new MarkCommand(3);
        markCommand.execute(tasks, storage);

        String actual = markCommand.execute(tasks, storage);

        String expected = "This task is ALREADY done!";
        assertEquals(expected, actual);
    }
}
