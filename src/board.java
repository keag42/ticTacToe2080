public class board {
    public char[][] board;
    public int movesPlaced;
    coordinate lastMove;
    public board(){
        board = new char[3][3];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = '-';
            }
        }
        movesPlaced = 0;
    }

    public void printBoard(){
        System.out.println("       X    ");
        System.out.println("     0 1 2");
        for(int i = 0; i < board.length; i++){
            if(i ==1){
                System.out.print("Y " + i + ": ");

            }
            else{

                System.out.print("  " + i + ": ");
            }
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void addMove(int x, int y, char moveType){
        board[x][y] = moveType;
        movesPlaced++;
        lastMove = new coordinate(x, y);
    }

    public boolean gameWon(char moveType){
        //TODO come up with better way than this hard coded way
        //Verticle
         if(board[0][0] == moveType && board[1][0] == moveType && board[2][0] == moveType)return true;
         if(board[0][1] == moveType && board[1][1] == moveType && board[2][1] == moveType)return true;
         if(board[0][2] == moveType && board[1][2] == moveType && board[2][2] == moveType) return true;

         //Horizontal
         if(board[0][0] == moveType && board[0][1] == moveType && board[0][2] == moveType)return true;
         if(board[1][0] == moveType && board[1][1] == moveType && board[1][2] == moveType)return true;
         if(board[2][0] == moveType && board[2][1] == moveType && board[2][2] == moveType)return true;

         //diagonal
         if(board[0][2] == moveType && board[1][1] == moveType && board[2][0] == moveType)return true;
         if(board[0][0] == moveType && board[1][1] == moveType && board[2][2] == moveType)return true;

         return false;
    }
}
