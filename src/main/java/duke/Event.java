package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to){
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String  toFileString(){
        return "E | " + (getStatus().equals("X") ? "1" : "0") + " | " + getDescription() + " | " + from + "｜" + to;
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return from.toLocalDate().equals(date)
                || to.toLocalDate().equals(date)
                || (from.toLocalDate().isBefore(date)
                && to.toLocalDate().isAfter(date));
    }

}
