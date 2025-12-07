public class ContextChange {

    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            System.out.println("Count: " + i);
            i++;
        }

        int sum = 0;
        while (sum < 20) {
            sum += 3;
            System.out.println("Sum is now: " + sum);
        }

        String[] names = {"Alice", "Bob", "Charlie", "Dana"};
        int idx = 0;
        while (idx < names.length) {
            System.out.println("Name: " + names[idx]);
            idx++;
        }

        int product = 1;
        int j = 1;
        while (j <= 5) {
            product *= j;
            System.out.println("Product step " + j + ": " + product);
            j++;
        }

        int k = 0;
        while (k < 15) {
            if (k % 2 == 0) {
                System.out.println("Even number: " + k);
            } else {
                System.out.println("Odd number: " + k);
            }
            k++;
        }

        int countdown = 5;
        while (countdown > 0) {
            System.out.println("Countdown: " + countdown);
            countdown--;
        }
    }
}
