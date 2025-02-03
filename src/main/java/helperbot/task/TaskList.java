package helperbot.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for TaskList without parameters
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList with parameters.
     *
     * @param tasks List of tasks
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Tasks should not be null";
        this.taskList = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        taskList.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index Index of the task to be deleted
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < taskList.size() : "Index out of bounds";
        taskList.remove(index);
    }

    /**
     * Gets the current taskList object as a List of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the size of the taskList.
     *
     * @return Size of the taskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets a task from the taskList.
     *
     * @param index Index of the task
     * @return Task object
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Sets the taskList.
     *
     * @param tasks List of tasks
     */
    public void setTask(List<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        this.taskList = tasks;
    }
}
