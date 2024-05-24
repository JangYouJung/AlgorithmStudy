import java.io.*;

import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String crypt = st.nextToken();
        String key = st.nextToken();
        int rotate = Integer.parseInt(st.nextToken());
        String message = st.nextToken();

        if( crypt.equals("encrypt")) {
            System.out.println(encrypt(key, rotate, message));
        }
        else System.out.println(decrypt(key, rotate, message));

    }

    static String encrypt( String key, int rotate, String message ){

        int[] messageToNum = new int[message.length()];

        // 1단계: 메시지와 키 숫자로 변환
        for( int i = 0; i < message.length(); i++ ){

            int sum = (key.charAt(i) - 'a') + (message.charAt(i) - 'a');
            if( sum > 25 ) sum -= 26;

            messageToNum[i] = sum;
        }

        // 2단계: rotate 만큼 회전
        if(rotate>0) messageToNum = leftRotatation( messageToNum, rotate);
        else messageToNum = rightRotatation( messageToNum, Math.abs(rotate));

        // 3단계: 숫자를 알파벳으로 바꾸기
        StringBuilder builder = new StringBuilder();
        for( int i : messageToNum ){
            builder.append((char)( i + 'a'));
        }

        return builder.toString();

    }

    static String decrypt( String key, int rotate, String message ){
        int[] messageToNum = new int[message.length()];

        // 복호화 1단계: 알파벳을 숫자로 변환
        for( int i = 0; i < message.length(); i++ ){
            messageToNum[i] = message.charAt(i) - 'a';
        }

        // 2단계: rotate 만큼 반대로 회전
        if(rotate>0) messageToNum = rightRotatation( messageToNum, rotate);
        else messageToNum = leftRotatation( messageToNum, Math.abs(rotate));

        // 3단계: 메시지에서 비밀키 빼기
        for( int i = 0; i < message.length(); i++ ){
            int minus =  messageToNum[i] - (key.charAt(i) - 'a');
            if( minus < 0 ) minus += 26;
            System.out.println( minus );
            messageToNum[i] = minus;
        }

        // 4단계: 숫자를 알파벳으로 바꾸기
        StringBuilder builder = new StringBuilder();
        for( int i : messageToNum ){
            builder.append((char)( i + 'a'));
        }

        return builder.toString();


    }

    static int[] leftRotatation( int[] original, int rotate ){
        while( rotate > 0 ){
            rotate--;

            int tmp = original[0];
            for( int i = 0; i < original.length - 1 ; i++ ){
                original[i] = original[i+1];
            }
            original[original.length-1] = tmp;
        }

        return original;
    }

    static int[] rightRotatation( int[] original, int rotate ){
        while( rotate > 0 ){
            rotate--;

            int tmp = original[original.length-1];

            for( int i = original.length-1 ; i > 0 ; i-- ){
                original[i] = original[i-1];
            }
            original[0] = tmp;
        }

        return original;
    }

}

// encrypt secretword 3 helloworld
// cspkfcgzin

//decrypt secretword 3 cspkfcgzin
// helloworld
