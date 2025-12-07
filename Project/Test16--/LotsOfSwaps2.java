public class LotsOfSwaps {

    private String author;   // moved first
    private int docId;       // moved second
    private String title;    // moved last

    public void updateDoc(String newTitle, String newAuthor) { // moved above constructor
        this.title = newTitle;
        this.author = newAuthor;
        System.out.println("Document updated: " + docId);
    }

    public DocumentManager(int docId, String title, String author) {
        this.docId = docId;
        this.title = title;
        this.author = author;
    }

    public void printDoc() { // moved before createDoc
        System.out.println("Doc[" + docId + "] " + title + " by " + author);
    }

    public void createDoc(int id, String t, String a) {
        this.docId = id;
        this.title = t;
        this.author = a;
        System.out.println("Document created: " + id);
    }

    // deleteDoc removed (eligible to return v4+)
}
