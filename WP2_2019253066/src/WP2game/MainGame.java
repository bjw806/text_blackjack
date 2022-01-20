package WP2game;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.awt.event.*;
import java.io.IOException;


public class MainGame extends JPanel{
    static JFrame frame = new JFrame("2019253066 복정우");//제목
    //텍스트 출력 영역
    static JTextArea textArea = new JTextArea();
    static JTextArea textArea1 = new JTextArea();
    static JTextArea textArea2 = new JTextArea();
    //스크롤 기능 생성
    static JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    static int bossnumber = 0;//보스 번호.
    static boolean bossbattle = false;//보스전=1
    static boolean endlasturn = false;//마지막 턴인가
    static String whatboss;//보스 번호는? 1,2,3,4
    static int A1or10 = 0;//1=1 or 2=11 0=no setting

    //보스의 능력치
    //체력바 출력
    static JPanel boss1Health = new JPanel();
    static JPanel boss2Health = new JPanel();
    static JPanel boss3Health = new JPanel();
    static JPanel boss4Health = new JPanel();
    //체력 출력
    static JLabel boss1HealthText = new JLabel("Boss: " + String.valueOf(Boss1.Health));
    static JLabel boss2HealthText = new JLabel("Boss: " + String.valueOf(Boss2.Health));
    static JLabel boss3HealthText = new JLabel("Boss: " + String.valueOf(Boss3.Health));
    static JLabel boss4HealthText = new JLabel("Boss: " + String.valueOf(Boss4.Health));
    //어택 데미지 출력
    static JLabel boss1attack = new JLabel("Attack: " + (bossnumber+1));
    static JLabel boss2attack = new JLabel("Attack: " + (bossnumber+1));
    static JLabel boss3attack = new JLabel("Attack: " + (bossnumber+1));
    static JLabel boss4attack = new JLabel("Attack: " + (bossnumber+1));

