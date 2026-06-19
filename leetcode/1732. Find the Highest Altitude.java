class Solution {
    public int largestAltitude(int[] gain) {

        int altitude = 0;
        int maxAltitude = 0;

        for (int g : gain) {
            altitude += g;
            maxAltitude = Math.max(maxAltitude, altitude);
        }

        return maxAltitude;
    }
}
//Time: O(n) n = gain.length
//Space: O(1)