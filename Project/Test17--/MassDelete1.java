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

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited " + amount + ", new balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            System.out.println("Withdrew " + amount + ", new balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal");
        }
    }

    public void transfer(AccountManager target, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            target.balance += amount;
            System.out.println("Transferred " + amount + " to " + target.owner);

        } else {
            System.out.println("Transfer failed");
        }
    }

    public void freezeAccount() {
        this.active = false;
        System.out.println("Account frozen: " + accountId);
    }

    public void activateAccount() {
        this.active = true;
        System.out.println("Account activated: " + accountId);
    }

    public void printAccount() {
        System.out.println("Account[" + accountId + "] " + owner +
            " type=" + accountType + " balance=" + balance +
            " active=" + active);
    }
}
