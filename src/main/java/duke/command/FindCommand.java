package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
