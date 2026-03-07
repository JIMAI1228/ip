package chloe.command;

import java.time.LocalDateTime;

import chloe.ChloeException;
import chloe.storage.Storage;
import chloe.task.Deadline;
import chloe.task.Task;
import chloe.task.TaskList;
import chloe.parser.DateTimeParser;
import java.time.format.DateTimeParseException;
import chloe.ui.Ui;

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
            throw new ChloeException("Deadline requires stuff and /by ...");
        }

        this.desc = parts[0].trim();
        if (desc.isEmpty()) {
            throw new ChloeException("Deadline description cannot be empty.");
        }

        try {
            this.date = DateTimeParser.parseStrict(parts[1]);
        } catch (DateTimeParseException e) {
            throw new ChloeException("This is an Invalid date or in an Invalid date. Use d/M/yyyy HHmm.");
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChloeException {
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

