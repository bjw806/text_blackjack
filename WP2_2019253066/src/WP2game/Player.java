package WP2game;

import java.util.Random;

public class Player{
    static Random random = new Random();

    static int player_x;
    static int player_y;
    static int original_x;//처음 생성된 위치 저장
    static int original_y;
    static int Health = 5;

    public static void randomPlayer_xy(){
        player_x = 50+10*random.nextInt(4);
        player_y = 50+30*random.nextInt(8);
    }
    //플레이어 체력 up/down
    public static void healthdown(int damage){
        Health -= damage;
    }

    public static void healthup(int heal){
        Health += heal;
    }
    //플레이어 위치 반환
    public static int getPlayer_x() {
        return player_x;
    }

    public static int getPlayer_y() {
        return player_y;
    }
    //플레이어 이동
    public static void move_EAST(){
        player_x += 10;
    }

    public static void move_WEST(){
        player_x -= 10;
    }

    public static void move_SOUTH(){
        player_y += 10;
    }

    public static void move_NORTH(){
        player_y -= 10;
    }

}


