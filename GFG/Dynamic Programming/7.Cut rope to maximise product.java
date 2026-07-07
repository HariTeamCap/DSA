class Solution {
    public int maxProduct(int n) {
        
        if (n == 2) return 1;
        if (n == 3) return 2;

        long ans;

        if (n % 3 == 0) {
            ans = power(3, n / 3);
        } 
        else if (n % 3 == 1) {
            ans = power(3, n / 3 - 1) * 4;
        } 
        else {
            ans = power(3, n / 3) * 2;
        }

        return (int) ans;
    }

    private long power(long base, int exp) {
        long res = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res *= base;
            }
            base *= base;
            exp >>= 1;
        }

        return res;
    }
}

// Time: O(log n)
// Space: O(1)