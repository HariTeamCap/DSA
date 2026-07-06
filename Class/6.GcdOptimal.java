import java.util.Scanner;

class GcdOptimal {
    static void findGcd(int a, int b) {
        while (b != 0) {
            int rem = a % b;
            a = b;
            b = rem;
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        findGcd(a, b);
    }
}

// Time: O(log(min(a, b)))
// Space: O(1)