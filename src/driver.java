public class driver {
    void main(){
        board a = new board();

        System.out.println("before");
        a.printBoard();

        a.addMove(1, 1, 'x');

        System.out.println("\nAfter");
        a.printBoard();

    }

}
