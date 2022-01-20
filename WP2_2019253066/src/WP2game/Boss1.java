package WP2game;

import java.util.Random;

public class Boss1{//모두 동일한 보스 능력치
    static Random random = new Random();
    static int boss_x;
    static int boss_y;
    static int Health = 5;
    static boolean isbossdied = false;

    public static void randomBoss(){//보스 위치를 랜덤으로 설정함
        boss_x=150+10*random.nextInt(10);
        boss_y=50+10*random.nextInt(10);
    }
    //체력 다운
    public static void healthdown(int damage){
        Health -= damage;
    }
    //체력 업
    public static void healthup(int heal){
        Health += heal;
    }
    //보스 x좌표
    public static int getBoss_x() {
        return boss_x;
    }
    //보스 y좌표
    public static int getBoss_y() {
        return boss_y;
    }
}
