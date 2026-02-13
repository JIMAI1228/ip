package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand using the given argument.
     *
     * @param arg the task index provided by the user
     * @throws ChloeException if the index is invalid
     */
    public UnmarkCommand(String arg) throws ChloeException {
        try {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } catch (Exception e) {
            throw new ChloeException("This is an invalid task number.");
        }
    }

    /**
     * Executes the unmark command by marking the specified task as not done.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     * @throws ChloeException if the index is out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChloeException {

        if (index < 0 || index >= tasks.size()) {
            throw new ChloeException("This is an invalid task number.");
        }

        tasks.get(index).markAsNotDone();
        Task t = tasks.get(index);
        storage.save(tasks.getTasks());

        ui.showLine("OK, I've marked this task as not done yet:");
        ui.showLine("  [ ] " + t.getDescription());
    }
}
