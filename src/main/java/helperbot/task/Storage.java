package helperbot.task;

import helperbot.parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage interface for tasks.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath File path of the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the data file.
     *
     * @return List of tasks
     * @throws IOException If an I/O error occurs
     */
    public List<Task> loadTask() throws IOException {
        List<Task> taskList = new ArrayList<>();
        File data = new File(filePath);
        if (!data.exists()) {
            throw new FileNotFoundException
                    ("The data file doesn't exist. Please initialise the data file in /data/tasks.txt");
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

    /**
     * Saves the tasks to the data file.
     *
     * @param taskList List of tasks
     * @throws IOException If an I/O error occurs
     */
    public void saveToFile(List<Task> taskList) throws IOException {
        File data = new File(filePath);
        if (!data.exists()) {
            throw new FileNotFoundException
                    ("The data file doesn't exist. Please initialise the data file in /data/tasks.txt");
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