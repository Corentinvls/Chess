package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

public class Utils {
    /**
     * Method used to check the position's chess piece.
     *
     * @param p position of the chess piece.
     * @return if the chess piece is in the chessboard or not.
     */
    public static void checkPosition(IChess.ChessPosition p) throws OutOfBoardException {
        if (p.x < 0 || p.x > 7) {
            throw new OutOfBoardException();
        } else if (p.y < 0 || p.y > 7) {
            throw new OutOfBoardException();
        }
    }
    public static boolean isEmpty (IChess.ChessPosition p, GameBoard gameBoard ){
        return gameBoard.getPiece(p) == null;
    }
    public static boolean isEnemy (IChess.ChessPosition currentPosition, IChess.ChessPosition targetPosition,GameBoard gameBoard){
        if(!isEmpty(targetPosition,gameBoard)) {
            return gameBoard.getPiece(currentPosition).getChessColor() != gameBoard.getPiece(targetPosition).getChessColor();
        }else{
        return false;
        }
    }
   // public IChess.ChessPosition getKingPosition(IChess.ChessColor color) {


    //}
}
