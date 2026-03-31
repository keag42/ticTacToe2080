import java.util.Scanner;

public class game {
    Scanner input = new Scanner(System.in);
    board gameBoard = new board();
    String player1Name;
    String player2name;

    boolean player1_isX;
    boolean isPlayerVsPlayer;
    boolean isPlayer1Turn;
    public game(){
        gameTypeQuery();
        moveTypeQuery();
        playerNamesQuery();
        isPlayer1Turn = true;
    }

    private void gameTypeQuery() {
        System.out.println("Human or AI, type H for human, A for AI");

        while (true) {
            String humanOrNot = input.nextLine();

            if (humanOrNot.equals("H") || humanOrNot.equals("A") || humanOrNot.equals("h") || humanOrNot.equals("a")) {
                isPlayerVsPlayer = humanOrNot.equals("H") || humanOrNot.equals("h");
                break;
            } else
                System.out.println("that is not a valid  answer only 'H' or 'A'");
        }
    }
    private void moveTypeQuery(){
        System.out.println("would you like to be X or O?");

        while (true) {
            String xOrO = input.nextLine();

            if (xOrO.equals("X") || xOrO.equals("x")){
                player1_isX = true;
                break;
            }
            else if(xOrO.equals("O") || xOrO.equals("o")) {
                player1_isX  = false;
                break;
            } else
                System.out.println("that is not a valid  answer only 'X' or 'O'");
        }
    }
    private void playerNamesQuery(){
        System.out.println("player 1 what is your name?");
        player1Name = input.nextLine();

        if(isPlayerVsPlayer){
            System.out.println("player 2 what is your name?");
            player2name = input.nextLine();
        }
        else player2name = "AI";

    }

    public void start(){
        System.out.println("----  Game Started  ----");


        while(true) {
            char moveType;
            String name;
            if(isPlayer1Turn){
                moveType = player1_isX ? 'X' : 'O';
                name = player1Name;
            }
            else{
                moveType = player1_isX ? 'O' : 'X';
                name = player2name;
            }


            System.out.printf("       %s's Turn \n",  name);
            if(!isPlayerVsPlayer && !isPlayer1Turn){
                coordinate bestMove = minimaxWorker(gameBoard.board);
                System.out.println("\u001B[41m" + "ai's idea: x: " + bestMove.x+ ", y: " +bestMove.y + ". move type: " + moveType + "\u001B[0m");
                gameBoard.addMove(bestMove.x, bestMove.y, moveType);
            }
            else{
                coordinate moveResult = getMoves();
                int x = moveResult.x;
                int y = moveResult.y;
                gameBoard.addMove(x, y, moveType);
            }


            gameBoard.printBoard();
            System.out.println("\n\n");




            if( gameBoard.gameWon(moveType) ){
                System.out.println("--- GAME OVER ----");
                System.out.println(name+" Won The Game.");
                break;
            }
            isPlayer1Turn = !isPlayer1Turn;
        }

    }

    private coordinate getMoves(){
        int x;
        int y;
        while(true){
            System.out.println("please type your Y cordinate in:");
            x = input.nextInt();
            System.out.println("please type your X cordinate in:");
            y = input.nextInt();


            if(y > 2 && y < 0 || x > 2 && x < 0) {
                System.out.println("invalid number, must be between 0-2 inclusive.");
                continue;
            }
            else if(gameBoard.board[y][x] != '-'){
                System.out.println("there is already a piece there. try a different spot.");
                continue;
            }
            break;

        }
        return new coordinate(x, y);
    }

    public int minimax(char[][] board, int depth, boolean isMax) {
        int score = getScore();

        if (score == 1) {
            return score;
        }

        if (gameBoard.movesPlaced == 9) { //no winner game tied
            return 0;
        }

        if (isMax) {
            int best = -10;
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < 3; row++) {
                    if (board[col][row] == '_') {// if cell empty{
                        // Make the move
                        board[col][row] = player1_isX ? 'X' : 'O';

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[col][row] = '_';
                    }
                }
            }
            return best;
        }
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == '_') {
                        // Make the move
                        board[i][j] = board[i][j] = player1_isX ? 'O' : 'X';;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }
    public coordinate minimaxWorker(char[][] board){
        int bestValue = -10;
        coordinate bestMove = new coordinate(-1, -1);

        for(int col = 0; col < 3; col ++){
            for(int row = 0; row < 3; row++){

                if(board[col][row] == '-'){
                    int moveValue = minimax(board, 0, false);
                    System.out.println("piece: " + board[col][row]); //todo remove this is only for testing
                    board[col][row] = '-';
                    if(moveValue > bestValue){
                        bestMove.x = col;
                        bestMove.y = row;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }
    public int getScore(){
        char player = player1_isX ? 'X' : 'O';
        char ai = player1_isX ? 'O' : 'X';
        if(gameBoard.gameWon(player)){
            return +1;
        }
        else if(gameBoard.gameWon(ai)){
            return -1;
        }
        else{
            return 0;
        }
    }
/*    public void minimaxWorker(){
        coordinate[] freeMoves = gameBoard.possibleMoves();
        //the bot will be O for now
        for(coordinate cord: freeMoves){
//            System.out.println("for move, x:" + cord.x +", y:" +cord.y);

            gameBoard.addMove(cord.x, cord.y, 'X');
            if(gameBoard.gameWon('X')){
                System.out.println("X can win by playing: X:" + cord.x + ", O: " + cord.y);
                System.out.println("WINNENR ");
            }
            gameBoard.removeLastMove();

            gameBoard.addMove(cord.x, cord.y, 'O');
            if(gameBoard.gameWon('O')){
                System.out.println("O can win by playing: X:" + cord.x + ", O: " + cord.y);
            }
            gameBoard.removeLastMove();
        }
    }*/
}
