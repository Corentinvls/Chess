package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.game.Utils;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for mouvement's  Bishop.
 *
 * @author Team KING
 */
public class Bishop implements IMove {
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard) {
        ArrayList<IChess.ChessPosition> list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x + i, p.y + i);
            if(!Utils.isEmpty(position,gameBoard)){
                list.add(position);
                break;
            }
            list.add(position);
        }
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x - i, p.y - i);
            if(!Utils.isEmpty(position,gameBoard)){
                list.add(position);
                break;
            }
            list.add(position);
        }
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x + i, p.y - i);
            if(!Utils.isEmpty(position,gameBoard)){
                list.add(position);
                break;
            }
            list.add(position);
        }
        for (int i = 1; i < 8; i++) {
            IChess.ChessPosition position = new IChess.ChessPosition(p.x - i, p.y + i);
            if(!Utils.isEmpty(position,gameBoard)){
                list.add(position);
                break;
            }
            list.add(position);
        }
        return list;
    }
}