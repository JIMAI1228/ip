package duke.Command;

import duke.*;

public class TodoCommand extends Command{
    private final String description;

    public TodoCommand(String description) throws ChloeException{
        if (description.trim().isEmpty()){
            throw new ChloeException("Todo description cannot be empty.");
        }

        this.description = description.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task t = new ToDo(description);
        tasks.add(t);
        storage.save(tasks.getTasks());
        ui.showLine("Got it. I've added this task:");
        ui.showLine("  " + t);
        ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
    }
}
