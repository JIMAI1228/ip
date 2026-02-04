package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> load){
        this.tasks = new ArrayList<>(load);
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int index){
        return tasks.get(index);
    }

    public Task remove(int index){
        return tasks.remove(index);
    }

    public void add(Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
