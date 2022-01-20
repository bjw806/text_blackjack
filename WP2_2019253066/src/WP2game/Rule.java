package WP2game;

public class Rule {
    private static final int BLACKJACK_NUM = 21;//블랙잭 =21
    //승자 리턴
    public String returnWinner(int dealerSum, int gamerSum) {

        int dealerMinus = BLACKJACK_NUM - dealerSum;
        int gamerMinus = BLACKJACK_NUM - gamerSum;

        if(dealerMinus > gamerMinus) {
            return "Player 승리";
        }else if(dealerMinus == gamerMinus) {
            return "무승부(PUSH)";
        }else {
            return "Boss 승리";
        }
    }

    // 버스트 확인
    public boolean isBust(BJPlayer bjplayer, int sum) {
        String name = bjplayer.getName();
        if(sum > 21) {
            //System.out.println(name + " Bust");
            return true;
        }else {
            return false;
        }
    }
    //버스트인지 리턴
    public String returnBust(BJPlayer bjplayer, int sum) {
        String name = bjplayer.getName();
        return name + " Bust";
    }
}
