package chloe.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Utility class for parsing date-time strings into {@code LocalDateTime}
 * using a strict day/month/year and 24-hour time format.
 */
public class DateTimeParser {
    private static final DateTimeFormatter STRICT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm")
                    .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses the given input string into a {@code LocalDateTime}
     * using the predefined strict formatter.
     *
     * @param input date-time string in the format d/M/uuuu HHmm
     * @return parsed {@code LocalDateTime}
     */
    public static LocalDateTime parseStrict(String input) {
        return LocalDateTime.parse(input.trim(), STRICT_FORMATTER);
    }
}
