package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

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
    private final Storage storage = new Storage();
    private final TaskList tasks = new TaskList(storage.load());
    private final Ui ui = new Ui();

    private boolean isExit = false;

    public void run() {
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

    /**
     * Returns Chloe's response for the given user input (GUI mode).
     *
     * @param input user input
     * @return Chloe's response as a string
     */
    public String getResponse(String input) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream tempOut = new PrintStream(buffer, true, StandardCharsets.UTF_8);

        try {
            System.setOut(tempOut);

            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            this.isExit = command.isExit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        return buffer.toString(StandardCharsets.UTF_8).trim();
    }

    /**
     * Indicates whether the last command requested exit.
     *
     * @return true if should exit
     */
    public boolean isExit() {
        return isExit;
    }
}

