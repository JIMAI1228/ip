package chloe.ui;

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
    public void showWelcome() {
        this.showLine(
                "Hello! I'm Chloe",
                "What can I do for you?",
                "(Enter help if you want to see all supported command)");
    }

    /**
     * Reads a line of command input from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays one or more lines to the user.
     * <p>
     * This method accepts a variable number of string arguments and prints
     * each line to the standard output on a new line.
     *
     * @param lines the lines to be displayed
     */
    public void showLine(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }

    }

    /**
     * Displays an error message to the user.
     *
     * @param msg the error message
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays the goodbye message before exiting the application.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
