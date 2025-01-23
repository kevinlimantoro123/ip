import java.io.BufferedWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HelperBot {
    private static List<Task> taskList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        String logo = " _    _      _       _            ____        _   \n"
                + "| |  | |    | |     | |          |  _ \\      | |  \n"
                + "| |__| | ___| |_ __ | | ___   _  | |_) | ___ | |_ \n"
                + "|  __  |/ _ \\ | '_ \\| |/ / | | | |  _ < / _ \\| __|\n"
                + "| |  | |  __/ | |_) |   <| |_| | | |_) | (_) | |_ \n"
                + "|_|  |_|\\___|_| .__/|_|\\_\\\\__, | |____/ \\___/ \\__|\n"
                + "              | |          __/ |                 \n"
                + "              |_|         |___/                  \n";


        String greeting = "Hello! I'm HelperBot\nWhat would you like to do?";
        System.out.println(logo);

        String bye = "Bye! Hope to see you again soon!";
        System.out.println(greeting);
        printHorizontalLine();

        loadTask();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
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
        if (task.split(" ").length < 2) {
            System.out.println("Please provide a valid task");
            return;
        }
        String[] str = task.split("/");
        Task newTask = getTask(task, str);
        if (newTask == null) {
            return;
        }
        taskList.add(newTask);
        saveToFile();
        System.out.println("Got it. I've added this task:\n" + newTask.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static Task getTask(String task, String[] str) {
        Task newTask = null;
        String temp = task.split(" ", 2)[0];
        switch (temp) {
            case "todo" -> {
                String description = task.split(" ", 2)[1];
                newTask = new Todo(description);
            }
            case "deadline" -> {
                String description = str[0].split(" ", 2)[1];
                if (str[1].split(" ").length == 1) {
                    System.out.println("Please provide a deadline date");
                    return null;
                }
                String date = str[1].split(" ", 2)[1];
                try {
                    newTask = new Deadline(description, date);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
            case "event" -> {
                String[] checker = task.split("/");
                if (checker.length <3) {
                    System.out.println("Please provide 'from' and 'to' timing");
                    return null;
                }
                String description = str[0].split(" ", 2)[1];
                String[] fromArr = str[1].split(" ", 2);
                String[] toArr = str[2].split(" ", 2);
                String from = fromArr[1];
                String to = toArr[1];
                newTask = new Event(description, from, to);
            }
            case "default" -> System.out.println("Please provide a valid task command");
        }
        return newTask;
    }

    public static void printTask() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ".  " + taskList.get(i).toString());
        }
    }

    public static void markTask (int index) {
        if(index > taskList.size() || index < 1) {
            System.out.println("Please provide valid task number");
            return;
        }
        int i = index - 1;
        Task task = taskList.get(i);
        task.setDone(true);
        saveToFile();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void unmarkTask(int index) {
        if(index > taskList.size() || index < 1) {
            System.out.println("Please provide valid task number");
            return;
        }
        int i = index - 1;
        Task task = taskList.get(i);
        task.setDone(false);
        saveToFile();
        System.out.println("OK I've unmarked this task as done:");
        System.out.println(task.toString());
    }

    public static void deleteTask(int index) {
        if(index > taskList.size()) {
            System.out.println("Please provide valid task number");
            return;
        }
        int i = index - 1;
        Task task = taskList.get(i);
        taskList.remove(i);
        saveToFile();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void handleTask(String input) {
        String[] msg = input.split(" ");
        String command = msg[0];

        switch (command) {
            case "list":
                if (taskList.size() == 0) {
                    System.out.println("You have no tasks in the list");
                } else {
                System.out.println("Here are the tasks in your list:");
                printTask();
                }
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
            case "delete":
                if (msg.length < 2) {
                    System.out.println("Please provide task number to delete");
                    break;
                }
                int index3 = Integer.parseInt(msg[1]);
                deleteTask(index3);
                break;
            default:
                addTask(input);
                break;
        }
    }

    private static void saveToFile() {
        File data = new File(FILE_PATH);
        if (!data.exists()) {
            System.out.println("The data file doesn't exist. Please initialise the data file in /data/tasks.txt");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : taskList) {
                    writer.write(task.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
    }

    private static void loadTask() {
        File data = new File(FILE_PATH);
        if (!data.exists()) {
            System.out.println("The data file doesn't exist. Please initialise the data file in /data/tasks.txt");
        } else {
            try (Scanner fileScanner = new Scanner(data)) {
                while (fileScanner.hasNextLine()) {
                    String task = fileScanner.nextLine();
                    Task newTask = parseTask(task);
                    if (newTask == null) {
                        return;
                    }
                    taskList.add(newTask);
                }
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }
    }

    private static Task parseTask(String task) {
        char type = task.charAt(1);
        boolean isDone = task.charAt(4) == 'X';
        String[] str = task.split(" ", 3);
        String description;
        if (isDone) {
            description = str[1];
        } else {
            description = str[2];
        }

        switch(type) {
            case 'T' -> {
                return new Todo(description);
            }
            case 'D' -> {
                String[] deadline = description.split("\\(by: ", 2);
                String deadlineDescription = deadline[0];
                String date = deadline[1].substring(0, deadline[1].length() - 1);
                return new Deadline(deadlineDescription, date);
            }
            case 'E' -> {
                String[] eventParts = description.split("\\(from: ", 2);
                String eventDescription = eventParts[0];
                String[] eventTiming = eventParts[1].split("to: ");
                String from = eventTiming[0];
                String to = eventTiming[1].substring(0, eventTiming[1].length() - 1);
                return new Event(eventDescription, from, to);
            }
            default -> {
                System.out.println("Invalid task type");
                return null;
            }


        }


    }
}
