package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task with a start and end date-time.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description and time range.
     *
     * @param description the task description
     * @param from the start date-time
     * @param to the end date-time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return super.toString()
                + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (getStatus().equals("X") ? "1" : "0")
                + " | " + getDescription()
                + " | " + from + "｜" + to;
    }

    /**
     * Checks whether the event occurs on the given date.
     *
     * @param date the date to check
     * @return true if the event occurs on the given date, false otherwise
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return from.toLocalDate().equals(date)
                || to.toLocalDate().equals(date)
                || (from.toLocalDate().isBefore(date)
                && to.toLocalDate().isAfter(date));
    }
}
