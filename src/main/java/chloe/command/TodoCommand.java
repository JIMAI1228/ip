package chloe.command;

import chloe.ChloeException;
import chloe.storage.Storage;
import chloe.task.Task;
import chloe.task.TaskList;
import chloe.task.ToDo;
import chloe.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChloeException {
        Task task = new ToDo(description);
        tasks.add(task);
        storage.save(tasks.getTasks());

        ui.showLine(
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}

