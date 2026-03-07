package chloe.command;

import chloe.storage.Storage;
import chloe.task.TaskList;
import chloe.ui.Ui;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Indicates that this command terminates the application.
     *
     * @return true since this command exits the program
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command by displaying the goodbye message.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }
}
