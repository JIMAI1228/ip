package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that creates a deadline task.
 */
public class DeadlineCommand extends Command {

    private final String desc;
    private final LocalDateTime date;

    /**
     * Constructs a DeadlineCommand from the user input.
     *
     * @param input the full user input
     * @throws ChloeException if the input format is invalid
     */
    public DeadlineCommand(String input) throws ChloeException {
        String[] parts = input.substring(9).split(" /by ");

        if (parts.length < 2) {
            throw new ChloeException("Deadline requires /by ...");
        }

        this.desc = parts[0].trim();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            this.date = LocalDateTime.parse(parts[1], formatter);
        } catch (Exception e) {
            throw new ChloeException(
                    "Please use date form in d/M/yyyy HHmm.");
        }
    }

    /**
     * Executes the deadline command by adding a new deadline task.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(desc, date);
        tasks.add(task);
        storage.save(tasks.getTasks());

        ui.showLine(
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}

