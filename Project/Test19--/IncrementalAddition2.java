public class IncrementalAddition {

    private int featureId;
    private String name;
    private String createdAt;   // new
    private String updatedAt;   // new

    public FeatureBuilder(int featureId, String name, String createdAt) {
        this.featureId = featureId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public void createFeature(int id, String n) {
        this.featureId = id;
        this.name = n;
        this.updatedAt = java.time.LocalDateTime.now().toString();
        System.out.println("Feature created: " + id);
    }

    public void printFeature() {
        System.out.println("Feature[" + featureId + "] " + name +
            " created=" + createdAt + " updated=" + updatedAt);
    }

    public void logFeature() { // new
        System.out.println("Logging feature: " + featureId + " - " + name);
    }
}
