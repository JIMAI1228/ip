import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void occursOn_sameDay_true() {
        LocalDateTime from = LocalDateTime.of(2025, 2, 3, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 2, 3, 12, 0);

        Event e = new Event("meeting", from, to);

        assertTrue(e.occursOn(from.toLocalDate()));
    }

    @Test
    public void occursOn_otherDay_false() {
        LocalDateTime from = LocalDateTime.of(2025,2,2,10,0);
        LocalDateTime to = LocalDateTime.of(2025,2,2,12,0);
        Event e = new Event("meeting", from, to);

        assertFalse(e.occursOn(LocalDate.of(2025,2,3)));
    }
}
