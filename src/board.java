public class board {
    private char[][] board;
    int movesPlaced;
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
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j]  +" ");
            }
            System.out.println();
        }

    }

    public void addMove(int row, int col, char moveType){
        board[row][col] = moveType;
        movesPlaced++;
    }

    public coordinate[] possibleMoves(){
        coordinate[] availableMoves = new coordinate[9-movesPlaced];
        int moveCount = 0;
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                if(board[y][x] == '-'){
                    availableMoves[moveCount++] = new coordinate(x, y);
                }

            }
        }
        return availableMoves;
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
