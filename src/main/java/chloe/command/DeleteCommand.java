package chloe.command;

import chloe.ChloeException;
import chloe.storage.Storage;
import chloe.task.Task;
import chloe.task.TaskList;
import chloe.ui.Ui;

import java.util.Arrays;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int[] indexes;

    /**
     * Constructs a DeleteCommand using the given argument.
     *
     * @param args the task indexes provided by the user
     * @throws ChloeException if the index is invalid
     */
    public DeleteCommand(String args) throws ChloeException {
        try {
            String[] parts = args.trim().split("\\s+");
            indexes = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                indexes[i] = Integer.parseInt(parts[i]) - 1;
            }
        } catch (Exception e) {
            throw new ChloeException("Invalid task number(s).");
        }
    }

    /**
     * Executes the delete command by removing the specified tasks.
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

        Arrays.sort(indexes);

        for (int i = indexes.length - 1; i >= 0; i--) {
            Task removed = tasks.remove(indexes[i]);
            ui.showLine("Noted. I've removed this task:" + removed);
        }

        storage.save(tasks.getTasks());
        ui.showLine("Now you have " + tasks.size() + " tasks.");
    }
}

