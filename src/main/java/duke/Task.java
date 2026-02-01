package duke;

public class Task {
    private String description;
    private boolean isDone;
    private TaskType type;

    public Task(String description, TaskType type){
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return type.getIcon() + " [" + getStatus() + "] " + description;
    }

    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + "|" + description;
    }
}
