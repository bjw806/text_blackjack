package WP2game;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {

    private static Stack<Card> deck;//카드 덱 스택
    private static String[] PATTERNS = {"♥","♠","♦","♣" };//패턴 종류
    private static int CARD_CNT = 13;//숫자 개수

    //카드 덱 생성(셔플)
    public CardDeck() {
        deck = this.generatedDeck();
        Collections.shuffle(deck);
    }
    //덱 지우고 새로운 덱 생성
    public void newCardDeck(){
        deckclear();
        deck = this.generatedDeck();
        Collections.shuffle(deck);
    }
    //카드 생성. 문자와 숫자 부여
    public Stack<Card> generatedDeck() {
        Stack<Card> deck = new Stack<Card>();
        for(String pattern : PATTERNS) {
            for(int i = 1; i <= CARD_CNT; i++) {
                String number = "";
                int point = 0;
                switch(i) {
                    case 1 :
                        number = "A";
                        break;
                    case 11 :
                        number = "J";
                        break;
                    case 12 :
                        number = "Q";
                        break;
                    case 13 :
                        number = "K";
                        break;
                    default :
                        number = Integer.toString(i);
                        break;
                }
                if(i == 1) {
                    point = 1;
                }else if(i >= 11) {
                    point = 10;
                }else {
                    point = i;
                }
                Card card = new Card(pattern, number, point);
                deck.add(card);
            }
        }
        return deck;//덱 리턴
    }
    //카드 나누기
    public Card splitCard() {
        return deck.pop();
    }
    //덱
    public void deckclear(){
        deck.clear();
    }

    @Override
    public String toString() {//string으로 변환
        StringBuilder sb = new StringBuilder();
        for(Card card : deck){
            sb.append(card.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public boolean isNoDeck(){//덱이 없으면 true반환
        if(deck.empty()){//카드 덱을 다 쓰면, 새 게임 시작
            return true;
        }
        return false;
    }
}