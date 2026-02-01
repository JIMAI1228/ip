package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Chloe {
    private final Storage storage = new Storage();
    private final ArrayList<Task> tasks = new ArrayList<>(storage.load());

    public void run(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Chloe");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
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
                    storage.save(tasks);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(index).markAsDone();
                    Task t = tasks.get(index);
                    storage.save(tasks);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [X] " + t.getDescription());
                    continue;
                }

                if(input.equals("unmark")){
                    throw new ChloeException("The description of an unmask cannot be empty.");
                }

                if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    tasks.get(index).markAsNotDone();
                    Task t = tasks.get(index);
                    storage.save(tasks);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + t.getDescription());
                    continue;
                }

                if(input.equals("todo")){
                    throw new ChloeException("The description of a todo cannot be empty.");
                }

                if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    Task t = new ToDo(desc);
                    tasks.add(t);
                    storage.save(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if(input.equals("deadline")){
                    throw new ChloeException("The description of a deadline cannot be empty.");
                }

                if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks.add(t);
                    storage.save(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                if(input.equals("event")){
                    throw new ChloeException("The description of an event cannot be empty.");
                }

                if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(t);
                    storage.save(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    continue;
                }

                throw new ChloeException("I'm sorry, I don't know what that means.");

            } catch (ChloeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
