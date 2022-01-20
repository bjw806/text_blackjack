package WP2game;

import java.util.Stack;

public class Gamer implements BJPlayer {

    private Stack<Card> deck;//카드 스택
    private static final String NAME = "Player";//이름 지정

    public Gamer() {
        deck = new Stack<Card>();
    }//생성자

    public String getName() {
        return NAME;
    }//이름 리턴

    // 카드 받음
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
    // 카드 오픈
    public String returnCards(){//카드정보 리턴
        StringBuilder sb = new StringBuilder();
        sb.append("Player's Card : ");
        for(Card card : deck) {
            sb.append(card.toString());
            sb.append(" ");
        }
        sb.append(" 총합 : " + this.getSum());
        return sb.toString();
    }

    public boolean isCardA(){//카드에 A가 있으면.true
        String sb = returnCards();
        if(sb.contains("A")){
            return true;
        }
        return false;
    }

    public boolean isBlackjack(){//블랙잭 판별
        int A = 0;
        int Q = 0;
        int K = 0;
        int J = 0;
        int n10 = 0;
        //처음 뽑은 카드에 A와 JQK10 중 아무거나 있다면,
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
            return true;//참 리턴
        }
        return false;//없으면 거짓 리턴
    }

    @Override
    public void printCards() {//카드 출력
        StringBuilder sb = new StringBuilder();
        sb.append("Player's Card : ");

        for(Card card : deck) {
            sb.append(card.toString());
            sb.append(" ");
        }

        System.out.println(sb.toString() + " 총합 : " + this.getSum());

    }

    @Override
    public int getSum() {//카드 총합 리턴
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
}