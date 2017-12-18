import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// TODO BETTER METHOD IS ARR_POS = Y * WIDTH + X;

public class Main {

    public static char[] board = "/////////".toCharArray();
    public static String p1 = "placeholder";
    public static String p2 = "placeholder";
    public static String lastPick;

    /*
    public static int[] solve0 = {1,2,3};
    public static int[] solve1 = {3,4,5};
    public static int[] solve2 = {6,7,8};
    public static int[] solve3 = {0,3,6};
    public static int[] solve4 = {1,4,7};
    public static int[] solve5 = {2,5,8};
    public static int[] solve6 = {0,4,8};
    public static int[] solve7 = {2,4,6};
    */


    public static int[] allSolves = {1,2,3,3,4,5,6,7,8,0,3,6,1,4,7,2,5,8,0,4,8,2,4,6};

    public static void main(String[] args) throws InterruptedException {

        //LitEngine.debugShowPressedKeys();
        LitEngine.debugDisableSplash();
        LitEngine.start("clear");

        Doodles.Pride(0,0);
        Thread.sleep(500);
        LitEngine.clear("clear");


        keyHandler();

        draw();

        LitEngine.printAnimated(1, 19, "Enter player one name:", 10);
        p1 = LitEngine.inputString();

        LitEngine.clear("clear");
        LitEngine.printAnimated(1, 19, "Enter player two name:", 10);
        p2 = LitEngine.inputString();

        draw();



        if(Math.random() > 0.5){
            // Player 1 starts
            pick(p1);

        } else {
            // Player 2 starts
            pick(p2);
        }
    }

    public static void check() throws InterruptedException {

        // Main array with all the solves "allSolves"

        for(int i = 0; i < allSolves.length; i+=3){
            if(board[allSolves[i]] == board[allSolves[i+1]] && board[allSolves[i]] == board[allSolves[i+2]] && board[allSolves[i]] != '/'){
                LitEngine.clearNoRender("border");
                String winner;
                if(board[allSolves[i]] == 'O'){
                    winner = p1;
                    Doodles.O_WINNER(0,0);

                } else {
                    winner = p2;
                    Doodles.X_WINNER(0,0);
                }

                LitEngine.printAnimatedCentered(winner + " won! (Press R to restart)", 10);
                boolean restart = false;
                while(!restart){
                    Thread.sleep(10);
                }



            }

        }


    }


    public static int cursor_x;
    public static int cursor_y;
    public static String pickingPlayer;

    public static void draw() throws InterruptedException {

        LitEngine.clearNoRender("clear");
        drawBoardLayout();

        // Draw out picks

        int x = 3;
        int y = 12;
        int i = 0;

        // Draw out first row
        while(i < 3){
            // Do this 3 times
            char drawSymbol = board[i];
            String finalDraw = drawSymbol + "";
            if(drawSymbol != '/'){
                // Only draw out if it's not a slash.
                LitEngine.drawNoRender(x, y, finalDraw);
            }
            i++;
            x = x +2;
        }

        x = 3;
        y = 14;

        // Draw out second row
        while(i < 6){
            // Do this 3 times
            char drawSymbol = board[i];
            String finalDraw = drawSymbol + "";
            if(drawSymbol != '/'){
                // Only draw out if it's not a slash.
                LitEngine.drawNoRender(x, y, finalDraw);
            }
            i++;
            x = x +2;
        }

        x = 3;
        y = 16;

        // Draw out third row
        while(i < 9){
            // Do this 3 times
            char drawSymbol = board[i];
            String finalDraw = drawSymbol + "";
            if(drawSymbol != '/'){
                // Only draw out if it's not a slash.
                LitEngine.drawNoRender(x, y, finalDraw);
            }
            i++;
            x = x +2;
        }

        // Draw cursor

        String cursor_char = "?";
        if(pickingPlayer == p1){
            cursor_char = "O";
        }

        if(pickingPlayer == p2){
            cursor_char = "X";
        }

        if(drawCursor){
            LitEngine.printNoRender(1, 19, "It's your turn, " + pickingPlayer +  ".");
            LitEngine.drawNoRenderColor(cursor_x, cursor_y, cursor_char, 1);
        }


        LitEngine.render();

    }

public static  int speed = 2;

