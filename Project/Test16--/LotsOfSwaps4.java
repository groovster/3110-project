public class LotsOfSwaps {

    public void deleteDoc() { // reintroduced at very top
        System.out.println("Document deleted: " + docId);
        this.heading = "";
        this.author = "";
    }

    private String heading;  // swapped order again
    private int docId;
    private String author;

    public void restoreDoc(String h, String a) { // new method
        this.heading = h;
        this.author = a;
        System.out.println("Document restored: " + docId);
    }

    public void updateDoc(String newHeading, String newAuthor) {
        this.heading = newHeading;
        this.author = newAuthor;
        System.out.println("Document updated: " + docId);
    }

    public void createDoc(int id, String h, String a) {
        this.docId = id;
        this.heading = h;
        this.author = a;
        System.out.println("Document created: " + id);
    }

    public DocumentManager(int docId, String heading, String author) {
        this.docId = docId;
        this.heading = heading;
        this.author = author;
    }

    public void printDoc() { // moved to very bottom
        System.out.println("Doc[" + docId + "] " + heading + " by " + author);
    }

    public void archiveDoc() {
        System.out.println("Document archived: " + docId);
    }
}
