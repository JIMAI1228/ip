package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command{
    private final int index;

    public MarkCommand(String arg) throws ChloeException{
        try {
            index = Integer.parseInt(arg.trim()) - 1;
        } catch (Exception e) {
            throw new ChloeException("This is an Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChloeException{
        if (index < 0 || index >= tasks.size()) {
            throw new ChloeException("This is an Invalid task number.");
        }

        tasks.get(index).markAsDone();
        Task t = tasks.get(index);
        storage.save(tasks.getTasks());
        ui.showLine("Nice! I've marked this task as done:");
        ui.showLine("  [X] " + t.getDescription());
    }
}
