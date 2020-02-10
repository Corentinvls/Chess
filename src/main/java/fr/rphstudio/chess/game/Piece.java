package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Piece {
    private IChess.ChessColor chessColor;
    private IChess.ChessType chessType;

    public Piece(IChess.ChessColor chessColor, IChess.ChessType chessType) {

        this.chessColor=chessColor;
        this.chessType=chessType;

    }

    public IChess.ChessColor getChessColor() {
        return chessColor;
    }

    public Piece setChessColor(IChess.ChessColor chessColor) {
        this.chessColor = chessColor;
        return this;
    }

    public IChess.ChessType getChessType() {
        return chessType;
    }

    public Piece setChessType(IChess.ChessType chessType) {
        this.chessType = chessType;
        return this;
    }
}
