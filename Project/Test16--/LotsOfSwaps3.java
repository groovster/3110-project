public class LotsOfSwaps {

    private int docId;
    private String heading;  // renamed from title
    private String author;

    public void createDoc(int id, String h, String a) { // moved to top
        this.docId = id;
        this.heading = h;
        this.author = a;
        System.out.println("Document created: " + id);
    }

    public void archiveDoc() { // new method
        System.out.println("Document archived: " + docId);
    }

    public void updateDoc(String newHeading, String newAuthor) {
        this.heading = newHeading;
        this.author = newAuthor;
        System.out.println("Document updated: " + docId);
    }

    public void printDoc() { // moved to bottom
        System.out.println("Doc[" + docId + "] " + heading + " by " + author);
    }

    public DocumentManager(int docId, String heading, String author) { // constructor moved to bottom
        this.docId = docId;
        this.heading = heading;
        this.author = author;
    }

    // deleteDoc still absent
}
