import java.util.Arrays;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long distance = 0;

        for( int i = n-1 ; i > -1; ){ // 택배 배달
            if( deliveries[i] <= 0 && pickups[i] <= 0 ) {
                i--;
                continue;
            }

            distance += ( 2L * ( i + 1 ) );

            // [1] 배달하기
            int deliveryCap = cap;
            for( int j = i; j > -1; j-- ){
                if(deliveryCap<=0) break;
                if(deliveries[j] > 0){
                    int tmp = deliveryCap;
                    deliveryCap -= deliveries[j];
                    deliveries[j] -= tmp;
                }
            }

            // [2] 픽업하기
            int pickupCap = cap;
            for( int j = i; j > -1; j-- ){
                if(pickupCap<=0) break;
                if( pickups[j] > 0  ){
                    int tmp = pickupCap;
                    pickupCap -= pickups[j];
                    pickups[j] -= tmp;
                }
            }

            System.out.println("배달: " + Arrays.toString(deliveries));
            System.out.println("픽업: " + Arrays.toString(pickups));
            System.out.println("이번에 간 거리: "+ (2L * ( i + 1 )));
        }

        return distance;
    }

}