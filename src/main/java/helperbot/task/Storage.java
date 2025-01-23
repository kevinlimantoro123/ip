package helperbot.task;

import helperbot.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTask() throws IOException {
        List<Task> taskList = new ArrayList<>();
        File data = new File(filePath);
        if (!data.exists()) {
            throw new FileNotFoundException("The data file doesn't exist. Please initialise the data file in /data/tasks.txt");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    Task newTask = Parser.parseTask(line);
                    if (newTask != null) {
                        taskList.add(newTask);
                    }
                }
            }
        }
        return taskList;
    }

    public void saveToFile(List<Task> taskList) throws IOException {
        File data = new File(filePath);
        if (!data.exists()) {
            throw new FileNotFoundException("The data file doesn't exist. Please initialise the data file in /data/tasks.txt");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : taskList) {
                    writer.write(task.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
    }
}