package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;

/**
 * Represents a command that marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int[] indexes;

    /**
     * Constructs an UnmarkCommand using the given argument.
     *
     * @param args the task indexes provided by the user
     * @throws ChloeException if the index is invalid
     */
    public UnmarkCommand(String args) throws ChloeException {
        try {
            this.indexes = Arrays.stream(args.trim().split("\\s+"))
                    .mapToInt(s -> Integer.parseInt(s) - 1)
                    .toArray();
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

        for (int i : indexes) {
            if (i < 0 || i >= tasks.size()) {
                throw new ChloeException("Invalid task number(s).");
            }
        }

        for (int i : indexes) {
            tasks.get(i).markAsNotDone();
        }

        storage.save(tasks.getTasks());

        ui.showLine("OK, I've marked these tasks as not done yet:");

        for (int i : indexes) {
            Task task = tasks.get(i);
            ui.showLine("  [ ] " + task.getDescription());
        }
    }
}
