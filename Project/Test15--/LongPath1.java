public class LongPath {

    private int taskId;
    private String description;
    private String status;

    public TaskManager(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "NEW";
    }

    public void createTask(int id, String desc) {
        this.taskId = id;
        this.description = desc;
        this.status = "NEW";
        System.out.println("Task created: " + id);
    }

    public void updateTask(String newDesc, String newStatus) {
        this.description = newDesc;
        this.status = newStatus;
        System.out.println("Task updated: " + taskId);
    }

    public void deleteTask() {
        System.out.println("Task deleted: " + taskId);
        this.description = "";
        this.status = "DELETED";
    }

    public void printTask() {
        System.out.println("Task[" + taskId + "] " + description + " (" + status + ")");
    }
}
