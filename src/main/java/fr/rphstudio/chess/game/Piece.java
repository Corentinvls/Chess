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
    private IChess.ChessColor chessColor;

    /**
     * Private field containing the type's chess piece.
     */
    private IChess.ChessType chessType;

    /**
     * Constructor's Piece.
     *
     * @param chessColor color's piece.
     * @param chessType  type's piece.
     */
    public Piece(IChess.ChessColor chessColor, IChess.ChessType chessType) {

        this.chessColor=chessColor;
        this.chessType=chessType;
        this.move=new Pawn();

    }

    /**
     * Geter's color of chess piece.
     *
     * @return color's chess piece.
     */
    public IChess.ChessColor getChessColor() {
        return chessColor;
    }

    /**
     * Seter's color of chess piece.
     *
     * @return color's chess piece.
     */
    public Piece setChessColor(IChess.ChessColor chessColor) {
        this.chessColor = chessColor;
        return this;
    }

    /**
     * Geter's type of chess piece.
     *
     * @return type's chess piece.
     */
    public IChess.ChessType getChessType() {
        return chessType;
    }

    /**
     * Seter's type of chess piece.
     *
     * @return type's chess piece.
     */
    public Piece setChessType(IChess.ChessType chessType) {
        this.chessType = chessType;
        return this;
    }

    public List<ChessPosition> getMove(ChessPosition p , GameBoard gameBoard) {
        return move.getPossibleMoves(p,gameBoard);
    }
}
