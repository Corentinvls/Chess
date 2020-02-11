package fr.rphstudio.chess.game.moves;



import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;
/**
 * Class used for mouvement's  Queen.
 *
 * @author Team KING
 */
public class Queen implements IMove {
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard) {

        ArrayList<IChess.ChessPosition> list = new ArrayList<>();
        IChess.ChessPosition position = new IChess.ChessPosition(p.x - 1, p.y - 1);
        list.add(position);
        return list;
    }
}
