import java.util.Scanner;

public class game {
    Scanner input = new Scanner(System.in);
    board gameBoard = new board();
    String player1Name;
    String player2name;

    boolean player1_isX;
    boolean isPlayerVsPlayer;

    public game(){
        gameTypeQuery();
        moveTypeQuery();
        playerNamesQuery();
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

}
