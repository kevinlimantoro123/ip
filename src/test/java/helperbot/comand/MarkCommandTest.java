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
import helperbot.Ui.TestUi;
import helperbot.Ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkCommandTest {
    @Test
    public void testMark() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1))));
        }

        TaskList tasks = new TaskList(taskList);
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        String expected = "[T][X] todo 3";

        MarkCommand markCommand = new MarkCommand(3);
        markCommand.execute(tasks, storage);

        assertTrue(tasks.getTask(2).isDone());
        assertEquals(expected, tasks.getTask(2).toString());
    }

    @Test
    public void testMarkOutOfBounds() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1))));
        }

        TaskList tasks = new TaskList(taskList);
        TestUi ui = new TestUi();
        Storage storage = new Storage("data/test.txt");

        String expected = "Please enter a valid task number";

        MarkCommand markCommand = new MarkCommand(10);
        markCommand.execute(tasks, storage);

        assertEquals(expected, ui.getOutput());
        for (Task task : tasks.getTaskList()) {
            assertFalse(task.isDone());
        }
    }

    @Test
    public void testMarkAlreadyDone() throws IOException {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            taskList.add(new Todo(String.format("todo " + (i + 1))));
        }

        TaskList tasks = new TaskList(taskList);
        TestUi ui = new TestUi();
        Storage storage = new Storage("data/test.txt");

        MarkCommand markCommand = new MarkCommand(3);
        markCommand.execute(tasks, storage);

        markCommand.execute(tasks, storage);

        String expected = "This task is ALREADY done!";
        assertEquals(expected, ui.getOutput());
    }
}
