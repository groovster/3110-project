import java.time.Instant;

public class LongPath {

    private int taskId;
    private String description;
    private String state;
    private String assignedUser;
    private boolean archived;
    private Instant createdAt;

    public TaskManager(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.state = "NEW";
        this.assignedUser = "";
        this.archived = false;
        this.createdAt = Instant.now();
    }

    public void createTask(int id, String desc) { // modified to include timestamp
        this.taskId = id;
        this.description = desc;
        this.state = "NEW";
        this.archived = false;
        this.createdAt = Instant.now();
        System.out.println("Task created: " + id + " at " + createdAt);
    }

    public void updateTask(String newDesc, String newState) {
        System.out.println("Update v7: " + taskId);
        this.description = newDesc;
        this.state = newState;
    }

    public void deleteTask() {
        System.out.println("Task deleted (v7): " + taskId);
        this.description = "";
        this.state = "DELETED";
        this.assignedUser = "";
    }

    public void printTask() {
        System.out.println("Task[" + taskId + "] " + description + " (" + state + "), user=" + assignedUser + ", archived=" + archived + ", createdAt=" + createdAt);
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

    public boolean searchTask(String query) {
        boolean found = (description != null && description.contains(query)) || (assignedUser != null && assignedUser.contains(query));
        System.out.println("Search('" + query + "') => " + found);
        return found;
    }

    public void exportTasks(String[] lines) {
        System.out.println("Exporting " + lines.length + " tasks:");
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
f