package duke;

import duke.Command.Command;

public class Chloe {
    private final Storage storage = new Storage();
    private final TaskList tasks = new TaskList(storage.load());
    private final Ui ui = new Ui();

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
