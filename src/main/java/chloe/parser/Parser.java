package chloe.parser;


import chloe.ChloeException;
import chloe.command.Command;
import chloe.command.DeadlineCommand;
import chloe.command.DeleteCommand;
import chloe.command.EventCommand;
import chloe.command.ExitCommand;
import chloe.command.FindCommand;
import chloe.command.HelpCommand;
import chloe.command.ListCommand;
import chloe.command.MarkCommand;
import chloe.command.TodoCommand;
import chloe.command.UnmarkCommand;

/**
 * Parses user input strings and converts them into executable Command objects.
 * <p>
 * This class acts as a factory that interprets user commands and creates
 * the corresponding Command subclass.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input Full command entered by the user.
     * @return Command object representing the user instruction.
     * @throws ChloeException If the command is invalid or missing required arguments.
     */
    public static Command parse(String input) throws ChloeException {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {

        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            requireArgument(parts, "The description of a mark cannot be empty.");
            return new MarkCommand(parts[1]);

        case "unmark":
            requireArgument(parts, "The description of an unmask cannot be empty. ");
            return new UnmarkCommand(parts[1]);

        case "delete":
            requireArgument(parts, "The description of a delete cannot be empty. ");
            return new DeleteCommand(parts[1]);

        case "todo":
            requireArgument(parts, "The description of a todo cannot be empty. ");
            return new TodoCommand(parts[1]);

        case "deadline":
            requireArgument(parts, "The description of a deadline cannot be empty. ");
            return new DeadlineCommand(input);

        case "event":
            requireArgument(parts, "The description of an event cannot be empty. ");
            return new EventCommand(input);

        case "find":
            requireArgument(parts, "Keyword cannot be empty.");
            return new FindCommand(parts[1]);

        case "help":
            return new HelpCommand();
            
        default:
            throw new ChloeException("I'm sorry, I don't know what that means.");
        }
    }

    /**
     * Checks whether the command contains an argument.
     *
     * @param parts the split user input
     * @param message the exception message to throw if the argument is missing
     * @throws ChloeException if the argument is missing
     */
    private static void requireArgument(String[] parts, String message) throws ChloeException {
        if (parts.length < 2) {
            throw new ChloeException(message);
        }
    }
}
