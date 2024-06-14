import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int scoreDiff = 0;
    public static int leastScore;
    public static int leastScoreNum;
    public static int N;
    public static List<int[]> lionScoreX;
    public static int[] scores;
    public static int[] apeachInfo;
    public static int[] result;

    public int[] solution(int n, int[] info) {
        int[] answer = {};

        N = n;

        lionScoreX = new ArrayList<>();
        scores = new int[11];
        apeachInfo = new int[11];
        result = new int[11];

        for (int i = 0; i < 11; i++) {
            lionScoreX.add(new int[] {info[i] + 1, 0});
            apeachInfo[i] = info[i];
        }

        permutationWithReputation(0);

        if (scoreDiff == 0) {
            return new int[] {-1};
        }
        else return result;

    }

    // 각 과녁 점수값에 대해 0 ~ N 의 값을 중복순열로 할당, 어피치의 해당 과녁 점수 값보다 1 큰 값이거나 0이어야 함
    public static void permutationWithReputation(int cnt) {

        if (cnt == 11) {
            getResultScore();
            return;
        }

        // 대상 집합(과녁에 맞은 화살 수)를 순회하며 숫자를 하나 선택한다.
        for(int i = 0; i <= lionScoreX.get(cnt)[0]; i++) {
            scores[cnt] = i;
            // 자신을 재귀호출한다.
            permutationWithReputation(cnt + 1);
        }

    }

    public static void getResultScore() {
        int apeachScore = 0;
        int lionScore = 0;

        if (getN()) {
            for(int i = 0; i < 11; i++) {
                if (apeachInfo[i] == 0 && scores[i] == 0) {
                    continue;
                }
                if (apeachInfo[i] >= scores[i]) {
                    apeachScore += (10 - i);
                }
                else if(apeachInfo[i] < scores[i]) lionScore += (10 - i);
            }

        }

        else return;

        if(lionScore - apeachScore > scoreDiff) {
            scoreDiff = lionScore - apeachScore;
            for (int i = 10; i >= 0 ; i--) {
                if (scores[i] > 0) {
                    leastScore = 10-i;
                    leastScoreNum = scores[i];
                    break;
                }
            }
            for (int i = 0; i < 11; i++) {
                result[i] = scores[i];
            }
        }
        else if(lionScore - apeachScore == scoreDiff) {
            int leastX = 0;
            int leastNumX = 0;
            for (int i = 10; i >= 0 ; i--) {
                if (scores[i] > 0) {
                    leastX = 10-i;
                    leastNumX = scores[i];
                    break;
                }
            }
            if(leastX < leastScore) {
                leastScore = leastX;
                leastScoreNum = leastNumX;
                for (int i = 0; i < 11; i++) {
                    result[i] = scores[i];
                }
                //return;
            }
            else if (leastX == leastScore && leastNumX > leastScoreNum) {
                leastScoreNum = leastNumX;
                for (int i = 0; i < 11; i++) {
                    result[i] = scores[i];
                }
            }
        }

        else return;

    }

    public static boolean getN() {
        int n = 0;
        for (int score: scores) {
            n += score;
        }
        if (n != N) {
            return false;
        }
        return true;
    }

}