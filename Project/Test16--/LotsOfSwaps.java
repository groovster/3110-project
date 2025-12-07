public class LotsOfSwaps {

    private int docId;
    private String title;
    private String author;

    public DocumentManager(int docId, String title, String author) {
        this.docId = docId;
        this.title = title;
        this.author = author;
    }

    public void createDoc(int id, String t, String a) {
        this.docId = id;
        this.title = t;
        this.author = a;
        System.out.println("Document created: " + id);
    }

    public void updateDoc(String newTitle, String newAuthor) {
        this.title = newTitle;
        this.author = newAuthor;
        System.out.println("Document updated: " + docId);
    }

    public void deleteDoc() {
        System.out.println("Document deleted: " + docId);
        this.title = "";
        this.author = "";
    }

    public void printDoc() {
        System.out.println("Doc[" + docId + "] " + title + " by " + author);
    }
}
