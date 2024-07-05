import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        Stack<Integer> stack = new Stack<>();

        int[] rest = new int[progresses.length];
        for(int i = 0; i < progresses.length; i++) {
            rest[i] = (int)Math.ceil((float)(100 - progresses[i]) / (float)speeds[i]);
        }

        List<Integer> result = new ArrayList<>();

        System.out.println(rest[0]);
        stack.push(rest[0]);
        result.add(1);
        for(int i = 1; i < rest.length; i++) {
            if(!stack.empty()) {
                if(stack.peek() < rest[i]) {
                    stack.push(rest[i]);
                    result.add(1);
                }
                else {
                    result.set(result.size()-1, result.get(result.size()-1)+1);
                }
            }
        }

        int[] answer = result.stream().mapToInt(i->i).toArray();
        return answer;
    }
}