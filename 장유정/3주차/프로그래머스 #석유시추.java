import java.util.*;
class Solution {
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static int[] oilArea; // 각 열 별로 얻을 수 있는 석유량
    static int MAX = Integer.MIN_VALUE;
    public int solution(int[][] land) {

        visited = new boolean[land.length][land[0].length];
        oilArea = new int[land[0].length];

        for( int i = 0; i < land.length; i++ ){
            for( int j = 0; j < land[0].length; j++ ){
                if( land[i][j] == 1 && !visited[i][j] ){
                    bfs( i, j, land );
                }
            }
        }

        for( int column : oilArea){
            if( column > MAX ) MAX = column;
        }

        return MAX;
    }

    public void bfs( int x, int y, int[][] land ){
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> columns = new HashSet<>(); // 현재 찾아낸 석유 덩어리가 차지하고 있는 세로(열) 좌표들 
        int totalOil = 1;

        visited[x][y] = true;
        queue.offer( new int[]{ x, y });

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            columns.add(current[1]); // 현재 석유 위치의 '세로'좌표 columns에 삽입

            for( int i = 0; i < 4; i++ ){
                int newX = current[0] + dx[i];
                int newY = current[1] + dy[i];

                if( newX < 0 || newY < 0 || newX > land.length - 1 ||
                        newY > land[0].length - 1 || visited[newX][newY] ) continue;

                if( land[newX][newY] == 1 ){ // 석유가 있는 땅이라면 
                    totalOil++; // 석유 덩어리 ++ 
                    visited[newX][newY] = true; // 방문 여부 표시
                    queue.offer( new int[]{ newX, newY });
                }
            }
        }

        for( int column : columns){
            oilArea[column] += totalOil;
        }

    }
}