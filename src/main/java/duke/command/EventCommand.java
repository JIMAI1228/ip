package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            this.fromDate = LocalDateTime.parse(parts[1], formatter);
            this.toDate = LocalDateTime.parse(parts[2], formatter);
        } catch (Exception e) {
            throw new ChloeException("Please use date form in d/M/yyyy HHmm.");
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
