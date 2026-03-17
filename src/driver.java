import java.util.Scanner;

public class driver {
    void main(){
        Scanner input = new Scanner(System.in);
        board a = new board();

        System.out.println("before");
        a.printBoard();

        a.addMove(1, 1, 'x');

        System.out.println("\nAfter");
        a.printBoard();


        System.out.println("Human or AI, type H for human, A for AI");
        boolean isHumanVsHuman = input.nextLine().equals("H");

        System.out.println("Player 1 what is your name?");
        String player1 = input.nextLine();
        String player2;

        if(isHumanVsHuman){
            System.out.println("Player 2 what is your name?");
            player2 = input.nextLine();
            System.out.printf("%s type the charactor you want to use, (X or O)", player2);
            char moveType2 =  input.nextLine().charAt(0);
        }

        System.out.printf("%s type the charactor you want to use, (X or O)", player1);
        char moveType1 =  input.nextLine().charAt(0);


    }


}