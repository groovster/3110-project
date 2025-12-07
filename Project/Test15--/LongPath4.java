public class LongPath {

    private int taskId;
    private String description;
    private String state;
    private String assignedUser;
    private boolean archived;

    public TaskManager(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.state = "NEW";
        this.assignedUser = "";
        this.archived = false;
    }

    public void createTask(int id, String desc) {
        this.taskId = id;
        this.description = desc;
        this.state = "NEW";
        this.archived = false;
        System.out.println("Task created: " + id);
    }

    public void updateTask(String newDesc, String newState) {
        System.out.println("Update v4: " + taskId);
        this.description = newDesc;
        this.state = newState;
    }

    public void deleteTask() {
        System.out.println("Task deleted (v4): " + taskId);
        this.description = "";
        this.state = "DELETED";
        this.assignedUser = "";
    }

    public void printTask() {
        System.out.println("Task[" + taskId + "] " + description + " (" + state + "), user=" + assignedUser + ", archived=" + archived);
    }

    public void markComplete() {
        this.state = "DONE";
        System.out.println("Task completed: " + taskId);
    }

    public void assignUser(String user) {
        this.assignedUser = user;
        System.out.println("Assigned user " + user + " to task " + taskId);
    }

    public void archiveTask() {
        this.archived = true;
        System.out.println("Task archived: " + taskId);
    }
}
