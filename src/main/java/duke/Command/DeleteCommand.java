package duke.Command;

import duke.*;

public class DeleteCommand extends Command{
    private final int index;

    public DeleteCommand(String arg) throws ChloeException{
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

        Task removed = tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showLine("Noted. I've removed this task:");
        ui.showLine("    " + removed);
        ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
    }
}
