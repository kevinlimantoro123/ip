public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString() + "(by: " + date + ")";
    }
}
