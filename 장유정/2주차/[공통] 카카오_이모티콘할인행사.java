import java.util.*;
class Solution {
    static int[] rate = { 10, 20, 30 ,40 };
    static int MAX_JOIN = Integer.MIN_VALUE;
    static int MAX_SALES = Integer.MIN_VALUE;

    public int[] solution(int[][] users, int[] emoticons) {

        switch ( emoticons.length ){
            case 1: one(users, emoticons);
                break;
            case 2: two(users, emoticons);
                break;
            case 3: three(users, emoticons);
                break;
            case 4: four(users, emoticons);
                break;
            case 5: five(users, emoticons);
                break;
            case 6: six(users, emoticons);
                break;
            case 7: seven(users,emoticons);
                break;
        }

        return new int[] { MAX_JOIN, MAX_SALES };
    }

    public void getResult( int[][] users, int[] emoticons, int[] rates ){

        int totalSales = 0;
        int totalCnt = 0;

        for( int i = 0; i < users.length; i++ ){
            int sale_by_person = 0;
            for( int j = 0; j < emoticons.length; j++ ){
                if( rates[j] >= users[i][0] ){
                    sale_by_person += ( emoticons[j] - emoticons[j]*rates[j]/100 );
                }
            }
            if( sale_by_person >= users[i][1]){
                totalCnt ++;
            }
            else totalSales += sale_by_person;
        }

        if( MAX_JOIN == totalCnt && MAX_SALES < totalSales ){
            MAX_SALES = totalSales;
        }
        else if( MAX_JOIN < totalCnt ){
            MAX_JOIN = totalCnt;
            MAX_SALES = totalSales;
        }

    }

    public void one(int[][] users, int[] emoticons){
        for( int i = 0; i < 4; i++ ){
            getResult( users, emoticons, new int[] { rate[i] });
        }
    }

    public void two(int[][] users, int[] emoticons){
        for( int i = 0; i< 4; i++ ){
            for( int j = 0; j < 4; j++ ){
                getResult( users, emoticons, new int[] { rate[i], rate[j] });
            }
        }
    }

    public void three(int[][] users, int[] emoticons){
        for( int i = 0; i< 4; i++ ){
            for( int j = 0; j < 4; j++ ){
                for( int k = 0; k < 4; k++){
                    getResult(users, emoticons, new int[] { rate[i], rate[j], rate[k] });
                }
            }
        }
    }

    public void four(int[][] users, int[] emoticons){
        for( int i = 0; i < 4; i++ ){
            for( int j = 0; j < 4; j++ ){
                for( int k = 0; k < 4; k++){
                    for( int l = 0; l < 4; l++){
                        getResult(users, emoticons, new int[] { rate[i], rate[j], rate[k], rate[l] });
                    }
                }
            }
        }
    }

    public void five(int[][] users, int[] emoticons){
        for( int i = 0; i < 4; i++ ){
            for( int j = 0; j < 4; j++ ){
                for( int k = 0; k < 4; k++){
                    for( int l = 0; l < 4; l++){
                        for( int m = 0; m < 4; m++ ){
                            getResult(users, emoticons, new int[] { rate[i], rate[j], rate[k], rate[l], rate[m] });
                        }
                    }
                }
            }
        }
    }

    public void six(int[][] users, int[] emoticons){
        for( int i = 0; i < 4; i++ ){
            for( int j = 0; j < 4; j++ ){
                for( int k = 0; k < 4; k++){
                    for( int l = 0; l < 4; l++){
                        for( int m = 0; m < 4; m++ ){
                            for( int n = 0; n < 4; n++ ){
                                getResult(users, emoticons, new int[] { rate[i], rate[j], rate[k], rate[l], rate[m], rate[n] });
                            }
                        }
                    }
                }
            }
        }
    }
    public void seven(int[][] users, int[] emoticons){
        for( int i = 0; i < 4; i++ ){
            for( int j = 0; j < 4; j++ ){
                for( int k = 0; k < 4; k++){
                    for( int l = 0; l < 4; l++){
                        for( int m = 0; m < 4; m++ ){
                            for( int n = 0; n < 4; n++ ){
                                for( int o = 0; o < 4; o++ ){
                                    getResult(users, emoticons, new int[] { rate[i], rate[j], rate[k], rate[l], rate[m], rate[n], rate[o] });
                                }
                            }
                        }
                    }
                }
            }
        }
    }



}