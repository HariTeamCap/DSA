class Solution {
    public ArrayList<Integer> constructList(int[][] queries) {

        ArrayList<Integer> list = new ArrayList<>();
        int xor = 0;

        list.add(0);

        for (int[] q : queries) {

            if (q[0] == 0) {
                list.add(q[1] ^ xor);
            } else {
                xor ^= q[1];
            }
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) ^ xor);
        }

        Collections.sort(list);

        return list;
    }
}