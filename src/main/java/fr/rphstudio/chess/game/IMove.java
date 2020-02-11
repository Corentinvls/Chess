package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.List;

/**
 * Interface used to create the movements of chess pieces.
 *
 * @author Team KING
 */
public interface IMove {
    List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard);

}
