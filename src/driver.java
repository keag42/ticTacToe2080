import java.util.Scanner;


void main(){
    //Scanner input = new Scanner(System.in);
    board a = new board();


    //how to use board
    System.out.println("before");
    a.printBoard();

    a.addMove(1, 1, 'x');

    System.out.println("\nAfter");
    a.printBoard();

    //how to use game interface

}


