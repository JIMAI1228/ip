package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by){
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString(){
        return "D | " + (getStatus().equals("X") ? "1" : "0" ) + " | " + getDescription() + " | " + by;
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

}
