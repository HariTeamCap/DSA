import java.util.Scanner;

class OptimalPrime {
    static void findPrime(int n){
        boolean isprime = true;
        if(n <= 1){
            isprime = false;
        } else {
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    isprime = false;
                }
            }
        }
        if(isprime){
            System.out.println("It is a Prime Number!!");
        }else {
            System.out.println("It is not a Prime Number!!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        findPrime(a);
    }
}
