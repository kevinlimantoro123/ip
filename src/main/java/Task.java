public class Task {
    private boolean isDone;
    private String desc;
    protected TaskType type;

    public Task(String desc, TaskType type) {
        this.isDone = false;
        this.desc = desc;
        this.type = type;
    }

    public TaskType getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }
}


