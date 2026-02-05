import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTask_sizeIncreases() {
        TaskList list = new TaskList();
        list.add(new ToDo("read"));

        assertEquals(1, list.size());
    }

    @Test
    public void removeTask_sizeDecreases() {
        TaskList list = new TaskList();
        list.add(new ToDo("read"));
        list.remove(0);

        assertEquals(0, list.size());
    }
}
