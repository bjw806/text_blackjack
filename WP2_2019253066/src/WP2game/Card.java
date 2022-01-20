package WP2game;

public class Card {
    private String pattern;//패턴
    private String denomination;//명칭
    private int point;//숫자

    public Card(String pattern, String denomination, int point) {
        this.pattern = pattern;
        this.denomination = denomination;
        this.point = point;
    }

    @Override
    public String toString() {
        return "(" + pattern + ", " + denomination + ")";
    }
    //패턴 관리
    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    public String getDenomination() {
        return denomination;
    }
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    //숫자 관리
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}