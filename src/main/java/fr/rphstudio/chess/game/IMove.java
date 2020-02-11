package fr.rphstudio.chess.game;


import fr.rphstudio.chess.interf.IChess;

import java.util.List;

public interface IMove {
    List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p);

}
