import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] lab;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int maxSafeZone = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(line.nextToken());
        M = Integer.parseInt(line.nextToken());
        lab = new int[N][M];

        for( int n = 0; n < N; n++ ){
            line = new StringTokenizer(bf.readLine(), " ");

            for( int m = 0; m < M; m++ ){
                int input = Integer.parseInt(line.nextToken());
                lab[n][m] = input;
            }
        }

        setWalls( 0 );
        System.out.println(maxSafeZone);
    }

    public static void setWalls( int wallsCnt ){ // dfs

        if(wallsCnt == 3){
            int[][] copyLab = new int[N][M];
            for( int i = 0; i < N; i++ ){
                copyLab[i] = lab[i].clone();
            }
            virus(copyLab);
            return;
        }

        for( int i = 0; i < N; i++ ){
            for( int j = 0; j < M; j++ ){
                if( lab[i][j] == 0 ){
                    lab[i][j] = 1;
                    setWalls( wallsCnt + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    static void virus ( int[][] copyLab ){  // bfs

        Queue<int[]> queue = new LinkedList<>();
        for( int i = 0; i < N; i++ ){
            for( int j = 0; j < M; j++ ){

                if( copyLab[i][j] == 2 ){
                    queue.offer( new int[]{ i, j });
                }
            }
        }

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            for( int i = 0; i < 4; i++ ){
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];

                if( nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > M - 1 ) continue;

                if( copyLab[nextX][nextY] == 0 ){
                    copyLab[nextX][nextY] = 2;
                    queue.offer( new int[]{ nextX, nextY } );
                }
            }
        }

        findSafeZone( copyLab );

    }

    static void findSafeZone(int[][] copyLab){

        int safeZone = 0;
        for( int i = 0; i < N; i++ ){
            for( int j = 0; j < M; j++ ){
                if( copyLab[i][j] == 0 ){
                    safeZone++;
                }
            }
        }

        maxSafeZone = Math.max(maxSafeZone, safeZone);
    }
}
