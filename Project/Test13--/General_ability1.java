public class General_ability {

    private int reportCount;
    private String title;

    public ReportManager() {
        this.reportCount = 0;
        this.title = "Untitled";
    }


    public void generateReport() {
        System.out.println("Generating report...");
    }

    public void exportReport() {
        System.out.println("Exporting report...");
    }

    public void deleteReport() {
        System.out.println("Deleting report...");
    }

    public int getReportCount() {
        return reportCount;
    }

    public String getTitle() {
        return title;
    }
}
