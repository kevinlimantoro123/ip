import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HelperBot {
    private static List<Task> taskList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " _    _      _       _            ____        _   \n"
                + "| |  | |    | |     | |          |  _ \\      | |  \n"
                + "| |__| | ___| |_ __ | | ___   _  | |_) | ___ | |_ \n"
                + "|  __  |/ _ \\ | '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
                + "| |  | |  __/ | |_) |   <| |_| | | |_) | (_) | |_ \n"
                + "|_|  |_|\\___|_| .__/|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
                + "              | |          __/ |                 \n"
                + "              |_|         |___/                  \n";
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
        if (task.trim().isEmpty()) {
            System.out.println("Task cannot be empty");
            return;
        }
        if (task.contains(" ")) {
            System.out.println("Please provide a task description");
            return;
        }

        String[] str = task.split("/");
        Task newTask = getTask(task, str);
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:\n" + newTask.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static Task getTask(String task, String[] str) {
        Task newTask;
        if(str.length == 1) {
            String desc = task.split(" ", 2)[1];
            newTask = new Todo(desc);
        } else if (str.length == 2) {
            String desc = str[0].split(" ", 2)[1];
            String date = str[1].split(" ", 2)[1];
            newTask = new Deadline(desc, date);
        } else {
            String desc = str[0].split(" ", 2)[1];
            String from = str[1].split(" ", 2)[1];
            String to = str[2].split(" ", 2)[1];
            newTask = new Event(desc, from, to);
        }
        return newTask;
    }

    public static void printTask() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ".  " + taskList.get(i).toString());
        }
    }

    public static void markTask (int index) {
        int i = index - 1;
        Task task = taskList.get(i);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void unmarkTask(int index) {
        int i = index - 1;
        Task task = taskList.get(i);
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
                if (msg.length < 2) {
                    System.out.println("Please provide task number to mark");
                    break;
                }
                int index1 = Integer.parseInt(msg[1]);
                markTask(index1);
                break;
            case "unmark":
                if (msg.length < 2) {
                    System.out.println("Please provide task number to unmark");
                    break;
                }
                int index2 = Integer.parseInt(msg[1]);
                unmarkTask(index2);
                break;
            case "todo":
                addTask(input);
                break;
            default:
                System.out.println("This is not a valid command! Please input a valid command");
        }
    }
}
