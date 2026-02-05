package duke.ui;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Chloe chatbot.
 * <p>
 * This class is responsible for displaying messages to the user and
 * reading user input from standard input.
 */
public class Ui {

    /**
     * Scanner used to read user input from standard input.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome(){
        System.out.println("Hello! I'm Chloe");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a line of command input from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Displays a normal output line to the user.
     *
     * @param s the message to display
     */
    public void showLine(String s){
        System.out.println(s);
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg the error message
     */
    public void showError(String msg){
        System.out.println(msg);
    }

    /**
     * Displays the goodbye message before exiting the application.
     */
    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
