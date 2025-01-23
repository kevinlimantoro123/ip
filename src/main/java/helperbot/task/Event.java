package helperbot.task;

import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return "E | " + (isDone() ? "X" : " ") + " | " + getDesc() + " | " + from + " | " + to;
    }
}
