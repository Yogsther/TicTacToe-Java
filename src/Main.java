public class Main {

    public static char[] board = "/////////".toCharArray();
    public static String p1 = "placeholder";
    public static String p2 = "placeholder";
    public static String lastPick;

    public static void main(String[] args) throws InterruptedException {

        LitEngine.debugDisableSplash();
        LitEngine.start("clear");



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

    public static void check(){



    }



    public static void draw() throws InterruptedException {

        LitEngine.clear("clear");
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

        LitEngine.render();

    }


    public static void pick(String player) throws InterruptedException {
        lastPick = player;
        LitEngine.print(1, 19, "It's your turn, " + player +  ".");
        String input = LitEngine.inputString();

        char charRow = input.charAt(0);
        int intRow = Character.getNumericValue(input.charAt(1));

        int boardPos;

        if(charRow == 'A' || charRow == 'a'){
            boardPos = intRow - 1;
        } else if(charRow == 'B' || charRow == 'b'){
            boardPos = intRow + 2;
        } else if(charRow == 'C' || charRow == 'c'){
            boardPos = intRow + 5;
        } else {
            // TODO Add error
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
        LitEngine.render();




    }


}


