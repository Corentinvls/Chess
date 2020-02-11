package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.moves.Pawn;
import fr.rphstudio.chess.game.moves.Queen;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.List;

/**
 * Class used to define chess pieces.
 *
 * @author Team KING
 */
public class Piece {

    /**
     * Private field intance's move.
     */
    private IMove move;

    /**
     * Private field containing the color's chess piece.
     */
    private ChessColor chessColor;

    /**
     * Private field containing the type's chess piece.
     */
    private ChessType chessType;

    /**
     * Constructor's Piece.
     *
     * @param chessColor color's piece.
     * @param chessType  type's piece.
     */
    public Piece(ChessColor chessColor, ChessType chessType) {

        this.chessColor=chessColor;
        this.chessType=chessType;
        this.move=new Pawn();

    }

    /**
     * Geter's color of chess piece.
     *
     * @return color's chess piece.
     */
    public ChessColor getChessColor() {
        return chessColor;
    }

    /**
     * Seter's color of chess piece.
     *
     * @return color's chess piece.
     */
    public Piece setChessColor(ChessColor chessColor) {
        this.chessColor = chessColor;
        return this;
    }

    /**
     * Geter's type of chess piece.
     *
     * @return type's chess piece.
     */
    public ChessType getChessType() {
        return chessType;
    }

    /**
     * Seter's type of chess piece.
     *
     * @return type's chess piece.
     */
    public Piece setChessType(ChessType chessType) {
        this.chessType = chessType;
        return this;
    }

    public List<ChessPosition> getMove(ChessPosition p , GameBoard gameBoard) {
        return move.getPossibleMoves(p,gameBoard);
    }
}
