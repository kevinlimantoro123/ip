package helperbot.task;

/**
 * Represents a task.
 */
public class Task {
    private boolean isDone;
    private String description;
    protected TaskType type;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task
     * @param type Type of the task
     */
    public Task(String description, TaskType type) {
        this.isDone = false;
        this.description = description.trim();
        this.type = type;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return Boolean indicating whether the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the task as done or not done.
     *
     * @param isDone Boolean indicating whether the task is done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}


