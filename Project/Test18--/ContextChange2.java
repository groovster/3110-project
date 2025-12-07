public class ContextChange {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Count: " + i);
        }

        int sum = 0;
        for (int j = 0; j < 7; j++) {
            sum += 3;
            System.out.println("Sum is now: " + sum);
        }

        String[] names = {"Alice", "Bob", "Charlie", "Dana"};
        for (int idx = 0; idx < names.length; idx++) {
            System.out.println("Name: " + names[idx]);
        }

        int product = 1;
        for (int j = 1; j <= 5; j++) {
            product *= j;
            System.out.println("Product step " + j + ": " + product);
        }

        for (int k = 0; k < 15; k++) {
            if (k % 2 == 0) {
                System.out.println("Even number: " + k);
            } else {
                System.out.println("Odd number: " + k);
            }
        }

        for (int countdown = 5; countdown > 0; countdown--) {
            System.out.println("Countdown: " + countdown);
        }
    }
}
