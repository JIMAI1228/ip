package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable user command.
 * <p>
 * Each specific command (e.g., TodoCommand, DeleteCommand, ExitCommand)
 * extends this class and implements the {@link #execute(TaskList, Ui, Storage)}
 * method to define its behavior.
 */
public abstract class Command {

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return true if this command causes the program to exit, false otherwise
     */
    public boolean isExit(){
        return false;
    }

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI used to display messages to the user.
     * @param storage The storage used to save task data.
     * @throws ChloeException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChloeException;
}
