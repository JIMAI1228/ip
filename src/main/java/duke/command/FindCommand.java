package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that finds tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying tasks that match the keyword.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Here are the matching tasks in your list:");

        int count = 1;
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(keyword)) {
                ui.showLine(count + ". " + t);
                count++;
            }
        }
    }
}
