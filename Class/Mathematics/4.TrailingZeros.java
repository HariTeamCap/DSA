import java.util.Scanner;
class TrailingZeros {
    static int factorialTrailingZeros(int n) {
        int count = 0;
        while (n > 0) {
            n = n/5;
            count = count + n;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = factorialTrailingZeros(n);
        System.out.println(res);
    }
}