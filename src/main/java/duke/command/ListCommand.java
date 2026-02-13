package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showLine("    " + (i + 1) + ". "
                    + tasks.get(i).toString());
        }
    }
}

