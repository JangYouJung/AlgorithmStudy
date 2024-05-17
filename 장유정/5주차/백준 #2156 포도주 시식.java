import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] wine;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        wine = new int[N];
        dp = new int[N];

        for( int i = 0; i < N; i++ ){
            wine[i] = Integer.parseInt(bf.readLine());
        }

        dp[0] = wine[0];

        if( N > 1 ) dp[1] = wine[0] + wine[1];
        if( N > 2 ) dp[2] = Math.max( dp[1], Math.max( wine[0] + wine[2], wine[1] + wine[2]));

        for( int i = 0; i < N; i++ ){
            
            
            dp[i] = Math.max( dp[i-1], Math.max( dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i] ));
        }

        System.out.println(dp[N-1]);

    }// end of main

}// end of class
