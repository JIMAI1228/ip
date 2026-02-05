package duke.parser;

import duke.ChloeException;
import duke.command.*;

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

        switch (cmd){

            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if(parts.length < 2){
                    throw new ChloeException("The description of a mask cannot be empty. ");
                }
                return new MarkCommand(parts[1]);

            case "unmark":
                if(parts.length < 2){
                    throw new ChloeException("The description of an unmask cannot be empty. ");
                }

                return new UnmarkCommand(parts[1]);

            case "delete":
                if(parts.length < 2){
                    throw new ChloeException("The description of a delete cannot be empty. ");
                }

                return new DeleteCommand(parts[1]);

            case "todo":
                if(parts.length < 2){
                    throw new ChloeException("The description of a todo cannot be empty. ");
                }

                return new TodoCommand(parts[1]);

            case "deadline":
                if(parts.length < 2){
                    throw new ChloeException("The description of a deadline cannot be empty. ");
                }

                return new DeadlineCommand(input);

            case "event":
                if (parts.length < 2){
                    throw new ChloeException("The description of an event cannot be empty. ");
                }

                return new EventCommand(input);

            default:
                throw new ChloeException("I'm sorry, I don't know what that means.");
        }
    }
}
