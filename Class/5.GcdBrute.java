import java.util.Scanner;

class GcdBrute {
     static int findGcd(int n1, int n2) {
         int gcd = 1;
         int min = Math.min(n1, n2);
         for (int i = 1; i <= min; i++) {
             if (n1 % i == 0 && n2 % i == 0) {
                 gcd = i;
             }
         }
         return gcd;
     }

     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int n1 = sc.nextInt();
         int n2 = sc.nextInt();
         int res = findGcd(n1, n2);
         System.out.println(res);
     }
}
