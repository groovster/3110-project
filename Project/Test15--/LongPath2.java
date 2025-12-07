public class LongPath {

    private int taskId;
    private String description;
    private String state; // renamed from status

    public TaskManager(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.state = "NEW";
    }

    public void createTask(int id, String desc) {
        this.taskId = id;
        this.description = desc;
        this.state = "NEW";
        System.out.println("Task created: " + id);
    }

    public void updateTask(String newDesc, String newState) {
        this.description = newDesc;
        this.state = newState;
        System.out.println("Task updated: " + taskId);
    }

    // deleteTask removed (can only return in v4 or later)

    public void printTask() {
        System.out.println("Task[" + taskId + "] " + description + " (" + state + ")");
    }

    public void markComplete() {
        this.state = "DONE";
        System.out.println("Task completed: " + taskId);
    }
}
