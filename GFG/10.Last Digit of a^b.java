class Solution {
    public int getLastDigit(String a, String b) {

        if (b.equals("0")) return 1;

        int base = a.charAt(a.length() - 1) - '0';

        int exp = 0;
        for (int i = 0; i < b.length(); i++) {
            exp = (exp * 10 + (b.charAt(i) - '0')) % 4;
        }

        if (exp == 0) exp = 4;

        int result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % 10;
            }

            base = (base * base) % 10;
            exp >>= 1;
        }

        return result;
    }
}
//Time: O(|b|)
//Space: O(1)