import java.io.IOException;
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task task = taskList.getTask(index - 1);
        taskList.deleteTask(index - 1);
        storage.saveToFile(taskList.getTaskList());
        ui.printResponse("Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}