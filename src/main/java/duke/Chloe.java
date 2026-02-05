package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main entry point of the Chloe chatbot application.
 * <p>
 * Chloe coordinates interactions between the user interface, task list,
 * storage system, and command parser.
 */
public class Chloe {
    /**
     * Handles loading and saving tasks from/to disk.
     */
    private final Storage storage = new Storage();

    /**
     * Stores the list of tasks currently in memory.
     */
    private final TaskList tasks = new TaskList(storage.load());

    /**
     * Handles all user interactions：input or output.
     */
    private final Ui ui = new Ui();

    /**
     * Starts the chatbot main loop.
     * <p>
     * Reads user commands, parses them into Command objects,
     * executes the commands, and exits when an ExitCommand is issued.
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }

        }
    }
}
