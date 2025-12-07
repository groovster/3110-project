public class IncrementalAddition {

    private int featureId;
    private String name;

    public FeatureBuilder(int featureId, String name) {
        this.featureId = featureId;
        this.name = name;
    }

    public void createFeature(int id, String n) {
        this.featureId = id;
        this.name = n;
        System.out.println("Feature created: " + id);
    }

    public void printFeature() {
        System.out.println("Feature[" + featureId + "] " + name);
    }
}
