package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.moves.Pawn;
import fr.rphstudio.chess.game.moves.Queen;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.List;

public class Piece {

    private IMove move;
    private ChessColor chessColor;
    private ChessType chessType;


    public Piece(ChessColor chessColor, ChessType chessType) {

        this.chessColor=chessColor;
        this.chessType=chessType;
        this.move=new Pawn();

    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public Piece setChessColor(ChessColor chessColor) {
        this.chessColor = chessColor;
        return this;
    }

    public ChessType getChessType() {
        return chessType;
    }

    public Piece setChessType(ChessType chessType) {
        this.chessType = chessType;
        return this;
    }

    public List<ChessPosition> getMove(ChessPosition p , GameBoard gameBoard) {
        return move.getPossibleMoves(p,gameBoard);
    }
}
