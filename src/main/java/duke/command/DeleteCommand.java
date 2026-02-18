package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand using the given argument.
     *
     * @param arg the task index provided by the user
     * @throws ChloeException if the index is invalid
     */
    public DeleteCommand(String arg) throws ChloeException {
        try {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } catch (Exception e) {
            throw new ChloeException("This is an invalid task number.");
        }
    }

    /**
     * Executes the delete command by removing the specified task.
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

        Task removed = tasks.remove(index);
        storage.save(tasks.getTasks());

        ui.showLine(
                "Noted. I've removed this task:",
                "    " + removed,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}

