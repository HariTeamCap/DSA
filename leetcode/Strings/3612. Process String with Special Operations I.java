class Solution {
    public String processStr(String s) {

        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (Character.isLowerCase(ch)) {
                sb.append(ch);
            }
            else if (ch == '*') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            else if (ch == '#') {
                sb.append(sb.toString());
            }
            else if (ch == '%') {
                sb.reverse();
            }
        }

        return sb.toString();
    }
}
// Time: O(final_string_length)
// Space: O(final_string_length)