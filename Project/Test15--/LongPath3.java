public class LongPath {

    private int taskId;
    private String description;
    private String state;
    private String assignedUser;

    public TaskManager(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.state = "NEW";
        this.assignedUser = "";
    }

    public void createTask(int id, String desc) {
        this.taskId = id;
        this.description = desc;
        this.state = "NEW";
        System.out.println("Task created: " + id);
    }

    public void updateTask(String newDesc, String newState) {
        System.out.println("Updating task " + taskId + " from [" + description + ", " + state + "]");
        this.description = newDesc;
        this.state = newState;
        System.out.println("Updated to [" + description + ", " + state + "]");
    }

    // deleteTask still removed (eligible to return in v4+)

    public void printTask() {
        System.out.println("Task[" + taskId + "] " + description + " (" + state + "), user=" + assignedUser);
    }

    public void markComplete() {
        this.state = "DONE";
        System.out.println("Task completed: " + taskId);
    }

    public void assignUser(String user) {
        this.assignedUser = user;
        System.out.println("Assigned user " + user + " to task " + taskId);
    }
}
