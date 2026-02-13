package duke.task;

/**
 * Represents the different types of tasks in the application.
 */
public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String icon;

    /**
     * Constructs a TaskType with the specified icon.
     *
     * @param icon the icon representing the task type
     */
    TaskType(String icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon associated with this task type.
     *
     * @return the task type icon
     */
    public String getIcon() {
        return this.icon;
    }
}

