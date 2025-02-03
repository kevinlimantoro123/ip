package helperbot.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description Description of the todo
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
        assert !description.isEmpty() : "Description of todo cannot be empty";
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task
     */
    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString();
    }
}