    //이동 버튼
    static JButton gotoEAST = new JButton("→");
    static JButton gotoWEST = new JButton("←");
    static JButton gotoSOUTH = new JButton("↓");
    static JButton gotoNOURTH = new JButton("↑");
    //hit, stand, a1, a10, yes, no 버튼
    static JButton hit = new JButton("HIT");
    static JButton stand = new JButton("STAND");
    static JButton a1 = new JButton("1");
    static JButton a10 = new JButton("11");
    static JButton yes = new JButton("Y");
    static JButton no = new JButton("N");
    //플레이어 능력치 라벨, 패널
    static JLabel playerHealthText = new JLabel("Player: " + String.valueOf(Player.Health));
    static JPanel playerHealth = new JPanel();
    static JLabel playerattackdamage = new JLabel("Attack: " + (bossnumber+1));
    //main 함수
    public static void main(String[] args) throws IOException{
        Dimension dimension = new Dimension(734,480);//창 크기 지정
        Mapping drawMap = new Mapping();

        ////////////////////frame
        frame.setLayout(null);//레이아웃 설정
        frame.setVisible(true);//보이게
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//x to close
        frame.setLocation(720,480);//창 위치
        frame.setPreferredSize(dimension);//창 크기

        //////////////////////PLAYER//////////////////////////
        Player.randomPlayer_xy();//플레이어 좌표 랜덤 생성
        Player.original_x=Player.player_x;//처음 생성된 좌표 저장
        Player.original_y=Player.player_y;
        Treasure.randomTreasure_xy();//보물 좌표 랜덤 생성
        random_xy_deletion();//맵 랜덤으로 지우기
        ///////////////////////BOSS///////////////////////////
        Boss1.randomBoss();//보스 위치 랜덤 생성
        Boss2.randomBoss();
        Boss3.randomBoss();
        Boss4.randomBoss();
        //////////////그래픽 맵 그리기////////////
        drawMap.setLayout(null);//drawmap 레이아웃 설정
        drawMap.setBounds(0,0,720,300);//
        frame.add(drawMap);//frame에 drawmap 추가

        /////////////////체력
        //위치와 색상 설정 후 추가
        playerHealthText.setBounds(145,415,100,25);
        playerHealth.setBounds(145,410,20*Player.Health,25);
        playerattackdamage.setBounds(145,403,100,25);
        playerHealth.setBackground(Color.green);
        frame.add(playerHealthText);
        frame.add(playerattackdamage);
        frame.add(playerHealth);
        ////////////////보스 체력
        //동일
        boss1HealthText.setBounds(490,415,100,25);
        boss2HealthText.setBounds(490,415,100,25);
        boss3HealthText.setBounds(490,415,100,25);
        boss4HealthText.setBounds(490,415,100,25);
        boss1attack.setBounds(485,403,100,25);
        boss2attack.setBounds(485,403,100,25);
        boss3attack.setBounds(485,403,100,25);
        boss4attack.setBounds(485,403,100,25);
        boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
        boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
        boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
        boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
        boss1Health.setBackground(Color.red);
        boss2Health.setBackground(Color.red);
        boss3Health.setBackground(Color.red);
        boss4Health.setBackground(Color.red);
        //맨처음엔 안보이게 설정
        boss1Health.setVisible(false);
        boss2Health.setVisible(false);
        boss3Health.setVisible(false);
        boss4Health.setVisible(false);
        boss1HealthText.setVisible(false);
        boss2HealthText.setVisible(false);
        boss3HealthText.setVisible(false);
        boss4HealthText.setVisible(false);
        boss1attack.setVisible(false);
        boss2attack.setVisible(false);
        boss3attack.setVisible(false);
        boss4attack.setVisible(false);
        //프레임에 추가
        frame.add(boss1HealthText);
        frame.add(boss2HealthText);
        frame.add(boss3HealthText);
        frame.add(boss4HealthText);
        frame.add(boss1attack);
        frame.add(boss2attack);
        frame.add(boss3attack);
        frame.add(boss4attack);
        frame.add(boss1Health);
        frame.add(boss2Health);
        frame.add(boss3Health);
        frame.add(boss4Health);
        frame.pack();

        //////////////////text
        scrollPane.setBounds(145,305,300,100);
        frame.add(scrollPane);

        textArea1.setBounds(550,410,85,25);
        frame.add(textArea1);

        textArea2.setBounds(450,305,185,100);
        frame.add(textArea2);
        ////////////////////버튼
        buttensetvisible();
        a1.setVisible(false);
        a10.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        //버튼 크기 위치 설정
        gotoEAST.setBounds(94,350,47,45);
        gotoWEST.setBounds(4,350,47,45);
        gotoSOUTH.setBounds(50,395,45,45);
        gotoNOURTH.setBounds(50,305,45,45);
        hit.setBounds(639,302,80,70);
        stand.setBounds(639,372,80,70);
        a1.setBounds(5,305,45,45);
        a10.setBounds(94,305,47,45);
        yes.setBounds(5,395,45,45);
        no.setBounds(95,395,45,45);
        //frame에 추가
        frame.getContentPane().add(gotoEAST);
        frame.getContentPane().add(gotoWEST);
        frame.getContentPane().add(gotoSOUTH);
        frame.getContentPane().add(gotoNOURTH);
        frame.getContentPane().add(hit);
        frame.getContentPane().add(stand);
        frame.getContentPane().add(a1);
        frame.getContentPane().add(a10);
        frame.getContentPane().add(yes);
        frame.getContentPane().add(no);
        frame.pack();//컨테이너의 크기를 구성 요소 들의 크기로 설정

        ////////////////////////인스턴스///////////////////////////////
        Dealer dealer = new Dealer();
        Gamer gamer = new Gamer();
        Rule rule = new Rule();
        CardDeck cardDeck = new CardDeck();
        Panel nextxy = new Panel();
        /////////////////////////////////////////////////////////////

        gotoEAST.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //textArea.setText("");
                if(bossbattle){//보스전 중에는,
                    textArea.append("\n보스전 중에는 움직일 수 없습니다!");
                }
                else if(getImgPixel(Player.getPlayer_x()+10,Player.getPlayer_y())!=-12566464 && Player.getPlayer_x()+10<720){//player +10블럭이 회색이 아닐때만.
                    Player.move_EAST();
                    nextxy.setBounds(Player.getPlayer_x(),Player.getPlayer_y(),10,10);

                    if(endlasturn==true){
                        Panel bossdelete = new Panel();
                        bossdelete.setBounds(Player.getPlayer_x()-9,Player.getPlayer_y()+1,8,8);
                        bossdelete.setBackground(Color.white);
                        drawMap.add(bossdelete);
                        endlasturn=false;
                    }

                    frame.add(nextxy);
                    textArea1.setText("→");
                    System.out.printf("(%d, %d)\r",Player.getPlayer_x(),Player.getPlayer_y());//플레이어 좌표 콘솔 출력

                    ///////////////////보스와 좌표가 같으면//////////////&& 살아있다면.
                    if((Player.getPlayer_x()==Boss1.getBoss_x() && Player.getPlayer_y()==Boss1.getBoss_y() && !Boss1.isbossdied)
                            || (Player.getPlayer_x()==Boss2.getBoss_x() && Player.getPlayer_y()==Boss2.getBoss_y() && !Boss2.isbossdied)
                            || (Player.getPlayer_x()==Boss3.getBoss_x() && Player.getPlayer_y()==Boss3.getBoss_y() && !Boss3.isbossdied)
                            || (Player.getPlayer_x()==Boss4.getBoss_x() && Player.getPlayer_y()==Boss4.getBoss_y() && !Boss4.isbossdied)){//보스와 같은 위치이면, 살아있으면
                        textArea.setText("보스 출현. 블랙잭 게임을 시작합니다.");
                        initGame(cardDeck, gamer, dealer);//게임 시작
                        bossbattle = true;//보스 배틀중인것을 알려줌
                        buttensetvisible();//버튼 활성화
                        bossmeter();//보스 체력바 공격력 출력
                    }
                    if(Player.getPlayer_x()==Treasure.getTreasure_x() && Player.getPlayer_y()==Treasure.getTreasure_y()){//보물과 위치가 같고,
                        textArea.setText("보물 발견!");
                        if(Boss1.isbossdied && Boss2.isbossdied && Boss3.isbossdied && Boss4.isbossdied){//모든 보스가 죽어있다면
                            Treasure.gettreasure=true;
                            textArea.append("\n모든 보스를 처치하여 보물을 획득했습니다!");
                            gamewin();//게임 승리
                        }
                        else{
                            textArea.append("\n보물을 얻으려면 모든 보스를 처치해야합니다!");
                        }
                    }
                }
                else{
                    textArea.setText("그 방향으로는 이동할 수 없습니다!");
                }
                textArea.setCaretPosition(textArea.getDocument().getLength());  // 맨아래로 스크롤한다.
            }
        });

        gotoWEST.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //textArea.setText("");
                if(bossbattle){//같은 좌표에 있는 보스가 살아있다면,
                    textArea.append("\n보스전 중에는 움직일 수 없습니다!");
                }
                else if(getImgPixel(Player.getPlayer_x()-10,Player.getPlayer_y())!=-12566464 && Player.getPlayer_x()-10>=0){//player +10블럭이 회색이 아닐때만.
                    Player.move_WEST();
                    nextxy.setBounds(Player.getPlayer_x(),Player.getPlayer_y(),10,10);

                    if(endlasturn){
                        Panel bossdelete = new Panel();
                        bossdelete.setBounds(Player.getPlayer_x()+9,Player.getPlayer_y()+1,8,8);
                        bossdelete.setBackground(Color.white);
                        drawMap.add(bossdelete);
                        endlasturn=false;
                    }

                    frame.add(nextxy);
                    textArea1.setText("←");
                    System.out.printf("(%d, %d)\r",Player.getPlayer_x(),Player.getPlayer_y());

                    if((Player.getPlayer_x()==Boss1.getBoss_x() && Player.getPlayer_y()==Boss1.getBoss_y() && !Boss1.isbossdied)
                            || (Player.getPlayer_x()==Boss2.getBoss_x() && Player.getPlayer_y()==Boss2.getBoss_y() && !Boss2.isbossdied)
                            || (Player.getPlayer_x()==Boss3.getBoss_x() && Player.getPlayer_y()==Boss3.getBoss_y() && !Boss3.isbossdied)
                            || (Player.getPlayer_x()==Boss4.getBoss_x() && Player.getPlayer_y()==Boss4.getBoss_y() && !Boss4.isbossdied)){//보스와 같은 위치이면
                        textArea.setText("보스 출현. 블랙잭 게임을 시작합니다.");
                        initGame(cardDeck, gamer, dealer);
                        bossbattle = true;
                        buttensetvisible();
                        bossmeter();
                    }
                    if(Player.getPlayer_x()==Treasure.getTreasure_x() && Player.getPlayer_y()==Treasure.getTreasure_y()){
                        textArea.setText("보물 발견!");
                        if(Boss1.isbossdied && Boss2.isbossdied && Boss3.isbossdied && Boss4.isbossdied){
                            Treasure.gettreasure=true;
                            textArea.append("\n모든 보스를 처치하여 보물을 획득했습니다!");
                            gamewin();//게임 승리
                        }
                        else{
                            textArea.append("\n보물을 얻으려면 모든 보스를 처치해야합니다!");
                        }
                    }
                }
                else{
                    textArea.setText("그 방향으로는 이동할 수 없습니다!");
                }
                textArea.setCaretPosition(textArea.getDocument().getLength());  // 맨아래로 스크롤한다.
            }
        });

        gotoSOUTH.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //textArea.setText("");
                if(bossbattle){//같은 좌표에 있는 보스가 살아있다면,
                    textArea.append("\n보스전 중에는 움직일 수 없습니다!");
                }
                else if(getImgPixel(Player.getPlayer_x(),Player.getPlayer_y()+10)!=-12566464 && Player.getPlayer_y()+10<300){//player +10블럭이 회색이 아닐때만.
                    Player.move_SOUTH();
                    nextxy.setBounds(Player.getPlayer_x(),Player.getPlayer_y(),10,10);

                    if(endlasturn){
                        Panel bossdelete = new Panel();
                        bossdelete.setBounds(Player.getPlayer_x()+1,Player.getPlayer_y()-9,8,8);
                        bossdelete.setBackground(Color.white);
                        drawMap.add(bossdelete);
                        endlasturn=false;
                    }

                    frame.add(nextxy);
                    textArea1.setText("↓");
                    System.out.printf("(%d, %d)\r",Player.getPlayer_x(),Player.getPlayer_y());

                    if((Player.getPlayer_x()==Boss1.getBoss_x() && Player.getPlayer_y()==Boss1.getBoss_y() && !Boss1.isbossdied)
                            || (Player.getPlayer_x()==Boss2.getBoss_x() && Player.getPlayer_y()==Boss2.getBoss_y() && !Boss2.isbossdied)
                            || (Player.getPlayer_x()==Boss3.getBoss_x() && Player.getPlayer_y()==Boss3.getBoss_y() && !Boss3.isbossdied)
                            || (Player.getPlayer_x()==Boss4.getBoss_x() && Player.getPlayer_y()==Boss4.getBoss_y() && !Boss4.isbossdied)){//보스와 같은 위치이면
                        textArea.setText("보스 출현. 블랙잭 게임을 시작합니다.");
                        initGame(cardDeck, gamer, dealer);
                        bossbattle = true;
                        buttensetvisible();
                        bossmeter();
                    }
                    if(Player.getPlayer_x()==Treasure.getTreasure_x() && Player.getPlayer_y()==Treasure.getTreasure_y()){
                        textArea.setText("보물 발견!");
                        if(Boss1.isbossdied && Boss2.isbossdied && Boss3.isbossdied && Boss4.isbossdied){
                            Treasure.gettreasure=true;
                            textArea.append("\n모든 보스를 처치하여 보물을 획득했습니다!");
                            gamewin();//게임 승리
                        }
                        else{
                            textArea.append("\n보물을 얻으려면 모든 보스를 처치해야합니다!");
                        }
                    }
                }
                else{
                    textArea.setText("그 방향으로는 이동할 수 없습니다!");
                }
                textArea.setCaretPosition(textArea.getDocument().getLength());  // 맨아래로 스크롤한다.
            }
        });

        gotoNOURTH.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //textArea.setText("");
                if(bossbattle){//같은 좌표에 있는 보스가 살아있다면,
                    textArea.append("\n보스전 중에는 움직일 수 없습니다!");
                }
                else if(getImgPixel(Player.getPlayer_x(),Player.getPlayer_y()-10)!=-12566464 && Player.getPlayer_y()-10>=0){//player +10블럭이 회색이 아닐때만.
                    Player.move_NORTH();
                    nextxy.setBounds(Player.getPlayer_x(),Player.getPlayer_y(),10,10);

                    if(endlasturn){
                        Panel bossdelete = new Panel();
                        bossdelete.setBounds(Player.getPlayer_x()+1,Player.getPlayer_y()+9,8,8);
                        bossdelete.setBackground(Color.white);
                        drawMap.add(bossdelete);
                        endlasturn=false;
                    }

                    frame.add(nextxy);
                    textArea1.setText("↑");
                    System.out.printf("(%d, %d)\r",Player.getPlayer_x(),Player.getPlayer_y());

                    if((Player.getPlayer_x()==Boss1.getBoss_x() && Player.getPlayer_y()==Boss1.getBoss_y() && !Boss1.isbossdied)
                            || (Player.getPlayer_x()==Boss2.getBoss_x() && Player.getPlayer_y()==Boss2.getBoss_y() && !Boss2.isbossdied)
                            || (Player.getPlayer_x()==Boss3.getBoss_x() && Player.getPlayer_y()==Boss3.getBoss_y() && !Boss3.isbossdied)
                            || (Player.getPlayer_x()==Boss4.getBoss_x() && Player.getPlayer_y()==Boss4.getBoss_y() && !Boss4.isbossdied)){//보스와 같은 위치이면
                        textArea.setText("보스 출현. 블랙잭 게임을 시작합니다.");
                        initGame(cardDeck, gamer, dealer);
                        bossbattle = true;
                        buttensetvisible();
                        bossmeter();
                    }
                    if(Player.getPlayer_x()==Treasure.getTreasure_x() && Player.getPlayer_y()==Treasure.getTreasure_y()){
                        textArea.setText("보물 발견!");
                        if(Boss1.isbossdied && Boss2.isbossdied && Boss3.isbossdied && Boss4.isbossdied){
                            Treasure.gettreasure=true;
                            textArea.append("\n모든 보스를 처치하여 보물을 획득했습니다!");
                            gamewin();//게임 승리
                        }
                        else{
                            textArea.append("\n보물을 얻으려면 모든 보스를 처치해야합니다!");
                        }
                    }
                }
                else{
                    textArea.setText("그 방향으로는 이동할 수 없습니다!");
                }
                textArea.setCaretPosition(textArea.getDocument().getLength());  // 맨아래로 스크롤한다.
            }
        });

        hit.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(bossbattle){//보스전이면
                    textArea1.setText("HIT");
                    textArea.setText("");
                    //게이머에게 한장 줌
                    Card card = cardDeck.splitCard();
                    if(cardDeck.isNoDeck()){
                        textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.");
                        initGame(cardDeck,gamer,dealer);//새 게임 시작
                        return;//중지
                    }
                    gamer.getCard(card);
                    if(cardDeck.isNoDeck()){
                        textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.");
                        initGame(cardDeck,gamer,dealer);
                        return;
                    }

                    if(A1or10==0 && gamer.isCardA()){
                        textArea2.setText("당신의 패에 A가 있습니다.\nA를 무엇으로 하시겠습니까?");
                        a1.setVisible(true);
                        a10.setVisible(true);
                        hit.setVisible(false);
                        stand.setVisible(false);
                    }

                    textArea.append("\n"+dealer.returnCards() + "\n" + gamer.returnCards());

                    if(rule.isBust(gamer, gamer.getSum())) {//플레이어 버스트. 딜러 승
                        Player.healthdown(bossnumber+1);//보스 공격력만큼 플레이어 체력 -x
                        textArea.append("\n" + rule.returnBust(gamer, gamer.getSum()));
                        if(Player.Health!=0){//플레이어 체력이 0이 아니면, 한판 더.
                            gamer.resetCard();
                            dealer.resetCard();
                            reGame(cardDeck, gamer, dealer);
                        }
                        /*else if(Player.Health<=0){//0이면 게임 오버
                            gameover();
                        }*/
                    }
                    else if(dealer.isLessThan()){//딜러 <=16 이면 자동 히트
                        dealer.getCard(card);
                        if(cardDeck.isNoDeck()){//덱이 없으면
                            textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.\n");
                            initGame(cardDeck,gamer,dealer);//다시 시작
                            return;
                        }
                        if(dealer.isLessThan()){
                            textArea.setText("\n"+dealer.returnCards() + "\n" + gamer.returnCards());
                            return;
                        }
                        textArea.setText(dealer.returnCards() + "\n" + gamer.returnCards());//카드 출력
                        if(rule.isBust(dealer,dealer.getSum())){//if 보스가 버스트
                            textArea.setText(dealer.returnCards() + "\n" + gamer.returnCards());

                            whatboss = whatboss();//보스판별
                            switch(whatboss){
                                case "Boss1":
                                    Boss1.healthdown(bossnumber+1);//보스 체력 -1
                                    textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                    if(Boss1.Health<=0){//보스 체력=0이 되면 승리. 보스전 종료
                                        Boss1.Health=-1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                        Boss2.healthup(1);//나머지 보스들의 체력 상승
                                        Boss3.healthup(1);
                                        Boss4.healthup(1);
                                        Player.healthup(2);//플레이어 체력 + 2
                                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                                        bossbattle=false;//보스전 종료 표시
                                        bossnumber++;//보스 번호 ++
                                        //drawMap.add(boss1died);
                                        gamer.resetCard();//카드 초기화
                                        dealer.resetCard();
                                        endlasturn=true;//마지막턴
                                        Boss1.isbossdied=true;//보스 사망
                                        bossmeter();//보스 체력바 관리
                                        hit.setVisible(bossbattle);//버튼 안보이게
                                        stand.setVisible(bossbattle);
                                    }
                                    else{//보스 체력이 0이 아니면, 한판 더
                                        //초기화 후
                                        boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));//보스 체력 출력
                                        boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                                        gamer.resetCard();//초기화
                                        dealer.resetCard();
                                        reGame(cardDeck, gamer, dealer);//리겜
                                    }
                                    break;
                                case "Boss2":
                                    Boss2.healthdown(bossnumber+1);//보스 체력 -1
                                    textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                    if(Boss2.Health<=0){//보스 체력=0이 되면 승리. 보스전 종료
                                        Boss2.Health=-1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                        Boss1.healthup(1);
                                        Boss3.healthup(1);
                                        Boss4.healthup(1);
                                        Player.healthup(2);//플레이어 체력 + 2
                                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                                        bossbattle=false;
                                        bossnumber++;
                                        //drawMap.add(boss2died);
                                        gamer.resetCard();
                                        dealer.resetCard();
                                        endlasturn=true;
                                        Boss2.isbossdied=true;
                                        bossmeter();
                                        hit.setVisible(bossbattle);
                                        stand.setVisible(bossbattle);
                                    }
                                    else{//보스 체력이 0이 아니면, 한판 더
                                        //초기화 후
                                        boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                                        boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                                        gamer.resetCard();
                                        dealer.resetCard();
                                        reGame(cardDeck, gamer, dealer);
                                    }
                                    break;
                                case "Boss3":
                                    Boss3.healthdown(bossnumber+1);//보스 체력 -1
                                    textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                    if(Boss3.Health<=0){//보스 체력=0이 되면 승리. 보스전 종료
                                        Boss3.Health=-1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                        Boss1.healthup(1);
                                        Boss2.healthup(1);
                                        Boss4.healthup(1);
                                        Player.healthup(2);//플레이어 체력 + 2
                                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                                        bossbattle=false;
                                        bossnumber++;
                                        //drawMap.add(boss3died);
                                        gamer.resetCard();
                                        dealer.resetCard();
                                        endlasturn=true;
                                        Boss3.isbossdied=true;
                                        bossmeter();
                                        hit.setVisible(bossbattle);
                                        stand.setVisible(bossbattle);
                                    }
                                    else{//보스 체력이 0이 아니면, 한판 더
                                        //초기화 후
                                        boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                                        boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                                        gamer.resetCard();
                                        dealer.resetCard();
                                        reGame(cardDeck, gamer, dealer);
                                    }
                                    break;
                                case "Boss4":
                                    Boss4.healthdown(bossnumber+1);//보스 체력 -1
                                    textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                    if(Boss4.Health<=0){//보스 체력=0이 되면 승리. 보스전 종료
                                        Boss4.Health=-1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                        Boss1.healthup(1);
                                        Boss2.healthup(1);
                                        Boss3.healthup(1);
                                        Player.healthup(2);//플레이어 체력 + 2
                                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                                        bossbattle=false;
                                        bossnumber++;
                                        //drawMap.add(boss4died);
                                        gamer.resetCard();
                                        dealer.resetCard();
                                        endlasturn=true;
                                        Boss4.isbossdied=true;
                                        bossmeter();
                                        hit.setVisible(bossbattle);
                                        stand.setVisible(bossbattle);
                                    }
                                    else{//보스 체력이 0이 아니면, 한판 더
                                        //초기화 후
                                        boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                                        boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                                        gamer.resetCard();
                                        dealer.resetCard();
                                        reGame(cardDeck, gamer, dealer);
                                    }
                                    break;
                            }
                        }
                    }
                    playerHealthText.setText(("Player: " + String.valueOf(Player.Health)));
                    playerattackdamage.setText("Attack: " + (bossnumber+1));
                    playerHealth.setBounds(145,410,20*Player.Health,25);
                    bossmeter();
                    if(Player.Health<=0){//0이면 게임 오버
                        gameover();
                    }
                }
                else{
                    textArea.setText("[HIT]는 보스전에서만 사용가능합니다!");
                }
                textArea.setCaretPosition(textArea.getDocument().getLength());  // 맨아래로 스크롤한다.
            }
        });

        stand.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(bossbattle) {//보스전 중이면
                    textArea1.setText("STAND");
                    textArea.setText("");
                    // 딜러 합 확인 뒤 승패 결정
                    Card card = cardDeck.splitCard();
                    if(cardDeck.isNoDeck()){//덱의 카드를 다 쓰면,
                        textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.");
                        initGame(cardDeck,gamer,dealer);
                        return;
                    }
                    if(dealer.isLessThan()){//딜러 <16 이면 자동 히트
                        dealer.getCard(card);
                        if(cardDeck.isNoDeck()){
                            textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.");
                            initGame(cardDeck,gamer,dealer);
                            return;
                        }
                        if(A1or10==0 && gamer.isCardA()){
                            textArea2.setText("당신의 패에 A가 있습니다. \nA를 무엇으로 하시겠습니까?");
                            a1.setVisible(true);//버튼 활성화
                            a10.setVisible(true);
                            hit.setVisible(false);//비활성화
                            stand.setVisible(false);
                        }
                        if(dealer.isLessThan()){
                            textArea.setText("\n"+dealer.returnCards() + "\n" + gamer.returnCards());
                            return;
                        }
                    }

                    if(A1or10==0 && gamer.isCardA()){
                        textArea2.setText("당신의 패에 A가 있습니다. \nA를 무엇으로 하시겠습니까?");
                        a1.setVisible(true);
                        a10.setVisible(true);
                        hit.setVisible(false);
                        stand.setVisible(false);
                    }

                    textArea.setText("\n"+dealer.returnCards() + "\n" + gamer.returnCards());

                    if(A1or10==0 && gamer.isCardA()){
                        textArea2.setText("당신의 패에 A가 있습니다. \nA를 무엇으로 하시겠습니까?");
                        a1.setVisible(true);
                        a10.setVisible(true);
                        hit.setVisible(false);
                        stand.setVisible(false);
                    }

                    if (rule.isBust(dealer, dealer.getSum())) {//딜러 버스트. 플레이서 승리
                        //textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));

                        whatboss = whatboss();//보스판별
                        switch(whatboss) {
                            case "Boss1":
                                Boss1.healthdown(bossnumber+1);//보스 체력 -1
                                textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                if (Boss1.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                    Boss1.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                    Boss2.healthup(1);
                                    Boss3.healthup(1);
                                    Boss4.healthup(1);
                                    Player.healthup(2);//플레이어 체력 + 2
                                    textArea.append("\n보스를 쓰러뜨렸습니다!");
                                    bossbattle = false;
                                    bossnumber++;gamer.resetCard();
                                    dealer.resetCard();
                                    endlasturn=true;
                                    Boss1.isbossdied=true;
                                    bossmeter();
                                    hit.setVisible(bossbattle);
                                    stand.setVisible(bossbattle);
                                } else {//보스 체력이 0이 아니면, 한판 더
                                    //초기화 후
                                    boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));
                                    boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    reGame(cardDeck, gamer, dealer);
                                }
                                break;
                            case "Boss2":
                                Boss2.healthdown(bossnumber+1);//보스 체력 -1
                                textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                if (Boss2.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                    Boss2.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                    Boss1.healthup(1);
                                    Boss3.healthup(1);
                                    Boss4.healthup(1);
                                    Player.healthup(2);//플레이어 체력 + 2
                                    textArea.append("\n보스를 쓰러뜨렸습니다!");
                                    bossbattle = false;
                                    bossnumber++;
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    endlasturn=true;
                                    Boss2.isbossdied=true;
                                    bossmeter();
                                    hit.setVisible(bossbattle);
                                    stand.setVisible(bossbattle);
                                } else {//보스 체력이 0이 아니면, 한판 더
                                    //초기화 후
                                    boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                                    boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    reGame(cardDeck, gamer, dealer);
                                }
                                break;
                            case "Boss3":
                                Boss3.healthdown(bossnumber+1);//보스 체력 -1
                                textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                if (Boss3.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                    Boss3.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                    Boss1.healthup(1);
                                    Boss2.healthup(1);
                                    Boss4.healthup(1);
                                    Player.healthup(2);//플레이어 체력 + 2
                                    textArea.append("\n보스를 쓰러뜨렸습니다!");
                                    bossbattle = false;
                                    bossnumber++;
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    endlasturn=true;
                                    Boss3.isbossdied=true;
                                    bossmeter();
                                    hit.setVisible(bossbattle);
                                    stand.setVisible(bossbattle);
                                } else {//보스 체력이 0이 아니면, 한판 더
                                    //초기화 후
                                    boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                                    boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    reGame(cardDeck, gamer, dealer);
                                }
                                break;
                            case "Boss4":
                                Boss4.healthdown(bossnumber+1);//보스 체력 -1
                                textArea.append("\n" + rule.returnBust(dealer, dealer.getSum()));
                                if (Boss4.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                    Boss4.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                    Boss1.healthup(1);
                                    Boss2.healthup(1);
                                    Boss3.healthup(1);
                                    Player.healthup(2);//플레이어 체력 + 2
                                    textArea.append("\n보스를 쓰러뜨렸습니다!");
                                    bossbattle = false;
                                    bossnumber++;
                                    //drawMap.add(boss4died);
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    endlasturn=true;
                                    Boss4.isbossdied=true;
                                    bossmeter();
                                    hit.setVisible(bossbattle);
                                    stand.setVisible(bossbattle);
                                } else {//보스 체력이 0이 아니면, 한판 더
                                    //초기화 후
                                    boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                                    boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    reGame(cardDeck, gamer, dealer);
                                }
                                break;
                        }

                    } else if (rule.isBust(gamer, gamer.getSum())) {//플레이어 버스트. 보스 승
                        textArea.append("\n" + rule.returnBust(gamer, gamer.getSum()));
                        Player.healthdown(bossnumber+1);//보스 공격력만큼 체력 -
                        if(Player.Health!=0){//플레이어 체력이 0이 아니면, 한판 더.
                            gamer.resetCard();
                            dealer.resetCard();
                            reGame(cardDeck, gamer, dealer);
                        }
                        /*else if(Player.Health<=0){//0이면 게임 오버
                            gameover();
                        }*/
                    } else {//or 숫자 비교로 승자 결정
                        String winner = rule.returnWinner(dealer.getSum(), gamer.getSum());
                        textArea.setText(dealer.returnCards("last") + "\n" + gamer.returnCards());
                        switch(winner){
                            case "Player 승리":
                                whatboss = whatboss();//보스판별
                                switch(whatboss) {
                                    case "Boss1":
                                        Boss1.healthdown(bossnumber+1);//보스 체력 -1
                                        textArea.append("\n플레이어 승리");
                                        if (Boss1.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                            Boss1.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                            Boss2.healthup(1);
                                            Boss3.healthup(1);
                                            Boss4.healthup(1);
                                            Player.healthup(2);//플레이어 체력 + 2
                                            textArea.append("\n보스를 쓰러뜨렸습니다!");
                                            bossbattle = false;
                                            bossnumber++;
                                            //drawMap.add(boss1died);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            endlasturn=true;
                                            Boss1.isbossdied=true;
                                            bossmeter();
                                            hit.setVisible(bossbattle);
                                            stand.setVisible(bossbattle);
                                        } else {//보스 체력이 0이 아니면, 한판 더
                                            //초기화 후
                                            boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));
                                            boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            reGame(cardDeck, gamer, dealer);
                                        }
                                        break;
                                    case "Boss2":
                                        Boss2.healthdown(bossnumber+1);//보스 체력 -1
                                        textArea.append("\n플레이어 승리");
                                        if (Boss2.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                            Boss2.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                            Boss1.healthup(1);
                                            Boss3.healthup(1);
                                            Boss4.healthup(1);
                                            Player.healthup(2);//플레이어 체력 + 2
                                            textArea.append("\n보스를 쓰러뜨렸습니다!");
                                            bossbattle = false;
                                            bossnumber++;
                                            //drawMap.add(boss2died);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            endlasturn=true;
                                            Boss2.isbossdied=true;
                                            bossmeter();
                                            hit.setVisible(bossbattle);
                                            stand.setVisible(bossbattle);
                                        } else {//보스 체력이 0이 아니면, 한판 더
                                            //초기화 후
                                            boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                                            boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            reGame(cardDeck, gamer, dealer);
                                        }
                                        break;
                                    case "Boss3":
                                        Boss3.healthdown(bossnumber+1);//보스 체력 -1
                                        textArea.append("\n플레이어 승리");
                                        if (Boss3.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                            Boss3.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                            Boss1.healthup(1);
                                            Boss2.healthup(1);
                                            Boss4.healthup(1);
                                            Player.healthup(2);//플레이어 체력 + 2
                                            textArea.append("\n보스를 쓰러뜨렸습니다!");
                                            bossbattle = false;
                                            bossnumber++;
                                            //drawMap.add(boss3died);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            endlasturn=true;
                                            Boss3.isbossdied=true;
                                            bossmeter();
                                            hit.setVisible(bossbattle);
                                            stand.setVisible(bossbattle);
                                        } else {//보스 체력이 0이 아니면, 한판 더
                                            //초기화 후
                                            boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                                            boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            reGame(cardDeck, gamer, dealer);
                                        }
                                        break;
                                    case "Boss4":
                                        Boss4.healthdown(bossnumber+1);//보스 체력 -1
                                        textArea.append("\n플레이어 승리");
                                        if (Boss4.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                                            Boss4.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                                            Boss1.healthup(1);
                                            Boss2.healthup(1);
                                            Boss3.healthup(1);
                                            Player.healthup(2);//플레이어 체력 + 2
                                            textArea.append("\n보스를 쓰러뜨렸습니다!");
                                            bossbattle = false;
                                            bossnumber++;
                                            //drawMap.add(boss4died);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            endlasturn=true;
                                            Boss4.isbossdied=true;
                                            bossmeter();
                                            hit.setVisible(bossbattle);
                                            stand.setVisible(bossbattle);
                                        } else {//보스 체력이 0이 아니면, 한판 더
                                            //초기화 후
                                            boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                                            boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                                            gamer.resetCard();
                                            dealer.resetCard();
                                            reGame(cardDeck, gamer, dealer);
                                        }
                                        break;
                                }
                                break;

                            case "Boss 승리":
                                Player.healthdown(bossnumber+1);
                                textArea.append("\nBoss 승리");
                                if(Player.Health!=0){//플레이어 체력이 0이 아니면, 한판 더.
                                    gamer.resetCard();
                                    dealer.resetCard();
                                    reGame(cardDeck, gamer, dealer);
                                }
                                /*else if(Player.Health<=0){//0이면 게임 오버
                                    gameover();
                                }*/
                                break;

                            case "무승부(PUSH)":
                                textArea.setText(dealer.returnCards("last") + "\n" + gamer.returnCards() + "\nPUSH! 무승부입니다.");
                                gamer.resetCard();
                                dealer.resetCard();
                                reGame(cardDeck, gamer, dealer);
                        }
                    }
                    playerHealthText.setText(("Player: " + String.valueOf(Player.Health)));
                    playerattackdamage.setText("Attack: " + (bossnumber+1));
                    playerHealth.setBounds(145,410,20*Player.Health,25);
                    bossmeter();
                    if(Player.Health<=0){//0이면 게임 오버
                        gameover();
                    }
                }
                else{
                    textArea.setText("[STAND]는 보스전에서만 사용가능합니다!");
                }
                textArea.setCaretPosition(textArea.getDocument().getLength());  // 맨아래로 스크롤한다.
            }
        });

        a1.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(bossbattle){//보스 배틀중이면,
                    if(A1or10==0 && gamer.isCardA()){//A가 있어야함
                        A1or10 = 1;// a=1
                        textArea2.append("\n이제 A는 1로 카운트 됩니다.");
                    }
                    else{
                        textArea2.append("\n패에 A카드가 없습니다!");
                    }
                }
                else{
                    textArea.append("\n보스전 중에만 선택 가능합니다!");
                }
                a1.setVisible(false);
                a10.setVisible(false);
                hit.setVisible(true);
                stand.setVisible(true);
            }
        });
        a10.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(bossbattle){
                    if(A1or10==0 && gamer.isCardA()){
                        A1or10 = 2;// a=11
                        textArea2.append("\n이제 A는 11로 카운트 됩니다.");
                    }
                    else{
                        textArea2.append("\n패에 A카드가 없습니다!");
                    }
                }
                else{
                    textArea.append("\n보스전 중에만 선택 가능합니다!");
                }
                a1.setVisible(false);
                a10.setVisible(false);
                hit.setVisible(true);
                stand.setVisible(true);
            }
        });
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerHealthText.setText(("Player: " + String.valueOf(Player.Health)));
                playerHealth.setBounds(145,410,20*Player.Health,25);
                bossmeter();
                if(Treasure.gettreasure || Player.Health<=0){
                    textArea2.setText("게임을 재시작합니다.");
                    // Run a java app in a separate system process
                    reStart();//새로운 WP2.jar실행후
                    System.exit(0);//지금 실행중인 WP2.jar 종료
                    //빌드해야함
                }
                else{
                    textArea2.setText("보물을 아직 얻지 못했습니다!\n");
                }
                yes.setVisible(false);
                no.setVisible(false);
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerHealthText.setText(("Player: " + String.valueOf(Player.Health)));
                playerHealth.setBounds(145,410,20*Player.Health,25);
                bossmeter();
                if(Treasure.gettreasure || Player.Health<=0){
                    //게임 종료
                    System.exit(0);//프로그램 중지
                }
                else{
                    textArea2.setText("보물을 아직 얻지 못했습니다!\n");
                }
                yes.setVisible(false);
                no.setVisible(false);
            }
        });
    }///////////////////////////////////////
