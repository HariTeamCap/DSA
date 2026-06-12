class Solution {
    public int findIndex(String s) {
        // code here
        int rightClose = 0;
        for (char ch : s.toCharArray()){
            if (ch == ')') {
                rightClose++;
            }
        }
        int openCount = 0;
        if (openCount == rightClose) {
            return 0;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                openCount++;
            } else {
                rightClose--;
            }
            if (openCount == rightClose) {
                return i + 1;
            }
        }
        return s.length();
    }
}

//Time Complexity: O(n)
//Auxiliary Space: O(1)
//Arrays & strings