import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HelperBot {
    private static List<Task> taskList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String greeting = "Hello! I'm HelperBot\nWhat can I do for you?";
        String bye = "Bye! Hope to see you again soon!";
        System.out.println(greeting);
        printHorizontalLine();


        String input = scanner.nextLine();
        while (!Objects.equals(input, "bye")) {
            printHorizontalLine();
            handleTask(input);
            printHorizontalLine();
            input = scanner.nextLine();
        }
        printHorizontalLine();
        System.out.println(bye);
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void addTask(String task) {
        Task newTask = new Task(task);
        taskList.add(newTask);
        System.out.println("added: " + task);
    }

    public static void printTask() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ".  " + taskList.get(i).toString());
        }
    }

    public static void markTask (int index) {
        Task task = taskList.get(index);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void unmarkTask(int index) {
        Task task = taskList.get(index);
        task.setDone(false);
        System.out.println("OK I've unmarked this task as done:");
        System.out.println(task.toString());
    }

    public static void handleTask(String input) {
        String[] msg = input.split(" ");
        String command = msg[0];

        switch (command) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                printTask();
                break;
            case "mark":
                int index1 = Integer.parseInt(msg[1]);
                markTask(index1);
                break;
            case "unmark":
                int index2 = Integer.parseInt(msg[1]);
                unmarkTask(index2);
                break;
            default:
                addTask(input);
                break;
        }
    }
}