    public static void keyHandler(){
        LitEngine.frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == 10) choosen = true;

                if(e.getKeyCode() == (38)) cursor_y -= speed;
                if(e.getKeyCode() == (40)) cursor_y += speed;
                if(e.getKeyCode() == (37)) cursor_x -= speed;
                if(e.getKeyCode() == (39)) cursor_x += speed;

                try {
                    draw();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static boolean drawCursor = false;

    public static  boolean choosen = false;

    public static void pick(String player) throws InterruptedException {
        lastPick = player;
        pickingPlayer = player;
        LitEngine.print(1, 19, "It's your turn, " + player +  ".");




        choosen = false;

        // 5, 14
        cursor_x = 5;
        cursor_y = 14;


        drawCursor = true;
        draw();

        while(!choosen){
            Thread.sleep(50);
        }

        drawCursor = false;


        int boardPos;
        boardPos = 0;
        boardPos = (cursor_y - 10) / 2;
        if(boardPos == 1) boardPos = -1;
        if(boardPos == 2) boardPos = 2;
        if(boardPos == 3) boardPos = 5;


        int adder = 0;
        if(cursor_x == 3) adder = 1;
        if(cursor_x == 5) adder = 2;
        if(cursor_x == 7) adder = 3;

        System.out.println(boardPos + " * " + adder +" - 2");
        boardPos = adder + boardPos;


        // Check if valid
        if(board[boardPos] != '/'){
            pick(player);
            return;
        }

        // Insert X or O in board array.
        if(player == p1){
            board[boardPos] = 'O';
        }

        if(player == p2){
            board[boardPos] = 'X';
        }


        check();
        draw();

        if(player == p1){
            pick(p2);
            return;
        }
        if(player == p2){
            pick(p1);
            return;
        }





/*

*/





    }



    public static void drawBoardLayout(){

        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(8, 11, "╗");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(2, 11, "╔");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(2, 17, "╚");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(8, 17, "╝");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(7, 17, "═");
        LitEngine.drawNoRender(5, 17, "═");
        LitEngine.drawNoRender(3, 17, "═");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(4, 17, "╩");
        LitEngine.drawNoRender(6, 17, "╩");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(4, 16, "║");
        LitEngine.drawNoRender(6, 16, "║");
        LitEngine.drawNoRender(4, 14, "║");
        LitEngine.drawNoRender(6, 14, "║");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(2, 16, "║");
        LitEngine.drawNoRender(2, 14, "║");
        LitEngine.drawNoRender(2, 12, "║");
        LitEngine.drawNoRender(8, 16, "║");
        LitEngine.drawNoRender(8, 14, "║");
        LitEngine.drawNoRender(8, 12, "║");
        LitEngine.drawNoRender(4, 12, "║");
        LitEngine.drawNoRender(6, 12, "║");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(4, 13, "╬");
        LitEngine.drawNoRender(6, 13, "╬");
        LitEngine.drawNoRender(4, 15, "╬");
        LitEngine.drawNoRender(6, 15, "╬");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(2, 13, "╠");
        LitEngine.drawNoRender(2, 15, "╠");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(8, 13, "╣");
        LitEngine.drawNoRender(8, 15, "╣");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(7, 15, "═");
        LitEngine.drawNoRender(7, 13, "═");
        LitEngine.drawNoRender(7, 11, "═");
        LitEngine.drawNoRender(3, 11, "═");
        LitEngine.drawNoRender(3, 13, "═");
        LitEngine.drawNoRender(3, 15, "═");
        LitEngine.drawNoRender(5, 15, "═");
        LitEngine.drawNoRender(5, 13, "═");
        LitEngine.drawNoRender(5, 11, "═");
        /* Generated with LitEngine Plotter */
        LitEngine.drawNoRender(4, 11, "╦");
        LitEngine.drawNoRender(6, 11, "╦");

        // Draw coordinates

        LitEngine.drawNoRender(3, 10, "1");
        LitEngine.drawNoRender(5, 10, "2");
        LitEngine.drawNoRender(7, 10, "3");
        LitEngine.drawNoRender(1, 12, "A");
        LitEngine.drawNoRender(1, 14, "B");
        LitEngine.drawNoRender(1, 16, "C");
        //LitEngine.render();




    }


}


