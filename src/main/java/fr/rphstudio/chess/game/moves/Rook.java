package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for mouvement's  Rook.
 *
 * @author Team KING
 */
public class Rook implements IMove {
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard) {
        ArrayList<IChess.ChessPosition> list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x + i, p.y);
            if(gameBoard.getPiece(p)!= null){
                break;
            }
            list.add(position);
        }
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x, p.y + i);
            if(gameBoard.getPiece(p)!= null){
                break;
            }
            list.add(position);
        }
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x - i, p.y);
            if(gameBoard.getPiece(p)!= null){
                break;
            }
            list.add(position);
        }
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x, p.y - i);
            if(gameBoard.getPiece(p)!= null){
                break;
            }
            list.add(position);
        }
        return list;
    }
}