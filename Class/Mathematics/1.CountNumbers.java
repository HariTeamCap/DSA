import java.util.Scanner;

class Counter{
    static int countDigits(int n){
        int count = 0;
        while(n > 0){
            n = n/10;
            count++;
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = countDigits(n);
        System.out.println(res);
    }
}