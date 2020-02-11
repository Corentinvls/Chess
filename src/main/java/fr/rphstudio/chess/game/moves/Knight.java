package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.ChessModel;
import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.interf.IChess.*;
import org.newdawn.slick.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for movement's  Knight.
 *
 * @author Team KING
 */
public class Knight implements IMove {
    @Override
    public List<ChessPosition> getPossibleMoves(ChessPosition p, GameBoard gameBoard) {

        ArrayList<ChessPosition> list = new ArrayList<>();

        ChessPosition position1 = new ChessPosition(p.x + 1, p.y - 2);
        ChessPosition position2 = new ChessPosition(p.x - 1, p.y - 2);
        ChessPosition position3 = new ChessPosition(p.x + 1, p.y + 2);
        ChessPosition position4 = new ChessPosition(p.x - 1, p.y + 2);
        ChessPosition position5 = new ChessPosition(p.x - 2, p.y + 1);
        ChessPosition position6 = new ChessPosition(p.x - 2, p.y - 1);
        ChessPosition position7 = new ChessPosition(p.x + 2, p.y + 1);
        ChessPosition position8 = new ChessPosition(p.x + 2, p.y - 1);

            list.add(position1);
            list.add(position2);
            list.add(position3);
            list.add(position4);
            list.add(position5);
            list.add(position6);
            list.add(position7);
            list.add(position8);



        return list;
    }
}
