package WP2game;

import java.util.Random;

public class Treasure{
    //보물 위치 랜덤 생성
    static Random random = new Random();
    static int treasure_x;
    static int treasure_y;
    static boolean gettreasure = false;//보물 획득 여부

    public static void randomTreasure_xy(){
        treasure_x=550+10*random.nextInt(10);
        treasure_y=30+30*random.nextInt(7);
    }

    public static int getTreasure_x() {
        return treasure_x;
    }

    public static int getTreasure_y() {
        return treasure_y;
    }
}
