package duke;

import javax.swing.event.ListDataEvent;

public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by){
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString(){
        return "D | " + (getStatus().equals("X") ? "1" : "0" ) + getDescription();
    }
}
