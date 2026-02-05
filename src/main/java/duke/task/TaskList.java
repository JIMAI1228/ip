package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * <p>
 * This class provides basic operations such as adding, removing,
 * retrieving tasks, and querying the size of the task list.
 */
public class TaskList {

    /**
     * Internal list that stores all tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an existing list of tasks.
     *
     * @param load list of tasks loaded from storage
     */
    public TaskList(List<Task> load){
        this.tasks = new ArrayList<>(load);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index index of the task to retrieve
     * @return the task at the given index
     */
    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index index of the task to remove
     * @return the removed task
     */
    public Task remove(int index){
        return tasks.remove(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task){
        tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     * <p>
     * This is mainly used by Storage for saving tasks to disk.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
