public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.printResponse("You have no tasks in the list.");
        } else {
            StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                res.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append("\n");
            }
            ui.printResponse(res.toString());
        }
    }
}