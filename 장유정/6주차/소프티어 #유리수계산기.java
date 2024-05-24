
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(),"[,] ");
        int firstSon = Integer.parseInt(st.nextToken());
        int firstMom = Integer.parseInt(st.nextToken());
        String calculate = st.nextToken();
        int secondSon = Integer.parseInt(st.nextToken());
        int secondMom = Integer.parseInt(st.nextToken());

        if( firstMom==0 || secondMom==0 ){
            System.out.println("ERR");
            return;
        }

        int[] answer = new int[2];
        switch (calculate){
            case "+":
                answer = plus(firstSon,firstMom,secondSon, secondMom);
                break;

            case "-":
                answer = minus(firstSon,firstMom,secondSon, secondMom);
                break;

            case "*":
                answer = multi(firstSon,firstMom,secondSon, secondMom);
                break;

            case "/":
                answer = divide(firstSon,firstMom,secondSon, secondMom);
                break;
        }

        // 약분하기
        if( answer[0]%answer[1] == 0 ) System.out.println(answer[0]/answer[1] );
        else{
            System.out.println(Arrays.toString(answer));
        }

    }

    static int[] plus(int firstSon, int firstMom, int SecondSon, int SecondMom ){
        int[] answer= new int[2];
        answer[1] = firstMom * SecondMom;
        answer[0] = firstSon*SecondMom + SecondSon*firstMom;

        return answer;
    }
    static int[] minus(int firstSon, int firstMom, int SecondSon, int SecondMom ){
        int[] answer= new int[2];
        answer[1] = firstMom * SecondMom;
        answer[0] = firstSon*SecondMom - SecondSon*firstMom;
        return answer;
    }
    static int[] multi(int firstSon, int firstMom, int SecondSon, int SecondMom ){
        int[] answer= new int[2];

        answer[0] = firstSon * SecondSon;
        answer[1] = firstMom * SecondMom;

        return answer;
    }
    static int[] divide(int firstSon, int firstMom, int SecondSon, int SecondMom ){
        int[] answer= new int[2];

        answer[0] = firstSon * SecondMom;
        answer[1] = firstMom * SecondSon;

        return answer;
    }
}
