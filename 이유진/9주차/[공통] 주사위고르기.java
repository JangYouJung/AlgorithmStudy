import java.util.*;
import java.util.stream.Collectors;

public class Solution_week10 {
    static int N;
    static Map<Integer, List<Integer>> diceInfo;
    static int maxWinningProb = 0;
    static List<Integer> sumA;
    static List<Integer> sumB;
    static List<Integer> resultA;

    public int[] solution(int[][] dice) {
        resultA = new ArrayList<>();
        N = dice.length;
        diceInfo = new HashMap<>();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            List<Integer> diceN = Arrays.stream(dice[i]).boxed().collect(Collectors.toList());
            diceInfo.computeIfAbsent(i+1, k -> new ArrayList<>(diceN));
            arr[i] = i+1;
        }
        boolean[] visited = new boolean[N];

        combination(arr, visited, 0, N, N/2);

        int[] answer = new int[N/2];
        answer = resultA.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    // n개의 주사위 중 n/2(=r)개의 주사위를 고르는 조합
    public void combination(int[] arr, boolean[] visited, int start, int n, int r) {

        if (r == 0) { // n개 중에서 r 개의 조합 다 구했음
            calWinningProb(arr, visited);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n,  r - 1);
            visited[i] = false;
        }

    }
    // 각 주사위 조합에 대해 주사위들을 모두 굴려 나오는 값들의 합을 구하는 함수
    public void getPossibleSum(int start, int depth, List<Integer> diceNum, int sum, List<Integer> sumList) {
        if (start == depth) {
            sumList.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            sum += diceInfo.get(diceNum.get(start)).get(i);
            getPossibleSum(start+1, depth, diceNum, sum, sumList);
            sum -= diceInfo.get(diceNum.get(start)).get(i);
        }
    }

    private void calWinningProb(int[] arr, boolean[] visited) {
        List<Integer> diceOfA = new ArrayList<>();
        List<Integer> diceOfB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            //System.out.println(arr[i]);
            if (visited[i]) {
                diceOfA.add(arr[i]);
            }
            else diceOfB.add(arr[i]);
        }


        // A가 주사위 굴려 나온 값 모두 더한 것들의 후보
        sumA = new ArrayList<>();
        sumB = new ArrayList<>();

        getPossibleSum(0, N/2, diceOfA, 0, sumA);
        getPossibleSum(0, N/2, diceOfB, 0, sumB);

        Collections.sort(sumB);

        int winningProb = 0;
        for (int i = 0; i < sumA.size(); i++) {
            winningProb += leftMostBinarySearch(sumB, sumA.get(i), sumB.size() - 1);
        }
        if (winningProb > maxWinningProb) {
            maxWinningProb = winningProb;
            resultA = new ArrayList<>(diceOfA);
        }

    }

    public int leftMostBinarySearch(List<Integer> sumB, int target, int end) {
        int m;
        int left = 0;
        int right = end;
        while (left <= right) {
            m = (left + right) / 2;
            if (target <= sumB.get(m)) {
                right = m - 1;
            }
            else if (target > sumB.get(m)) {
                left = m + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Solution_week10 sol = new Solution_week10();
        Map<Integer, List<Integer>> diceInfo = new HashMap<>();

        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};

        int[] result = sol.solution(dice);
        for(int i: result) {
            System.out.println(i);
        }
    }
}
