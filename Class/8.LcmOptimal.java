import java.util.Scanner;

class LcmOptimal{
        static void findGcd(int a, int b) {
            int num1 = a;
            int num2 = b;
            while (b != 0) {
                int rem = a % b;
                a = b;
                b = rem;
            }
            int gcd = a;
            int lcm = (num1 * num2) / gcd;
            System.out.println("GCD is"+gcd);
            System.out.println("LCM is"+lcm);
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();
            findGcd(a, b);
        }
    }
// Time Complexity: O(log(min(a, b)))
// Space Complexity: O(1)