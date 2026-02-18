package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand using the given argument.
     *
     * @param arg the task index provided by the user
     * @throws ChloeException if the index is invalid
     */
    public MarkCommand(String arg) throws ChloeException {
        try {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } catch (Exception e) {
            throw new ChloeException("This is an invalid task number.");
        }
    }

    /**
     * Executes the mark command by marking the specified task as done.
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

        tasks.get(index).markAsDone();
        Task t = tasks.get(index);
        storage.save(tasks.getTasks());

        ui.showLine(
                "Nice! I've marked this task as done:",
                "  [X] " + t.getDescription()
        );
    }
}

