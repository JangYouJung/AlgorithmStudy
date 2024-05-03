import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static int N;
    public static int K;
    public static ArrayList<Integer> sensors;
    public static ArrayList<Integer> distances;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        String [] line = br.readLine().split(" ");
        sensors = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sensors.add(Integer.parseInt(line[i]));
        }
        //센서 위치 정렬
        Collections.sort(sensors);

        //센서 간 간격과 인덱스 distances 에 저장
        distances = new ArrayList<>();
        for (int i = 0; i < N-1; i++) {
            distances.add(sensors.get(i + 1) - sensors.get(i));
        }

        //distance 기준으로 역순 정렬 (-> 간격 가장 큰 K-1개 선정 위해)
        Collections.sort(distances, Collections.reverseOrder());

        //K-1 개의 간격 빼고 나머지 간격들의 합 계산
        int result = 0;
        for (int i = K-1; i < N - 1; i++) {
            result += distances.get(i);
        }

        System.out.println(result);

    }
}