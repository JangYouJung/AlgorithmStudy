import java.util.*;
class Solution {
    static int[] answer = new int[11];
    static int[] apeach = new int[11];
    static int[] ryan = new int[11];
    static int max_score = 0;
    static int N;
    public int[] solution(int n, int[] info) {

        apeach = info;
        N = n;
        backtracking(0,0);
        return max_score == 0 ? new int[]{-1} : answer;

    }

    public void backtracking(int count, int start){

        if( count == N ){
            int apeach_score = 0;
            int ryan_score = 0;

            // 라이언과 어피치 각자 점수 계산
            for( int i = 0; i < 11; i++ ){
                if( ryan[i] == 0 && apeach[i] == 0 ) continue;
                if( ryan[i] > apeach[i] ){
                    // 라이언이 더 많이 맞췄으면 라이언의 점수 높이기
                    ryan_score += ( 10 - i );
                }
                else if( ryan[i] <= apeach[i] ){
                    // 어피치가 더 많이 맞췄으면 어파치 점수 높이기
                    apeach_score += ( 10 - i );
                }
            }

            // 라이언이 최대의 점수차로 이기는 경우 찾기
            if(ryan_score - apeach_score > max_score){
                System.out.println("라이언: " + ryan_score + Arrays.toString(ryan));
                max_score = ryan_score - apeach_score;
                answer = ryan.clone();
            }
            else if( ryan_score - apeach_score == max_score){
                // 점수차가 최대 점수차와 같은 경우 더 낮은 점수를 많이 맞힌 경우를 answer에 담기
                for( int i = 10; i > -1 ; i-- ){
                    
                    // 현재 구한 라이언 배열이 더 낮은 점수를 많이 맞힌 경우
                    if( answer[i] < ryan[i] ){
                        answer = ryan.clone();
                        System.out.println("갱신 발생"+ ryan_score + Arrays.toString(ryan));
                        break;
                    }
                    
                    // 예외 처리 추가: 현재 정답 배열이 더 낮은 점수를 많이 맞힌 경우
                    if( answer[i] > ryan[i] ){
                        break;
                    }
                }
            }

            return;

        }


        for( int i = start; i < 11; i++ ){
            if(apeach[i] < ryan[i]) continue;
            ryan[i] ++;
            backtracking( count + 1, i);
            ryan[i]--;
        }

    }
}