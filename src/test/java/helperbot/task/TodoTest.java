package helperbot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoTest {
    @Test
    public void testTodoInstance() {
        String description = "read book";
        Todo todo = new Todo(description);

        assertEquals(description, todo.getDescription());
        assertFalse(todo.isDone());
        assertNotNull(todo);
    }

    @Test
    public void testTodoToString() {
        String description = "study for exam";
        Todo todo = new Todo(description);

        String expected = "[T][ ] study for exam";
        assertEquals(expected, todo.toString());

        todo.setDone(true);
        String newExpected = "[T][X] study for exam";
        assertEquals(newExpected, todo.toString());
    }
}
