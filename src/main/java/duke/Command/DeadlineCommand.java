package duke.Command;

import duke.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command{
    private final String desc;
    private final LocalDateTime date;

    public DeadlineCommand(String input) throws ChloeException{
        String[] parts = input.substring(9).split(" /by ");

        if(parts.length < 2){
            throw new ChloeException("Deadline requires /by ...");
        }

        desc = parts[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try{
            this.date = LocalDateTime.parse(parts[1], formatter);
        } catch (Exception e) {
            throw new ChloeException("Please use date form in d/M/yyyy HHmm.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task t = new Deadline(desc, date);
        tasks.add(t);
        storage.save(tasks.getTasks());
        ui.showLine("Got it. I've added this task:");
        ui.showLine("  " + t);
        ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
    }
}
