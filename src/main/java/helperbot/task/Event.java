package helperbot.task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event
     * @param from Starting time of the event
     * @param to Ending time of the event
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
