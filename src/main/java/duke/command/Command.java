package duke.command;

import duke.ChloeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public boolean isExit(){
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChloeException;
}
