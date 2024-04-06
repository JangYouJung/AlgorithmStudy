import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] numbers;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[ N + 1 ];
        tree = new int[getTreeSize()];

        for( int i = 0; i < N; i++ ){
            numbers[i+1] = Integer.parseInt(bf.readLine());
        }
        
        init( 1, N, 1);
        
        for( int i = 0; i < M; i++ ){
            st = new StringTokenizer(bf.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            System.out.println( getMin(1, N, 1, from, to ) );
        }

    }

    // 세그먼트 트리 사이즈 구하기
    static int getTreeSize(){
        int h = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
        return (int)Math.pow(2,h);
    }

    // 세그먼트 트리 초기화
    static int init( int start, int end, int node ){
        if( start == end ) return tree[node] = numbers[start];

        int mid = ( start + end ) / 2;

        return tree[node] = Math.min( init(start, mid, node * 2), init( mid+1, end, node * 2 + 1));

    }

    // [ left ~ to ] 구간 최솟값 구하기
    static int getMin(int start, int end, int node, int from, int to ){
        // 범위 밖에 있는 경우
        if( from > end || to < start ) return Integer.MAX_VALUE;

        // 범위 안에 있는 경우
        if( from <= start && to >= end ) return tree[node];

        // 두 경우에 해당하지 않는다면 두 부분으로 나눠서 최솟값 구하기
        int mid = ( start + end ) / 2;
        return Math.min( getMin( start, mid, node * 2, from, to ), getMin( mid + 1, end, node * 2 + 1, from , to) );
    }
    
}
