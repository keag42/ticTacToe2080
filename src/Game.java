/*
    Keagan Cameron: 101543441
    O'Neal Jean: 101544778
    Caio Cotts: 101571045
*/
import java.util.Scanner;

public class Game {
    Scanner input = new Scanner(System.in);
    Board gameBoard = new Board();
    String player1Name;
    String player2name;

    boolean player1_isX;
    boolean isPlayerVsPlayer;
    boolean isPlayer1Turn;
    
    char xChar = 'X';
    char oChar = 'O';
    char emptyChar = '-';
    
    public Game(){
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

        gameBoard.printBoard();
        isPlayer1Turn = player1_isX;

        while(true) {
            char moveType;
            String name;
            if(isPlayer1Turn){
                moveType = player1_isX ? xChar : oChar;
                name = player1Name;
            }
            else{
                moveType = player1_isX ? oChar : xChar;
                name = player2name;
            }


            System.out.printf("       %s's Turn \n",  name);
            if(!isPlayerVsPlayer && !isPlayer1Turn){
                Coordinate bestMove = Ai.minimaxWorker(gameBoard.board, player1_isX);
               // System.out.println("\u001B[41m" + "ai's idea: x: " + bestMove.x+ ", y: " +bestMove.y + ". move type: " + moveType + "\u001B[0m");
                gameBoard.addMove(bestMove.x, bestMove.y, moveType);
            }
            else{
                Coordinate moveResult = getMoves();
                int x = moveResult.x;
                int y = moveResult.y;
                gameBoard.addMove(x, y, moveType);
            }


            gameBoard.printBoard();
            System.out.println("\n\n");




            if( gameBoard.gameWon(moveType) ){
                System.out.println("--- GAME OVER ----");
                System.out.println(name + " Won The Game.");
                break;
            }
            if(gameBoard.gameTied()){
                System.out.println("--- GAME OVER ----");
                System.out.println("This game ended in a tie.");
                break;
            }
            isPlayer1Turn = !isPlayer1Turn;
        }

    }

    private Coordinate getMoves(){
        int x;
        int y;
        while(true){
            System.out.println("please type your Y cordinate in:");
            x = input.nextInt();
            System.out.println("please type your X cordinate in:");
            y = input.nextInt();


            if(y > 2 || y < 0 || x > 2 || x < 0) {
                System.out.println("invalid number, must be between 0-2 inclusive.");
                continue;
            }
            else if(gameBoard.board[x][y] != emptyChar){
                System.out.println("there is already a piece there. try a different spot.");
                continue;
            }
            break;

        }
        return new Coordinate(x, y);
    }

}
