public class Ai {
    static char oChar = 'O';
    static char xChar = 'X';
    static char emptyChar = '-';

    public static Coordinate minimaxWorker(char[][] board, boolean player1_isX){
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
        char aiPiece = player1_isX ? oChar : xChar;

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(board[row][col] == emptyChar){
                    board[row][col] = aiPiece;
                    int moveValue = minimax(board, 0, false, player1_isX);
                    board[row][col] = emptyChar;

                    if(moveValue > bestValue){
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return new Coordinate(bestMove[0], bestMove[1]);
    }

    private static int minimax(char[][] board, int depth, boolean isMax, boolean player1_isX) {
        char aiPiece = player1_isX ? oChar : xChar;
        char humanPiece = player1_isX ? xChar : oChar;

        int  boardValue = evaluateGame(board, player1_isX);


        if(boardValue == 10 ) return 10 - depth;
        if(boardValue == -10) return depth - 10;

        boolean movesLeft = false;
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(board[row][col] == emptyChar){
                    movesLeft = true;
                    break;
                }
            }
            if(movesLeft) break;
        }

        if(!movesLeft){
            return 0; // tie
        }

        if(isMax){ //max will be O
            int highestValue = Integer.MIN_VALUE;
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[0].length; col++){
                    if(board[row][col] == emptyChar){
                        board[row][col] = aiPiece; // place piece for recursive testing
                        highestValue = Math.max(highestValue, minimax(board, depth+1, false, player1_isX)); //call recursion
                        board[row][col] = emptyChar; //remove piece
                    }
                }
            }
            return highestValue;
        }
        else{
            int lowestValue = Integer.MAX_VALUE;
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[0].length; col++){
                    if(board[row][col] == emptyChar){
                        board[row][col] = humanPiece;
                        lowestValue = Math.min(lowestValue, minimax(board, depth+1, true, player1_isX));
                        board[row][col] = emptyChar;
                    }
                }
            }
            return lowestValue;
        }

    }

    private static int evaluateGame(char[][] board, boolean player1_isX){
        char ai = player1_isX ? oChar : xChar;
        char plater = player1_isX ? xChar : oChar;

        // Rows
        if(board[0][0] == ai && board[0][1] == ai && board[0][2] == ai) return 10;
        if(board[1][0] == ai && board[1][1] == ai && board[1][2] == ai) return 10;
        if(board[2][0] == ai && board[2][1] == ai && board[2][2] == ai) return 10;

        if(board[0][0] == plater && board[0][1] == plater && board[0][2] == plater) return -10;
        if(board[1][0] == plater && board[1][1] == plater && board[1][2] == plater) return -10;
        if(board[2][0] == plater && board[2][1] == plater && board[2][2] == plater) return -10;

        // Columns
        if(board[0][0] == ai && board[1][0] == ai && board[2][0] == ai) return 10;
        if(board[0][1] == ai && board[1][1] == ai && board[2][1] == ai) return 10;
        if(board[0][2] == ai && board[1][2] == ai && board[2][2] == ai) return 10;

        if(board[0][0] == plater && board[1][0] == plater && board[2][0] == plater) return -10;
        if(board[0][1] == plater && board[1][1] == plater && board[2][1] == plater) return -10;
        if(board[0][2] == plater && board[1][2] == plater && board[2][2] == plater) return -10;

        // Diagonals
        if(board[0][0] == ai && board[1][1] == ai && board[2][2] == ai) return 10;
        if(board[0][0] == plater && board[1][1] == plater && board[2][2] == plater) return -10;

        if(board[0][2] == ai && board[1][1] == ai && board[2][0] == ai) return 10;
        if(board[0][2] == plater && board[1][1] == plater && board[2][0] == plater) return -10;

        return 0;
    }
}
