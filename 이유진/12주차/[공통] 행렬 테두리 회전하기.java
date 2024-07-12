import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int [rows][columns];

        int count = 1;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = count;
                count += 1;
            }
        }

        List<Integer> nums = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Queue<int[]> coordinateQ = new LinkedList<>();

        // 회전 수행 횟수
        int qNum = queries.length;

        // 회전 수행 횟수만큼 수행
        for (int i = 0; i < qNum; i++) {
            queue.clear();
            coordinateQ.clear();
            int x1 = queries[i][0]-1;
            int y1 = queries[i][1]-1;
            int x2 = queries[i][2]-1;
            int y2 = queries[i][3]-1;

            int nowX = x1;
            int nowY = y1;

            while(nowX <= x2 && nowY <= y2) {
                // 회전 1번 줄
                if (x1 == nowX && nowY < y2) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowY += 1;
                }
                // 회전 1번 줄과 2번 줄의 교차 지점
                else if (x1 == nowX && nowY == y2) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowX += 1;
                }
                // 회전 2번 줄
                else if (nowX < x2 && nowY == y2) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowX += 1;
                }
                // 회전 2번 줄과 3번 줄의 교차 지점
                else if (nowX == x2 && nowY == y2) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowY -= 1;
                }
                // 회전 3번 줄
                else if (nowX == x2 && nowY > y1) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowY -= 1;
                }
                // 회전 3번 줄과 4번 줄의 교차 지점
                else if (nowX == x2 && nowY == y1) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowX -= 1;

                    if (nowX == x1) break;
                }
                // 회전 4번 줄
                else if (nowX > x1 && nowY == y1) {
                    queue.offer(arr[nowX][nowY]);
                    coordinateQ.offer(new int[] {nowX, nowY});
                    nowX -= 1;
                    if(nowX == x1) break;
                }
            }

            nums = new ArrayList<>(queue);
            answer[i] = Collections.min(nums);

            // 시계 방향으로 이동하기 위해 맨 처음 위치의 원소를 큐의 맨 뒤에 넣음
            coordinateQ.offer(coordinateQ.peek());
            coordinateQ.poll();
            while(!queue.isEmpty()) {
                int[] coordinate = coordinateQ.poll();
                int x = coordinate[0];
                int y = coordinate[1];
                // System.out.println(x + " " + y);

                arr[x][y] = queue.poll();
            }

        }

        return answer;
    }
}