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
            isExit = processUserInput();
        }
    }

    /**
     * Returns Chloe's response for the given user input (GUI mode).
     *
     * @param input user input
     * @return Chloe's response as a string
     */
    public String getResponse(String input) {
        return captureOutput(() -> {
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    /**
     * Indicates whether the last command requested exit.
     *
     * @return true if should exit
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Reads a single line of user input, parses it into a {@link Command},
     * executes the command, and determines whether the application should exit.
     *
     * <p>
     * This method encapsulates one iteration of the main command-processing loop.
     * It assumes that user input and parsed commands are non-null, which is
     * documented using assertions.
     * </p>
     *
     * @return {@code true} if the executed command indicates the application should exit;
     *         {@code false} otherwise, including when an exception occurs.
     */
    private boolean processUserInput() {
        try {
            String input = ui.readCommand();
            assert input != null : "User input should not be null";

            Command command = Parser.parse(input);
            assert command != null : "Parser should return a Command";

            command.execute(tasks, ui, storage);
            return command.isExit();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    /**
     * Executes the given action while temporarily redirecting {@code System.out},
     * captures all printed output, and returns it as a string.
     *
     * <p>
     * This method is used to adapt console-based output into GUI-compatible
     * responses by intercepting printed messages during command execution.
     * </p>
     *
     * <p>
     * The original {@code System.out} stream is restored after execution,
     * even if the action throws an exception.
     * </p>
     *
     * @param action the operation whose output should be captured
     * @return the captured output as a trimmed string
     */
    private String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream tempOut = new PrintStream(buffer, true, StandardCharsets.UTF_8);

        System.setOut(tempOut);

        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }

        return buffer.toString(StandardCharsets.UTF_8).trim();
    }

}

