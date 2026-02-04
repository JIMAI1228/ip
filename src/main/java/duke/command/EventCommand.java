package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command{
    private final String desc;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    public EventCommand(String input) throws ChloeException{
        String[] parts = input.substring(6).split(" /from | /to ");

        if(parts.length < 2){
            throw new ChloeException("Event requires /from ... /to ...");
        }

        this.desc = parts[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            this.fromDate = LocalDateTime.parse(parts[1], formatter);
            this.toDate = LocalDateTime.parse(parts[2], formatter);
        } catch (Exception e) {
            throw new ChloeException("Please use date form in d/M/yyyy HHmm.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task t = new Event(desc, fromDate, toDate);
        tasks.add(t);
        storage.save(tasks.getTasks());
        ui.showLine("Got it. I've added this task:");
        ui.showLine("  " + t);
        ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
    }
}
