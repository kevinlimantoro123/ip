import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HelperBot {
    private static List<String> taskList = new ArrayList<>();
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

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!Objects.equals(input, "bye")) {
            printHorizontalLine();
            if (Objects.equals(input, "list")) {
                printTask();
                printHorizontalLine();
                input = scanner.nextLine();
            } else {
                addTask(input);
                printHorizontalLine();
                input = scanner.nextLine();
            }
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

        public static void addTask (String task) {
            taskList.add(task);
            System.out.println("added: " + task);
        }

        public static void printTask () {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }

        }
}
