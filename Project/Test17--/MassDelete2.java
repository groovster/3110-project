public class MassDelete {

    private int accountId;
    private String owner;
    private double balance;
    private String accountType;
    private boolean active;

    public AccountManager(int accountId, String owner, double balance, String accountType) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = balance;
        this.accountType = accountType;
        this.active = true;
    }

    public void printAccount() {
        System.out.println("Account[" + accountId + "] " + owner +
            " type=" + accountType + " balance=" + balance +
            " active=" + active);
    }
}
