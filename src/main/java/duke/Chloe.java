package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Chloe {
    private final Storage storage = new Storage();
    private final TaskList tasks = new TaskList(storage.load());
    private final Ui ui = new Ui();

    public void run(){
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();

                if (input.equals("bye")) {
                    ui.sayBye();
                    break;
                }

                if (input.equals("list")) {
                    ui.showLine("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        ui.showLine("    " + (i + 1) + ". " + tasks.get(i).toString());
                    }
                    continue;
                }

                if(input.equals("mark")){
                    throw new ChloeException("The description of a mask cannot be empty.");
                }

                if(input.equals("delete")){
                    throw new ChloeException("The description of a delete cannot be empty.");
                }

                if(input.startsWith("delete ")){
                    int index = Integer.parseInt(input.substring(7)) - 1;

                    if(index < 0 || index >= tasks.size()){
                        throw new ChloeException("This task number does not exist.");
                    }

                    Task removed = tasks.remove(index);
                    storage.save(tasks.getTasks());
                    ui.showLine("Noted. I've removed this task:");
                    ui.showLine("    " + removed);
                    ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(index).markAsDone();
                    Task t = tasks.get(index);
                    storage.save(tasks.getTasks());
                    ui.showLine("Nice! I've marked this task as done:");
                    ui.showLine("  [X] " + t.getDescription());
                    continue;
                }

                if(input.equals("unmark")){
                    throw new ChloeException("The description of an unmask cannot be empty.");
                }

                if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    tasks.get(index).markAsNotDone();
                    Task t = tasks.get(index);
                    storage.save(tasks.getTasks());
                    ui.showLine("OK, I've marked this task as not done yet:");
                    ui.showLine("  [ ] " + t.getDescription());
                    continue;
                }

                if(input.equals("todo")){
                    throw new ChloeException("The description of a todo cannot be empty.");
                }

                if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    Task t = new ToDo(desc);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showLine("Got it. I've added this task:");
                    ui.showLine("  " + t);
                    ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if(input.equals("deadline")){
                    throw new ChloeException("The description of a deadline cannot be empty.");
                }

                if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime date = LocalDateTime.parse(parts[1], formatter);
                    Task t = new Deadline(parts[0], date);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showLine("Got it. I've added this task:");
                    ui.showLine("  " + t);
                    ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if(input.equals("event")){
                    throw new ChloeException("The description of an event cannot be empty.");
                }

                if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime from = LocalDateTime.parse(parts[1], formatter);
                    LocalDateTime to = LocalDateTime.parse(parts[2], formatter);
                    Task t = new Event(parts[0], from, to);
                    tasks.add(t);
                    storage.save(tasks.getTasks());
                    ui.showLine("Got it. I've added this task:");
                    ui.showLine("  " + t);
                    ui.showLine("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if (input.startsWith("on ")) {
                    LocalDate date;
                    try {
                        DateTimeFormatter inFormatter =
                                DateTimeFormatter.ofPattern("d/M/yyyy");
                        date = LocalDate.parse(input.substring(3).trim(), inFormatter);
                    } catch (Exception e) {
                        ui.showLine("Please use date format: d/M/yyyy (e.g., 2/12/2019)");
                        continue;
                    }

                    DateTimeFormatter outFormatter =
                            DateTimeFormatter.ofPattern("MMM dd yyyy");

                    ui.showLine("Here are the tasks on " + date.format(outFormatter) + ":");

                    int count = 1;
                    boolean found = false;
                    for (Task t : tasks.getTasks()) {
                        if (t.occursOn(date)) {
                            ui.showLine(count + ". " + t);
                            count++;
                            found = true;
                        }
                    }

                    if (!found) {
                        ui.showLine("No tasks on this date.");
                    }
                    continue;
                }



                throw new ChloeException("I'm sorry, I don't know what that means.");

            } catch (ChloeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
