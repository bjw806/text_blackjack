package WP2game;

import java.util.Random;

public class Boss4{
    static Random random = new Random();
    static int boss_x;
    static int boss_y;
    static int Health = 5;
    static boolean isbossdied = false;

    public static void randomBoss(){
        boss_x=450+10*random.nextInt(10);
        boss_y=150+10*random.nextInt(10);
    }

    public static void healthdown(int damage){
        Health -= damage;
    }

    public static void healthup(int heal){
        Health += heal;
    }

    public static int getBoss_x() {
        return boss_x;
    }

    public static int getBoss_y() {
        return boss_y;
    }
}
