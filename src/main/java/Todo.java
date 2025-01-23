public class Todo extends Task {
    public Todo (String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString();
    }
}
