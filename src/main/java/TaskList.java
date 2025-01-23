import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void markTask(int index) {
        taskList.get(index).setDone(true);
    }

    public void unmarkTask(int index) {
        taskList.get(index).setDone(false);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}