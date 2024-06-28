package codingtest_study;

class Solution_week11 {
    // 누적합 계산하는 함수
    private void accumulate(int[][] accumulatedArray) {
        // 행의 누적합 계산
        for (int i = 1; i < accumulatedArray.length; i++) {
            for (int j = 0; j < accumulatedArray[0].length; j++) {
                accumulatedArray[i][j] += accumulatedArray[i-1][j];
            }
        }

        // 열의 누적합 계산
        for (int i = 0; i < accumulatedArray.length; i++) {
            for (int j = 1; j < accumulatedArray[0].length; j++) {
                accumulatedArray[i][j] += accumulatedArray[i][j-1];
            }
        }
    }

    // skill 결과 누적합 배열에 적용하는 함수
    private void pointScore(int[][] accumulatedArray, int r1, int c1, int r2, int c2, int value) {
        accumulatedArray[r1][c1] += value;
        accumulatedArray[r1][c2+1] -= value;
        accumulatedArray[r2+1][c1] -= value;
        accumulatedArray[r2+1][c2+1] += value;
    }

    public int solution(int[][] board, int[][] skill) {

        int answer = 0;
        int [][] accumulatedArray = new int[1001][1001];

        for (int[] s: skill) {
            int type = s[0];
            int r1 = s[1];
            int r2 = s[3];
            int c1 = s[2];
            int c2 = s[4];
            int degree = s[5];
            int value = degree;

            if (type == 1) value *= -1;
            pointScore(accumulatedArray, r1, c1, r2, c2, value);
        }

        accumulate(accumulatedArray);

        // board 배열에 누적합 결과 반영
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += accumulatedArray[i][j];
                if (board[i][j] > 0) answer += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution_week11 sol = new Solution_week11();
        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        int answer = sol.solution(board, skill);

        System.out.println(answer);
    }
}
