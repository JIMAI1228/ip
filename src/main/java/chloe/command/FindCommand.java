package chloe.command;

import java.util.List;
import chloe.storage.Storage;
import chloe.task.Task;
import chloe.task.TaskList;
import chloe.ui.Ui;

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

        List<Task> matched = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        for (int i = 0; i < matched.size(); i++) {
            ui.showLine((i + 1) + ". " + matched.get(i));
        }
    }
}
