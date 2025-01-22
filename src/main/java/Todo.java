public class Todo extends Task {
    public Todo (String desc) {
        super(desc, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[" + type.name().charAt(0) + "]" + super.toString();
    }
}
