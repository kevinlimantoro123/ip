package helperbot.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private final int priority;
    /**
     * Constructor for Todo
     *
     * @param description Description of the todo
     */
    public Todo(String description, int priority) {
        super(description, TaskType.TODO, priority);
        this.priority = priority;
        assert !description.isEmpty() : "Description of todo cannot be empty";
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " (Priority: " + priority + ")";
    }
}
