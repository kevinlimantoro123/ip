package helperbot.task;

public class Task {
    private boolean isDone;
    private String description;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.isDone = false;
        this.description = description.trim();
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

