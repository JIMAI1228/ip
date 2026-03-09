package chloe.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import chloe.task.Deadline;
import chloe.task.Event;
import chloe.task.Task;
import chloe.task.ToDo;

/**
 * Handles loading and saving of tasks to a local file.
 */
public class Storage {

    private final String filePath = "./data/chloe.txt";

    /**
     * Loads tasks from the storage file.
     *
     * @return a list of tasks loaded from file
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                Task task;
                switch (type) {
                case "T":
                    task = new ToDo(desc);
                    break;
                case "D":
                    LocalDateTime date = LocalDateTime.parse(parts[3]);
                    task = new Deadline(desc, date);
                    break;
                case "E":
                    String[] fromTo = parts[3].split(" ~ ");
                    LocalDateTime from = LocalDateTime.parse(fromTo[0]);
                    LocalDateTime to = LocalDateTime.parse(fromTo[1]);
                    task = new Event(desc, from, to);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }

        } catch (IOException e) {
            System.out.println("Failed to load data.");
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks the list of tasks to be saved
     */
    public void save(List<Task> tasks) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                bw.write(t.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }
}
