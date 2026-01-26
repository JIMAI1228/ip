package duke;

import java.util.Scanner;

public class Chloe {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void run(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Chloe");
        System.out.println("What can I do for you?");

        while (true){
            String input = scanner.nextLine();

            if(input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if(input.equals("list")){
                for(int i = 0; i < taskCount; i++){
                    System.out.println((i + 1) + ". [" + tasks[i].getStatus() + "]" + tasks[i].getDescription());
                }
                continue;
            }

            if(input.startsWith("mark ")){
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsDone();
                Task t = tasks[index];
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + t.getDescription());
            }

            if(input.startsWith("unmark ")){
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].markAsNotDone();
                Task t = tasks[index];
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + t.getDescription());
            }

            System.out.println("added:" + input);
            tasks[taskCount] = new Task(input);
            taskCount++;

        }
    }
}
