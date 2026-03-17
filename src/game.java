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
            isPlayer1Turn = !isPlayer1Turn;

            System.out.printf("       %s's Turn \n",  name);
            int[] moveResult = getMoves();
            int x = moveResult[1];
            int y = moveResult[0];
            gameBoard.addMove(x, y, moveType);

            gameBoard.printBoard();
            System.out.println("\n\n");


            if( gameBoard.gameWon(moveType) ){
                System.out.println("--- GAME OVER ----");
                System.out.println(name+" Won The Game.");
                break;
            }
        }

    }

    private int[] getMoves(){
        int x;
        int y;
        while(true){
            System.out.println("please type your X cordinate in:");
            x = input.nextInt();
            if(x < 3 && x >= 0)break;
            else  System.out.println("invalid number");
        }
        while(true){
            System.out.println("please type your Y cordinate in:");
            y = input.nextInt();
            if(y < 3 && y >= 0)break;
            else System.out.println("invalid number");
        }
        return new int[]{x, y};
    }

}
