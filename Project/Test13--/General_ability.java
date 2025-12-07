public class General_ability {

    private int count;
    private String name;
    private boolean archived;
    public ReportManager() {
        this.count = 0;
        this.name = "Untitled";
        this.archived = false;
    }

    public void generateReport() {
        System.out.println("Generating report updated...");
    }

    public void exportData() {
        System.out.println("Exporting report...");
    }

    



    public int getCount() { 
        return count;
    }

    public String getName() { 
        return name;
    }
}
