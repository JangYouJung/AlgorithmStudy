import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static int N;
    public static int K;
    static ArrayList<Integer> heights;
    static ArrayList<int []> diff;
    static ArrayList<int []> heightDiff;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);

        heights = new ArrayList<>();
        String[] heightLine = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            heights.add(Integer.parseInt(heightLine[i]));
        }

        diff = new ArrayList<>();
        //이전 원소와의 차 계산
        for(int i = 0; i < N - 1; i++) {
            diff.add(new int[] {heights.get(i + 1) - heights.get(i), i});
        }

        //이전 원소와의 차 기준으로 역순 정렬
        Collections.sort(diff, (l1, l2) -> l2[0] - l1[0]);

        heightDiff = new ArrayList<>();
        for (int i = 0; i < K - 1; i++) {
            heightDiff.add(diff.get(i));
        }

        // 차 가장 큰 K개의 위치 인덱스 정렬
        Collections.sort(heightDiff, (l1, l2) -> l1[1] - l2[1]);

        int prevIdx = 0;
        int result = 0;
        for(int[] hd : heightDiff) {
            result += (heights.get(hd[1]) - heights.get(prevIdx));
            prevIdx = hd[1] + 1;
        }

        result += (heights.get(heights.size() - 1) - heights.get(prevIdx));

        System.out.println(result);
    }
}