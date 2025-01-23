public class Task {
    private boolean isDone;
    private String description;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.isDone = false;
        this.description = description;
        this.type = type;
    }

    public TaskType getType() {
        return type;
    }

    public String getDesc() {
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


