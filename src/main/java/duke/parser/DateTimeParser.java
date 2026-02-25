package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DateTimeParser {
    private static final DateTimeFormatter STRICT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm")
                    .withResolverStyle(ResolverStyle.STRICT);

    public static LocalDateTime parseStrict(String input) {
        return LocalDateTime.parse(input.trim(), STRICT_FORMATTER);
    }
}
