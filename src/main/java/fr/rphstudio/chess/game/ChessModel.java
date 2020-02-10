package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessModel {


    private static IChess IChess;

    public ChessModel() {

    }

    public static IChess getInstance() {
        return IChess;
    }
}
