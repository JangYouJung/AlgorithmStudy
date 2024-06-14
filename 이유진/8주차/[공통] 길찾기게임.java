import java.util.*;

public class Solution {
    static int MAX = 100001;
    static int MIN = -1;
    static int TREE_DEPTH = 0;

    public int[][] solution(int[][] nodeinfo) {
        Map<Integer, List<int[]>> preTree = new HashMap<>();
        Map<Integer, List<int[]>> postTree = new HashMap<>();
        Set<Integer> levels = new HashSet<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            preTree.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, i + 1});
            postTree.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, i + 1});
            levels.add(y);
        }

        for (int level : levels) {
            preTree.get(level).sort((a, b) -> Integer.compare(b[0], a[0]));
            postTree.get(level).sort((a, b) -> Integer.compare(b[0], a[0]));
        }

        List<Integer> levelList = new ArrayList<>(levels);
        Collections.sort(levelList, Collections.reverseOrder());
        TREE_DEPTH = levelList.size();

        List<Integer> preorder = new ArrayList<>();
        preOrder(MIN, MAX, 0, preorder, preTree, levelList);
        List<Integer> postorder = new ArrayList<>();
        postOrder(MIN, MAX, 0, postorder, postTree, levelList);

        return new int[][]{
                preorder.stream().mapToInt(i -> i).toArray(),
                postorder.stream().mapToInt(i -> i).toArray()
        };
    }

    private void preOrder(int left, int right, int depth, List<Integer> result, Map<Integer, List<int[]>> tree, List<Integer> level) {
        if (depth == TREE_DEPTH) return;
        if (tree.get(level.get(depth)).isEmpty()) return;
        if (!(left < tree.get(level.get(depth)).get(tree.get(level.get(depth)).size() - 1)[0] && tree.get(level.get(depth)).get(tree.get(level.get(depth)).size() - 1)[0] < right))
            return;

        int[] node = tree.get(level.get(depth)).remove(tree.get(level.get(depth)).size() - 1);
        result.add(node[1]);
        preOrder(left, node[0], depth + 1, result, tree, level);
        preOrder(node[0], right, depth + 1, result, tree, level);
    }

    private void postOrder(int left, int right, int depth, List<Integer> result, Map<Integer, List<int[]>> tree, List<Integer> level) {
        if (depth == TREE_DEPTH) return;
        if (tree.get(level.get(depth)).isEmpty()) return;
        if (!(left < tree.get(level.get(depth)).get(tree.get(level.get(depth)).size() - 1)[0] && tree.get(level.get(depth)).get(tree.get(level.get(depth)).size() - 1)[0] < right))
            return;

        int[] node = tree.get(level.get(depth)).remove(tree.get(level.get(depth)).size() - 1);
        postOrder(left, node[0], depth + 1, result, tree, level);
        postOrder(node[0], right, depth + 1, result, tree, level);
        result.add(node[1]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] result = sol.solution(nodeinfo);
        System.out.println("Preorder: " + Arrays.toString(result[0]));
        System.out.println("Postorder: " + Arrays.toString(result[1]));
    }
}
