import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][][] box;
    static boolean[][][] visited;
    static int M, N, H;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dn = { 0, 0, 0, 0, 1, -1 };
    static int[] dm = { 0, 0, 1, -1, 0, 0 };
    static int[] dh = { 1, -1, 0, 0, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(bf.readLine(), " ");

        M = Integer.parseInt(line.nextToken());
        N = Integer.parseInt(line.nextToken());
        H = Integer.parseInt(line.nextToken());
        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        // [1] 입력 받기
        for( int h = 0; h < H; h++ ){
            for( int n = 0; n < N; n++ ){
                line = new StringTokenizer(bf.readLine(), " ");
                for( int m = 0; m < M; m++ ){
                    int tomato = Integer.parseInt(line.nextToken());
                    box[h][n][m] = tomato;
                }
            }
        }

        // [2] 익은 토마토 찾고 bfs 실행
        for( int h = 0; h < H; h++ ){
            for( int n = 0; n < N; n++ ){
                for( int m = 0; m < M; m++ ){
                    if( box[h][n][m] == 1 && !visited[h][n][m] ){
                        queue.offer( new int[]{ m, n, h } );
                        visited[h][n][m] = true;
                    }
                }
            }
        }

        bfs();

        // [3] max 값 구하기
        int max = 0;
        for( int h = 0; h < H; h++ ){
            for( int n = 0; n < N; n++ ){
                for( int m = 0; m < M; m++ ){
                    if( box[h][n][m] == 0 ) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                    else if( box[h][n][m] > max ){
                        max = box[h][n][m];
                    }
                }
            }
        }
        System.out.println( max - 1 );

    }

    static void bfs(){

        // [2]-2 bfs 실행
        while(!queue.isEmpty()){
            int[] location = queue.poll();

            for( int i = 0; i < 6; i++ ){
                int nextM = location[0] + dm[i];
                int nextN = location[1] + dn[i];
                int nextH = location[2] + dh[i];

                if( nextM < 0 || nextM >= M || nextN < 0 ||
                        nextN >= N || nextH < 0 || nextH >= H ||
                        visited[nextH][nextN][nextM]) continue;

                if( box[nextH][nextN][nextM] == 0 ){
                    box[nextH][nextN][nextM] = box[location[2]][location[1]][location[0]] + 1;
                    visited[nextH][nextN][nextM] = true;
                    queue.offer( new int[]{ nextM, nextN, nextH });
                }
            }
        }
    }
}
