public class Deadline extends Task {
    protected String date;

    public Deadline(String desc, String date) {
        super(desc, TaskType.DEADLINE);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString() + "(by: " + date + ")";
    }
}
