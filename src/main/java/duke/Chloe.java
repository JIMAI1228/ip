package duke;

import java.util.Scanner;

public class Chloe {
    private String[] items = new String[100];
    private int itemCount = 0;

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
                for(int i = 0; i < itemCount; i++){
                    System.out.println((i + 1) + ". " + items[i]);
                }
                continue;
            }
            System.out.println("added:" + input);
            items[itemCount] = input;
            itemCount++;

        }
    }
}
