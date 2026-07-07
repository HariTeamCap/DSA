class Solution {
    static final long MOD = 1000000007L;
    public int computeValue(int n) {
        // code here
        long[] fact = new long[2 * n + 1];
        fact[0] = 1;
        for (int i = 1; i <= 2 * n; i++){
            fact[i] = (fact[i - 1]* i) % MOD;
        }
        long numerator = fact[2 * n];
        long denominator = (fact[n] * fact[n])% MOD;
        long inverse = modPow(denominator, MOD - 2);
        return (int) ((numerator * inverse)% MOD);
    }
    private long modPow(long base, long exp) {
        long ans = 1;
        while (exp > 0){
            if ((exp&1) == 1) {
             ans = (ans * base)% MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return ans;
    }
}