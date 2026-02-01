package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath = "./data/duke.txt";

    public List<Task> load(){
        List<Task> tasks = new ArrayList<>();

        File file = new File(filePath);
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
                        task = new Deadline(desc, parts[3]);
                        break;
                    case "E":
                        String[] fromTo = parts[3].split("-");
                        task = new Event(desc, fromTo[0], fromTo[1]);
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Task t: tasks){
                bw.write(t.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }
}
