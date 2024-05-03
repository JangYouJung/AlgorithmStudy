import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static int N;
    public static int M;
    public static ArrayList<Integer> cranes;
    public static ArrayList<Integer> boxes;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cranes = new ArrayList<>();
        String[] line = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            cranes.add(Integer.parseInt(line[i]));
        }

        M = Integer.parseInt(br.readLine());
        String[] lines = br.readLine().split(" ");
        boxes = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(lines[i]));
        }

        //크레인 내림차순 정렬
        Collections.sort(cranes, Collections.reverseOrder());
        //박스 내림차순 정렬
        Collections.sort(boxes, Collections.reverseOrder());

        int result = 0;
        int boxIdx;

        //각 박스를 순서대로 가장 높은 무게 옮길 수 있는 크레인부터 배치
        while(!boxes.isEmpty()) {
            result += 1;
            for(Integer crane: cranes) {
                boxIdx = 0;
                if(boxes.isEmpty()) {
                    break;
                }
                if(boxes.get(boxIdx) <= crane) {
                    boxes.remove(boxIdx);
                }
                else if(crane == cranes.get(0)) {
                    boxes = new ArrayList<>();
                    result = -1;
                    break;
                }
                else {
                    while(boxes.get(boxIdx) > crane) {
                        boxIdx += 1;
                        if(boxIdx == boxes.size()) break;
                    }

                    if(boxIdx < boxes.size() && boxes.get(boxIdx) <= crane) {
                        boxes.remove(boxIdx);
                    }

                }
            }
        }

        System.out.println(result);
    }
}