////////////////////////////////////////////
    static int[] del_x = new int[100];//x좌표 지우기
    static int[] del_y = new int[100];
    static int[] del_width = new int[100];
    static int[] del_height = new int[100];
    //랜덤으로 맵을 지움
    public static void random_xy_deletion(){
        Random random = new Random();
        for(int k=0;k<100;k++){
            del_x[k] = 10*random.nextInt(70);
            del_y[k] = 10*random.nextInt(28);
            del_width[k] = 10*random.nextInt(10);
            del_height[k] = 10*random.nextInt(10);
        }
    }
    //스크린샷 후 픽셀 스캔. rgb값 리턴
    public static int getImgPixel(int x, int y){
        BufferedImage bufferedImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        frame.paint(bufferedImage.getGraphics());
        //버퍼에 저장한 이미지
        int image_x = x+12;
        int image_y = y+35;
        //rgb 추출
        int whatrgb = bufferedImage.getRGB(image_x,image_y);
        return whatrgb;
        //벽 -12566464
        //플레이어 -16711936
        //보스 -65536
        //보물 -256
        //빈공간 -1
    }
    //게임오버
    public static void gameover(){
        bossbattle=false;
        textArea2.setText("게임 오버! \n게임을 다시 시작하시겠습니까?");
        yes.setVisible(true);
        no.setVisible(true);
        hit.setVisible(false);
        stand.setVisible(false);
        gotoEAST.setVisible(false);
        gotoWEST.setVisible(false);
        gotoNOURTH.setVisible(false);
        gotoSOUTH.setVisible(false);
    }
    //게임 승리
    public static void gamewin(){
        bossbattle=false;
        textArea.setText("게임 승리! \n게임을 다시 시작하시겠습니까?");
        yes.setVisible(true);
        no.setVisible(true);
    }
    //처음 받는 카드 수
    public static final int INIT_RECEIVE_CARD_CNT = 2;
    public static void initGame(CardDeck cardDeck, Gamer gamer, Dealer dealer) {
        playerHealthText.setText(("Player: " + String.valueOf(Player.Health)));
        playerHealth.setBounds(145,410,20*Player.Health,25);
        bossmeter();
        A1or10=0;
        cardDeck.newCardDeck();
        for(int i = 0; i < INIT_RECEIVE_CARD_CNT; i++) {
            if(cardDeck.isNoDeck()){
                textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.\n");
                cardDeck.newCardDeck();
                initGame(cardDeck,gamer,dealer);
                return;
            }
            Card card = cardDeck.splitCard();
            gamer.getCard(card);

            Card card2 = cardDeck.splitCard();
            dealer.getCard(card2);
        }
        textArea.append("\n" + dealer.returnCards() + "\n" + gamer.returnCards());

        if(gamer.isBlackjack() && dealer.isBlackjack()){
            textArea.append("\n플레이어와 보스 모두 Blackjack! 플레이어가 보스를 두배의 공격력으로 공격합니다.");
            whatboss = whatboss();//보스판별
            switch(whatboss) {
                case "Boss1":
                    Boss1.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss1.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss1.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss1.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));
                        boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss2":
                    Boss2.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss2.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss2.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss2.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                        boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss3":
                    Boss3.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss3.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss3.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss3.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                        boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss4":
                    Boss4.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss4.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss4.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss4.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                        boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
            }
            //initGame(cardDeck,gamer,dealer);
            //return;
        }
        else if(gamer.isBlackjack()){
            textArea.append("\n플레이어 Blackjack! 보스를 두배의 공격력으로 공격합니다.");
            whatboss = whatboss();//보스판별
            switch(whatboss) {
                case "Boss1":
                    Boss1.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss1.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss1.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss1.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));
                        boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss2":
                    Boss2.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss2.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss2.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss2.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                        boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss3":
                    Boss3.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss3.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss3.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss3.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                        boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss4":
                    Boss4.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss4.Health == 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss4.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss4.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                        boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
            }
            //initGame(cardDeck,gamer,dealer);
            //return;
        }
        else if(dealer.isBlackjack()){
            textArea.append("\n보스 Blackjack! 플레이어를 두배의 공격력으로 공격합니다.");
            Player.healthdown(2*(bossnumber+1));
            if(Player.Health<=0){
                gameover();
                return;
            }
            gamer.resetCard();
            dealer.resetCard();
            initGame(cardDeck,gamer,dealer);
            return;
        }

        if(A1or10==0 && gamer.isCardA()){
            textArea2.setText("당신의 패에 A가 있습니다. \nA를 무엇으로 하시겠습니까?");
            a1.setVisible(true);
            a10.setVisible(true);
            hit.setVisible(false);
            stand.setVisible(false);
        }
    }
    //게임 시작
    public static void reGame(CardDeck cardDeck, Gamer gamer, Dealer dealer) {
        playerHealthText.setText(("Player: " + String.valueOf(Player.Health)));
        playerHealth.setBounds(145,410,20*Player.Health,25);
        bossmeter();
        A1or10=0;
        cardDeck.newCardDeck();
        for(int i = 0; i < INIT_RECEIVE_CARD_CNT; i++) {
            if(cardDeck.isNoDeck()){
                textArea2.setText("덱에 남은 카드가 없습니다. \n새 라운드를 시작합니다.\n");
                initGame(cardDeck,gamer,dealer);
                return;
            }
            Card card = cardDeck.splitCard();
            gamer.getCard(card);

            Card card2 = cardDeck.splitCard();
            dealer.getCard(card2);
        }
        textArea.append("\n보스전을 계속합니다.\n" + dealer.returnCards() + "\n" + gamer.returnCards());

        if(gamer.isBlackjack() && dealer.isBlackjack()){
            textArea.append("\n플레이어와 보스 모두 Blackjack! 플레이어가 보스를 두배의 공격력으로 공격합니다.");
            whatboss = whatboss();//보스판별
            switch(whatboss) {
                case "Boss1":
                    Boss1.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss1.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss1.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss1.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));
                        boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss2":
                    Boss2.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss2.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss2.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss2.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                        boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss3":
                    Boss3.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss3.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss3.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss3.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                        boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss4":
                    Boss4.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss4.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss4.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss4.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                        boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
            }
            //initGame(cardDeck,gamer,dealer);
            //return;
        }
        else if(gamer.isBlackjack()){
            textArea.append("\n플레이어 Blackjack! 보스를 두배의 공격력으로 공격합니다.");
            whatboss = whatboss();//보스판별
            switch(whatboss) {
                case "Boss1":
                    Boss1.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss1.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss1.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss1.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss1HealthText.setText(("Boss: " + String.valueOf(Boss1.Health)));
                        boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss2":
                    Boss2.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss2.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss2.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss3.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss2.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss2HealthText.setText(("Boss: " + String.valueOf(Boss2.Health)));
                        boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss3":
                    Boss3.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss3.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss3.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss4.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss3.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss3HealthText.setText(("Boss: " + String.valueOf(Boss3.Health)));
                        boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
                case "Boss4":
                    Boss4.healthdown(2*(bossnumber+1));//보스 체력 -1
                    if (Boss4.Health <= 0) {//보스 체력=0이 되면 승리. 보스전 종료
                        Boss4.Health = -1;//보스제거 후, 다른 보스 공격력, 체력 + 1
                        Boss1.healthup(1);
                        Boss2.healthup(1);
                        Boss3.healthup(1);
                        Player.healthup(2);//플레이어 체력 + 2
                        textArea.append("\n보스를 쓰러뜨렸습니다!");
                        bossbattle = false;
                        bossnumber++;
                        gamer.resetCard();
                        dealer.resetCard();
                        endlasturn=true;
                        Boss4.isbossdied=true;
                        bossmeter();
                        hit.setVisible(bossbattle);
                        stand.setVisible(bossbattle);
                    } else {//보스 체력이 0이 아니면, 한판 더
                        boss4HealthText.setText(("Boss: " + String.valueOf(Boss4.Health)));
                        boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                        gamer.resetCard();
                        dealer.resetCard();
                        reGame(cardDeck, gamer, dealer);
                        return;
                    }
                    break;
            }
            //initGame(cardDeck,gamer,dealer);
            //return;
        }
        else if(dealer.isBlackjack()){
            textArea.append("\n보스 Blackjack! 플레이어를 두배의 공격력으로 공격합니다.");
            Player.healthdown(2*(bossnumber+1));
            if(Player.Health<=0){
                gameover();
                return;
            }
            gamer.resetCard();
            dealer.resetCard();
            initGame(cardDeck,gamer,dealer);
            return;
        }

        if(A1or10==0 && gamer.isCardA()){
            textArea2.setText("당신의 패에 A가 있습니다. \nA를 무엇으로 하시겠습니까?");
            a1.setVisible(true);
            a10.setVisible(true);
            hit.setVisible(false);
            stand.setVisible(false);
        }
    }
    //보스 번호 판별
    public static String whatboss(){
        if(Player.getPlayer_x()==Boss1.getBoss_x() && Player.getPlayer_y()==Boss1.getBoss_y()){
            return "Boss1";
        }
        else if(Player.getPlayer_x()==Boss2.getBoss_x() && Player.getPlayer_y()==Boss2.getBoss_y()){
            return "Boss2";
        }
        else if(Player.getPlayer_x()==Boss3.getBoss_x() && Player.getPlayer_y()==Boss3.getBoss_y()){
            return "Boss3";
        }
        else if(Player.getPlayer_x()==Boss4.getBoss_x() && Player.getPlayer_y()==Boss4.getBoss_y()){
            return "Boss4";
        }
        else{
            return "No Boss";
        }
    }
    //보스 체력, 공격력 관리
    public static void bossmeter(){
        whatboss = whatboss();
        switch (whatboss){
            case "Boss1":
                boss1HealthText.setText("Boss: " + String.valueOf(Boss1.Health));
                boss2HealthText.setText("Boss: " + String.valueOf(Boss2.Health));
                boss3HealthText.setText("Boss: " + String.valueOf(Boss3.Health));
                boss4HealthText.setText("Boss: " + String.valueOf(Boss4.Health));
                boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                boss1HealthText.setVisible(!Boss1.isbossdied);
                boss1Health.setVisible(!Boss1.isbossdied);
                boss1attack.setText("Attack: " + (bossnumber+1));
                boss1attack.setVisible(!Boss1.isbossdied);
                break;
            case "Boss2":
                boss1HealthText.setText("Boss: " + String.valueOf(Boss1.Health));
                boss2HealthText.setText("Boss: " + String.valueOf(Boss2.Health));
                boss3HealthText.setText("Boss: " + String.valueOf(Boss3.Health));
                boss4HealthText.setText("Boss: " + String.valueOf(Boss4.Health));
                boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                boss2HealthText.setVisible(!Boss2.isbossdied);
                boss2Health.setVisible(!Boss2.isbossdied);
                boss2attack.setText("Attack: " + (bossnumber+1));
                boss2attack.setVisible(!Boss2.isbossdied);
                break;
            case "Boss3":
                boss1HealthText.setText("Boss: " + String.valueOf(Boss1.Health));
                boss2HealthText.setText("Boss: " + String.valueOf(Boss2.Health));
                boss3HealthText.setText("Boss: " + String.valueOf(Boss3.Health));
                boss4HealthText.setText("Boss: " + String.valueOf(Boss4.Health));
                boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                boss3HealthText.setVisible(!Boss3.isbossdied);
                boss3Health.setVisible(!Boss3.isbossdied);
                boss3attack.setText("Attack: " + (bossnumber+1));
                boss3attack.setVisible(!Boss3.isbossdied);
                break;
            case "Boss4":
                boss1HealthText.setText("Boss: " + String.valueOf(Boss1.Health));
                boss2HealthText.setText("Boss: " + String.valueOf(Boss2.Health));
                boss3HealthText.setText("Boss: " + String.valueOf(Boss3.Health));
                boss4HealthText.setText("Boss: " + String.valueOf(Boss4.Health));
                boss1Health.setBounds(445-((Boss1.Health-5)*20),410,20*Boss1.Health,25);
                boss2Health.setBounds(445-((Boss2.Health-5)*20),410,20*Boss2.Health,25);
                boss3Health.setBounds(445-((Boss3.Health-5)*20),410,20*Boss3.Health,25);
                boss4Health.setBounds(445-((Boss4.Health-5)*20),410,20*Boss4.Health,25);
                boss4HealthText.setVisible(!Boss4.isbossdied);
                boss4Health.setVisible(!Boss4.isbossdied);
                boss4attack.setText("Attack: " + (bossnumber+1));
                boss4attack.setVisible(!Boss4.isbossdied);
                break;
        }
        textArea2.setText("");
    }
    //버튼 활성화
    public static void buttensetvisible(){
        stand.setVisible(bossbattle);
        hit.setVisible(bossbattle);
    }
    //게임 재시작
    public static void reStart() {
        try {
            Runtime rt = Runtime.getRuntime();//런타임
            Process p = rt.exec("java -jar WP2.jar");//WP2.jar 을 java -jar형식으로 실행
        } catch (IOException e) {e.printStackTrace();}
    }
}