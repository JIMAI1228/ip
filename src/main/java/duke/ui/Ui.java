package duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome(){
        System.out.println("Hello! I'm Chloe");
        System.out.println("What can I do for you?");
    }

    public String readCommand(){
        return scanner.nextLine();
    }

    public void showLine(String s){
        System.out.println(s);
    }

    public void showError(String msg){
        System.out.println(msg);
    }

    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
