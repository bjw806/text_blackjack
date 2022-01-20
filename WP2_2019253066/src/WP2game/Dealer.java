package WP2game;
import java.util.Stack;

public class Dealer implements BJPlayer {//gamer와 동일한 구성

    private Stack<Card> deck;
    private static final String NAME = "Boss";

    public Dealer() {
        deck = new Stack<Card>();
    }

    public String getName() {
        return NAME;
    }

    @Override
    public void getCard(Card card) {
        this.deck.add(card);
    }

    public void resetCard() {
        this.deck.clear();
    }

    public boolean isnocard(){
        return this.deck.empty();
    }

    public String returnCards(){
        StringBuilder sb = new StringBuilder();
        sb.append("Boss's Card : ");

        for(int i = 0; i < deck.size(); i++) {

            Card card = deck.get(i);
            if (i == 0) {
                sb.append("(?) ");
            }else {
                sb.append(card.toString());
                sb.append(" ");
            }

        }
        return sb.toString();
    }

    public String returnCards(String last) {
        StringBuilder sb = new StringBuilder();
        sb.append("Boss's Card : ");
        for(Card card : deck) {
            sb.append(card.toString());
        }

        //System.out.println(sb.toString() + " 총합 : " + this.getSum());
        sb.append(" 총합 : " + this.getSum());
        return sb.toString();
    }

    public boolean isBlackjack(){
        int A = 0;
        int Q = 0;
        int K = 0;
        int J = 0;
        int n10 = 0;
        for(Card card : deck) {
            String cd = card.toString();
            if(cd.contains("A")){
                A++;
            }
        }
        for(Card card : deck) {
            String cd = card.toString();
            if (cd.contains("Q")) {
                Q++;
            }
        }
        for(Card card : deck) {
            String cd = card.toString();
            if(cd.contains("K")){
                K++;
            }
        }
        for(Card card : deck) {
            String cd = card.toString();
            if(cd.contains("J")){
                J++;
            }
        }
        for(Card card : deck) {
            String cd = card.toString();
            if(cd.contains("10")){
                n10++;
            }
        }
        if(A==1 && (Q==1 || J==1 || K==1 || n10==1)){
            return true;
        }
        return false;
    }

    @Override
    public void printCards() {
        StringBuilder sb = new StringBuilder();
        sb.append("Boss's Card : ");

        for(int i = 0; i < deck.size(); i++) {

            Card card = deck.get(i);
            if (i == 0) {
                sb.append("(?) ");
            }else {
                sb.append(card.toString());
                sb.append(" ");
            }

        }

        System.out.println(sb.toString());
    }

    public void printCards(String last) {
        StringBuilder sb = new StringBuilder();
        sb.append("Boss's Card : ");
        for(Card card : deck) {
            sb.append(card.toString());
        }

        System.out.println(sb.toString() + " 총합 : " + this.getSum());
    }

    @Override
    public int getSum() {
        int sum = 0;
        for(Card card : deck) {
            sum += card.getPoint();
            String cd = card.toString();
            if(cd.contains("A")){
                if(MainGame.A1or10==2){
                    sum = sum +10;
                }
            }
        }
        return sum;
    }

    // 합 16 이하인지 확인
    public boolean isLessThan() {
        return this.getSum() <= 16;
    }

    // 합이 16 이하인 경우 이상으로 만들기
    public void checkDealerCards(CardDeck cardDeck) {
        while(isLessThan()) {
            Card card = cardDeck.splitCard();
            this.getCard(card);
        }
    }

}