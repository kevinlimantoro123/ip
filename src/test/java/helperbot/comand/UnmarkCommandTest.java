package helperbot.comand;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import helperbot.task.Todo;
import helperbot.task.Storage;
import helperbot.task.TaskList;
import helperbot.task.Task;
import helperbot.command.MarkCommand;
import helperbot.command.UnmarkCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnmarkCommandTest {
    @Test
    public void testUnmark() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1))));
        }

        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("data/test.txt");

        String expectedMark = "[T][X] todo 3";
        String expectedUnmark = "[T][ ] todo 3";

        MarkCommand markCommand = new MarkCommand(3);
        markCommand.execute(tasks, storage);
        assertTrue(tasks.getTask(2).isDone());
        assertEquals(expectedMark, tasks.getTask(2).toString());

        UnmarkCommand unmarkCommand = new UnmarkCommand(3);
        unmarkCommand.execute(tasks, storage);

        assertFalse(tasks.getTask(2).isDone());
        assertEquals(expectedUnmark, tasks.getTask(2).toString());

    }

    @Test
    public void testUnmarkOutOfBounds() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1))));
        }


        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("data/test.txt");

        for (Task task : tasks.getTaskList()) {
            task.setDone(true);
        }

        String expected = "Please enter a valid task number";

        UnmarkCommand unmarkCommand = new UnmarkCommand(10);
        String actual = unmarkCommand.execute(tasks, storage);

        assertEquals(expected, actual);
        for (Task task : tasks.getTaskList()) {
            assertTrue(task.isDone());
        }
    }
    @Test
    public void testUnmarkAlreadyDone() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1))));
        }

        TaskList tasks = new TaskList(taskList);
        Storage storage = new Storage("data/test.txt");

        UnmarkCommand unmarkCommand = new UnmarkCommand(3);
        unmarkCommand.execute(tasks, storage);

        String actual = unmarkCommand.execute(tasks, storage);

        String expected = "This task is NOT done!";
        assertEquals(expected, actual);
    }
}
