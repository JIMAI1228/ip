package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String FILE_PATH = "./data/duke.txt";

    public List<Task> load(){
        List<Task> tasks = new ArrayList<>();

        File file = new File(FILE_PATH);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                Task task;
                switch (type){
                    case "T":
                        task = new ToDo(desc);
                        break;
                    case "D":
                        LocalDateTime date = LocalDateTime.parse(parts[3]);
                        task = new Deadline(desc, date);
                        break;
                    case "E":
                        String[] fromTo = parts[3].split("｜");
                        LocalDateTime from = LocalDateTime.parse(fromTo[0]);
                        LocalDateTime to = LocalDateTime.parse(fromTo[1]);
                        task = new Event(desc, from, to);
                        break;
                    default:
                        continue;
                }

                if(isDone){
                    task.markAsDone();
                }
                tasks.add(task);
            }

        } catch (IOException e) {
            System.out.println("Failed to load data.");
        }
        return tasks;
    }

    public void save(List<Task> tasks){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))){
            for(Task t: tasks){
                bw.write(t.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }
}
