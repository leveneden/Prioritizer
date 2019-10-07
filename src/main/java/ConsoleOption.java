public enum ConsoleOption {

    CREATE_TASK(1, "Create a new Task."),
    DELETE_TASK(2, "Delete a Task."),
    REVIEW_TASKS(3, "Review all Tasks."),
    LIST_TASKS(4, "List all tasks."),
    EXIT(5, "Exit.");

    private int index;
    private String description;

    ConsoleOption(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getIndex() {
        return index;
    }

    public String getEnumeratedDescription() {
        return index + ". " + description;
    }
}
