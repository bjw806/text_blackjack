package WP2game;

import javax.swing.*;
import java.awt.*;

public class Mapping extends JPanel {

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);  //JPanel의 Paint

        graphics.setColor(Color.darkGray);
        graphics.fillRect(0, 0, 720, 300); //맵 크기 지정

        graphics.setColor(Color.white);
        for(int k=0;k<100;k++) {//랜덤으로 100번 지우기
            graphics.fillRect(MainGame.del_x[k], MainGame.del_y[k], MainGame.del_width[k], MainGame.del_height[k]);
        }

        //필수적으로 지워야하는 곳
        graphics.fillRect(0,50,720,30);
        graphics.fillRect(0,200,720,30);
        graphics.fillRect(50,0,40,300);
        graphics.fillRect(500,0,30,300);
        //각 보스들의 주변 3x3을 비움. 보스는 빨강색으로 색칠
        graphics.setColor(Color.white);
        graphics.fillRect(Boss1.getBoss_x()-20, Boss1.getBoss_y()-20, 50, 50);
        graphics.setColor(Color.red);
        graphics.fillRect(Boss1.getBoss_x(), Boss1.getBoss_y(), 10, 10);

        graphics.setColor(Color.white);
        graphics.fillRect(Boss2.getBoss_x()-20, Boss2.getBoss_y()-20, 50, 50);
        graphics.setColor(Color.red);
        graphics.fillRect(Boss2.getBoss_x(), Boss2.getBoss_y(), 10, 10);

        graphics.setColor(Color.white);
        graphics.fillRect(Boss3.getBoss_x()-20, Boss3.getBoss_y()-20, 50, 50);
        graphics.setColor(Color.red);
        graphics.fillRect(Boss3.getBoss_x(), Boss3.getBoss_y(), 10, 10);

        graphics.setColor(Color.white);
        graphics.fillRect(Boss4.getBoss_x()-20, Boss4.getBoss_y()-20, 50, 50);
        graphics.setColor(Color.red);
        graphics.fillRect(Boss4.getBoss_x(), Boss4.getBoss_y(), 10, 10);
        ///////////////////보물////////////////
        //보물 주변 3x3 제거
        graphics.setColor(Color.white);
        graphics.fillRect(Treasure.treasure_x-20, Treasure.treasure_y-20, 50, 50);
        graphics.fillRect(Treasure.treasure_x, Treasure.treasure_y-20, 20, 100);
        graphics.setColor(Color.yellow);//보물 노랑색
        graphics.fillRect(Treasure.treasure_x, Treasure.treasure_y, 10, 10);
        ////////////////////플레이어/////////////////////////////

        graphics.setColor(Color.green);//플레이어 초록
        graphics.fillRect(Player.getPlayer_x(), Player.getPlayer_y(), 10, 10);
        ////////////////////////////////////////////////////
        graphics.setColor(Color.green);
        graphics.drawLine(0, 300, 720, 300);//맵 끝에 선긋기
        graphics.setColor(Color.lightGray);//맵 밑에 채우기
        graphics.fillRect(0,300,720,180);
    }
}