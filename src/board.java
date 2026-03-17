public class board {
    private char[][] board;

    public board(){
        board = new char[3][3];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = '-';
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }

    public void addMove(int row, int col, char moveType){
        board[row][col] = moveType;
    }
}
