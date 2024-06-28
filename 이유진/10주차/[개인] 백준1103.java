import java.io.*;
import java.util.*;

public class Solution_BJ1103 {

    static int N;
    static int M;
    static int MaxMove = -1;
    static boolean isLoop = false;
    static int size;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] readLine = br.readLine().split(" ");

        N = Integer.parseInt(readLine[0]);
        M = Integer.parseInt(readLine[1]);

        int[][] board = new int[N][M];
        int[][] maxVisited = new int[N][M];
        size = N*M;

        for (int i = 0; i < N; i++) {
            String read = br.readLine();
            for (int j = 0; j < M; j++) {
                if (read.charAt(j) == 'H') {
                    board[i][j] = 0;
                }
                else board[i][j] = Integer.parseInt(String.valueOf(read.charAt(j)));
            }
        }

        //System.out.println(maxVisited[0][0]);
        dfs(board, maxVisited, 0, 0, 1);
        if (isLoop) {
            System.out.println(-1);
        }
        else System.out.println(MaxMove);
    }

    //
    static void dfs(int[][] arr, int[][] visited, int x, int y, int n) {
        // n 이 maxVisited 보다 클 때만 값 갱신하고 dfs 진행
        if (n > size) {
            isLoop = true;
            return;
        }
        if (visited[x][y] < n) {
            visited[x][y] = n;
            if (MaxMove < n) MaxMove = n;

            int distance = arr[x][y];
            // 탐색 위치가 배열 범위 안이고, 구멍이 아니라면 탐색을 진행한다.
            if (x+distance < N && arr[x+distance][y] > 0) {
                dfs(arr, visited, x+distance, y, n+1);
            }
            if (0 <= x-distance && arr[x-distance][y] > 0) {
                dfs(arr, visited, x - distance, y, n+1);
            }
            if (y+distance < M && arr[x][y+distance] > 0) {
                dfs(arr, visited, x, y+distance, n+1);
            }
            if (0 <= y-distance && arr[x][y-distance] > 0) {
                dfs(arr, visited, x, y-distance, n+1);
            }
        }
    }


}