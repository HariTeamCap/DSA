import java.util.Scanner;

class LcmBrute {
    static int findLcm(int n1, int n2){
        int max = Math.max(n1, n2);
        int i = max;
        while (true){
            if (i % n1 == 0 &&  i % n2 == 0){
                return i;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int res = findLcm(n1, n2);
        System.out.println(res);
    }
}
// Time Complexity: O(LCM(n1, n2))
// Space Complexity: O(1)