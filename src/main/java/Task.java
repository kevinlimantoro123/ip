public class Task {
    private boolean done;
    private String desc;

    public Task(String desc) {
        this.done = false;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }
}


