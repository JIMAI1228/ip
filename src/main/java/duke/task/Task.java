package duke.task;

import java.time.LocalDate;

/**
 * Represents a generic task.
 * <p>
 * A Task contains a description, completion status, and task type.
 * Specific task types such as ToDo, Deadline, and Event extend this class.
 */
public class Task {

    /**
     * Description of the task.
     */
    private final String description;

    /**
     * Indicates whether the task is completed.
     */
    private boolean isDone;

    /**
     * Type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    private final TaskType type;

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description Description of the task.
     * @param type Type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status symbol of this task.
     *
     * @return "X" if done, otherwise a blank space
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a user-friendly string representation of this task.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return type.getIcon() + " [" + getStatus() + "] " + description;
    }

    /**
     * Converts this task into a string format suitable for file storage.
     *
     * @return formatted string for saving to disk
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Checks whether this task occurs on the specified date.
     * <p>
     * Default implementation returns false and should be overridden
     * by subclasses Deadline and Event.
     *
     * @param date the date to check
     * @return true if the task occurs on the given date, false otherwise
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }
}