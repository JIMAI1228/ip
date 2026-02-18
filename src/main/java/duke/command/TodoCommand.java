package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents a command that creates a todo task.
 */
public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a TodoCommand with the given description.
     *
     * @param description the task description
     * @throws ChloeException if the description is empty
     */
    public TodoCommand(String description) throws ChloeException {
        if (description.trim().isEmpty()) {
            throw new ChloeException("Todo description cannot be empty.");
        }

        this.description = description.trim();
    }

    /**
     * Executes the todo command by adding a new to-do task.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new ToDo(description);
        tasks.add(t);
        storage.save(tasks.getTasks());

        ui.showLine(
                "Got it. I've added this task:",
                "  " + t,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}

