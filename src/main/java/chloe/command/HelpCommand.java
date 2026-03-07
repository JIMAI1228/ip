package chloe.command;

import chloe.storage.Storage;
import chloe.task.TaskList;
import chloe.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine(
                "I can show you some useful and available commands: ",
                "   list",
                "     - Lists all tasks.",
                "",
                "   todo Description",
                "     - Add a todo task",
                "     - e.g., todo read",
                "",
                "  deadline DESCRIPTION /by dd/MM/yyyy HHmm",
                "    - Adds a deadline task",
                "    - e.g., deadline homework /by 20/02/2026 2359",
                "",
                "  event DESCRIPTION /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm",
                "    - Adds an event task",
                "    - e.g., event exam /from 01/06/2026 1600 /to 01/06/2026 1800",
                "",
                "  find KEYWORD",
                "    - Finds tasks containing the keyword",
                "    - e.g., find homework",
                "",
                "  mark INDEX [INDEXES]",
                "    - Marks one or more tasks as done",
                "    - e.g., mark 2 3",
                "",
                "  unmark INDEX [INDEXES]",
                "    - Marks one or more tasks as not done",
                "    - e.g., unmark 2 3",
                "",
                "  delete INDEX [INDEXES]",
                "    - Deletes one or more tasks",
                "    - e.g., delete 1 4 7",
                "",
                "  bye",
                "    - Exits the application"
        );
    }
}
