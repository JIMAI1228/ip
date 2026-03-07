package chloe.command;

import chloe.ChloeException;
import chloe.storage.Storage;
import chloe.task.Task;
import chloe.task.TaskList;
import chloe.ui.Ui;

import java.util.Arrays;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {

    private final int[] indexes;

    /**
     * Constructs a MarkCommand using the given argument.
     *
     * @param args the task indexes provided by the user
     * @throws ChloeException if the index is invalid
     */
    public MarkCommand(String args) throws ChloeException {
        try {
            this.indexes = Arrays.stream(args.trim().split("\\s+"))
                    .mapToInt(s -> Integer.parseInt(s) - 1)
                    .toArray();
        } catch (Exception e) {
            throw new ChloeException("Invalid task number(s).");
        }
    }

    /**
     * Executes the mark command by marking the specified tasks as done.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     * @throws ChloeException if the index is out of range
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChloeException {

        for (int i : indexes) {
            if (i < 0 || i >= tasks.size()) {
                throw new ChloeException("Invalid task number(s).");
            }
        }

        for (int i : indexes) {
            tasks.get(i).markAsDone();
        }

        storage.save(tasks.getTasks());

        ui.showLine("Nice! I've marked these tasks as done:");

        for (int i : indexes) {
            Task task = tasks.get(i);
            ui.showLine("  [X] " + task.getDescription());
        }
    }
}

