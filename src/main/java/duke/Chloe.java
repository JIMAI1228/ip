package duke;

import java.util.Scanner;

public class Chloe {
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
            System.out.println(input);
        }
    }
}
