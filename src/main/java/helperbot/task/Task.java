package helperbot.task;

/**
 * Represents a task.
 */
public class Task {
    protected TaskType type;
    private boolean isDone;
    private String description;
    private int priority;

    /**
     * Constructor for Task with a priority
     *
     * @param description Description of the task
     * @param type Type of the task
     */
    public Task(String description, TaskType type, int priority) {
        this.isDone = false;
        this.description = description.trim();
        this.type = type;
        this.priority = priority;
    }

    /**
     * Constructor for Task with no priority
     *
     * @param description Description of the task
     * @param type Type of the task
     */
    public Task(String description, TaskType type) {
        this.isDone = false;
        this.description = description.trim();
        this.type = type;
        this.priority = 0;
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
     * Sets the priority of the task.
     *
     * @param priority The priority to set.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the priority of the task.
     *
     * @return The task priority.
     */
    public int getPriority() {
        return priority;
    }
    /**
     * Returns the string representation of the task
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        String res;
        if (isDone) {
            res = "[X] " + description;
        } else {
            res = "[ ] " + description;
        }
        return res;
    }
}


