public class LargeTest {

    private int baseValue;
    private double factor;

    public MathLibrary(int baseValue, double factor) {
        this.baseValue = baseValue;
        this.factor = factor;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public double square(double x) {
        return x * x;
    }

    public double cube(double x) {
        return x * x * x;
    }

    public double power(double x, double y) {
        return Math.pow(x, y);
    }

    public double sqrt(double x) {
        return Math.sqrt(x);
    }

    public double log(double x) {
        return Math.log(x);
    }

    public double exp(double x) {
        return Math.exp(x);
    }

    public int mod(int a, int b) {
        return a % b;
    }

    public int abs(int a) {
        return Math.abs(a);
    }

    public double sin(double x) {
        return Math.sin(x);
    }

    public double cos(double x) {
        return Math.cos(x);
    }

    public double tan(double x) {
        return Math.tan(x);
    }

    public double asin(double x) {
        return Math.asin(x);
    }

    public double acos(double x) {
        return Math.acos(x);
    }

    public double atan(double x) {
        return Math.atan(x);
    }

    public double sinh(double x) {
        return Math.sinh(x);
    }

    public double cosh(double x) {
        return Math.cosh(x);
    }

    public double tanh(double x) {
        return Math.tanh(x);
    }

    public double random() {
        return Math.random();
    }

    public int max(int a, int b) {
        return Math.max(a, b);
    }

    public int min(int a, int b) {
        return Math.min(a, b);
    }

    public double hypot(double a, double b) {
        return Math.hypot(a, b);
    }

    public double ceil(double x) {
        return Math.ceil(x);
    }

    public double floor(double x) {
        return Math.floor(x);
    }

    public double round(double x) {
        return Math.round(x);
    }

    public double toRadians(double x) {
        return Math.toRadians(x);
    }

    public double toDegrees(double x) {
        return Math.toDegrees(x);
    }

    public double scale(double x) {
        return x * factor;
    }

    public int shift(int x) {
        return x + baseValue;
    }

    public double weightedAverage(double a, double b) {
        return (a * factor + b * (1 - factor));
    }

    public int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public int fibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public double arithmeticMean(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum / arr.length;
    }

    public double geometricMean(double[] arr) {
        double product = 1;
        for (double v : arr) {
            product *= v;
        }
        return Math.pow(product, 1.0 / arr.length);
    }

    public double harmonicMean(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += 1.0 / v;
        }
        return arr.length / sum;
    }

    public double variance(double[] arr) {
        double mean = arithmeticMean(arr);
        double sum = 0;
        for (double v : arr) {
            sum += (v - mean) * (v - mean);
        }
        return sum / arr.length;
    }

    public double stddev(double[] arr) {
        return Math.sqrt(variance(arr));
    }

    public double dotProduct(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}