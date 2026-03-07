package chloe.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chloe.ChloeException;
import chloe.parser.DateTimeParser;
import chloe.storage.Storage;
import chloe.task.Event;
import chloe.task.Task;
import chloe.task.TaskList;
import chloe.ui.Ui;

/**
 * Represents a command that creates an event task.
 */
public class EventCommand extends Command {

    private final String desc;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    /**
     * Constructs an EventCommand from the user input.
     *
     * @param input the full user input
     * @throws ChloeException if the input format is invalid
     */
    public EventCommand(String input) throws ChloeException {
        String[] parts = input.substring(6).split(" /from | /to ");

        if (parts.length < 3) {
            throw new ChloeException("Event requires /from ... /to ...");
        }

        this.desc = parts[0].trim();
        if (this.desc.isEmpty()){
            throw new ChloeException("Event description cannot be empty");
        }

        try {
            this.fromDate = DateTimeParser.parseStrict(parts[1]);
            this.toDate = DateTimeParser.parseStrict(parts[2]);
        } catch (DateTimeParseException e) {
            throw new ChloeException("This is an Invalid date form. Use d/M/yyyy HHmm.");
        }

        if (!fromDate.isBefore(toDate)) {
            throw new ChloeException("Event start time must be before end time.");
        }
    }

    /**
     * Executes the event command by adding a new event task.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChloeException {
        Task task = new Event(desc, fromDate, toDate);
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showLine(
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}
