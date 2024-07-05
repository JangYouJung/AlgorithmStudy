import java.util.*;
class Solution {
    public boolean solution(String[] phoneBook) {
        Map<String, Integer> hash = new HashMap<String, Integer>();

        for (int i = 0; i < phoneBook.length; i++) {
            hash.put(phoneBook[i], i);
        }

        for (int i = 0; i < phoneBook.length; i++) {
            for (int j = 0; j < phoneBook[i].length(); j++) {
                if (hash.containsKey(phoneBook[i].substring(0, j))) {
                    return false;
                }
            }
        }

        return true;
    }
}