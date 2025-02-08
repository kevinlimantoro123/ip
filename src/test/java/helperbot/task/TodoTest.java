package helperbot.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Represents a test class for Todo.
 */
public class TodoTest {
    @Test
    public void testTodoInstance() {
        String description = "read book";
        Todo todo = new Todo(description, 1);

        assertEquals(description, todo.getDescription());
        assertFalse(todo.isDone());
        assertNotNull(todo);
    }

    @Test
    public void testTodoToString() {
        String description = "study for exam";
        Todo todo = new Todo(description, 1);

        String expected = "[T][ ] study for exam (Priority: 1)";
        assertEquals(expected, todo.toString());

        todo.setDone(true);
        String newExpected = "[T][X] study for exam (Priority: 1)";
        assertEquals(newExpected, todo.toString());
    }
}
