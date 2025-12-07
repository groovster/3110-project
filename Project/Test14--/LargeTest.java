public class LargeTest {

    private long base;
    private float multiplier;
    private String label;

    public MathLibrary(long base, float multiplier, String label) {
        this.base = base;
        this.multiplier = multiplier;
        this.label = label;
    }

    public int addNumbers(int a, int b) {
        return a + b;
    }

    public int subtractNumbers(int a, int b) {
        return a - b;
    }

    public int multiplyNumbers(int a, int b) {
        return a * b;
    }

    public int divideNumbers(int a, int b) {
        if (b == 0) return 0;
        return a / b;
    }

    public double squareValue(double x) {
        return x * x;
    }

    public double cubeValue(double x) {
        return x * x * x;
    }

    public double powerValue(double x, double y) {
        return Math.pow(x, y);
    }

    public double sqrtValue(double x) {
        return Math.sqrt(x);
    }

    public double logValue(double x) {
        return Math.log(x);
    }

    public double expValue(double x) {
        return Math.exp(x);
    }

    public int modulus(int a, int b) {
        return a % b;
    }

    public int absolute(int a) {
        return Math.abs(a);
    }

    public double sine(double x) {
        return Math.sin(x);
    }

    public double cosine(double x) {
        return Math.cos(x);
    }

    public double tangent(double x) {
        return Math.tan(x);
    }

    public double arcsine(double x) {
        return Math.asin(x);
    }

    public double arccosine(double x) {
        return Math.acos(x);
    }

    public double arctangent(double x) {
        return Math.atan(x);
    }

    public double hyperbolicSine(double x) {
        return Math.sinh(x);
    }

    public double hyperbolicCosine(double x) {
        return Math.cosh(x);
    }

    public double hyperbolicTangent(double x) {
        return Math.tanh(x);
    }

    public double randomValue() {
        return Math.random();
    }

    public int maximum(int a, int b) {
        return Math.max(a, b);
    }

    public int minimum(int a, int b) {
        return Math.min(a, b);
    }

    public double hypotenuse(double a, double b) {
        return Math.hypot(a, b);
    }

    public double ceiling(double x) {
        return Math.ceil(x);
    }

    public double flooring(double x) {
        return Math.floor(x);
    }

    public double rounding(double x) {
        return Math.round(x);
    }

    public double radians(double x) {
        return Math.toRadians(x);
    }

    public double degrees(double x) {
        return Math.toDegrees(x);
    }

    // 🔹 NEW METHODS INSERTED MID-FILE (cause line shifts)
    public double averageThree(double a, double b, double c) {
        return (a + b + c) / 3.0;
    }

    public double weightedTriple(double a, double b, double c) {
        return a * 0.2 + b * 0.3 + c * 0.5;
    }

    public int doubleFactorial(int n) {
        if (n <= 0) return 1;
        int result = 1;
        for (int i = n; i > 0; i -= 2) {
            result *= i;
        }
        return result;
    }

    public int triangularNumber(int n) {
        return n * (n + 1) / 2;
    }

    public int squareNumber(int n) {
        return n * n;
    }

    public int cubeNumber(int n) {
        return n * n * n;
    }

    public double inverse(double x) {
        return 1.0 / x;
    }

    public double reciprocal(double x) {
        return 1.0 / x;
    }

    public double linearCombination(double a, double b) {
        return a * multiplier + b * base;
    }

    public double shiftedEquation(double x) {
        return (x + base) * multiplier;
    }

    public double scaledEquation(double x) {
        return (x * multiplier) + base;
    }

    // 🔹 CONTINUATION OF ORIGINAL METHODS (shifted down)
    public double weightedAverageValue(double a, double b) {
        return (a * multiplier + b * (1 - multiplier));
    }

    public int factorialValue(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public int fibonacciValue(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public double arithmeticMeanValue(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum / arr.length;
    }

    public double geometricMeanValue(double[] arr) {
        double product = 1;
        for (double v : arr) {
            product *= v;
        }
        return Math.pow(product, 1.0 / arr.length);
    }

    public double harmonicMeanValue(double[] arr) {
        double sum = 0;
        for (double v : arr) {
            sum += 1.0 / v;
        }
        return arr.length / sum;
    }

    public double varianceValue(double[] arr) {
        double mean = arithmeticMeanValue(arr);
        double sum = 0;
        for (double v : arr) {
            sum += (v - mean) * (v - mean);
        }
        return sum / arr.length;
    }

    public double stddevValue(double[] arr) {
        return Math.sqrt(varianceValue(arr));
    }

    public double dotProductValue(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
