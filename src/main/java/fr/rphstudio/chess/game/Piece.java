package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.moves.*;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.List;

/**
 * Class used to define chess pieces.
 *
 * @author Team KING
 */
public class Piece {
    /**
     * Private field move counter
     */
    private int movesCount;
    /**
     * Private field instance move.
     */
    private IMove move;

    /**
     * Private field containing the piece's chess color.
     */
    private ChessColor chessColor;

    /**
     * Private field containing the piece's chess type.
     */
    private ChessType chessType;

    /**
     *  Piece's constructor.
     *
     * @param chessColor piece's color.
     * @param chessType   piece's type.
     */
    public Piece(ChessColor chessColor, ChessType chessType) {

        this.chessColor = chessColor;
        this.chessType = chessType;
        switch (chessType) {
            case TYP_BISHOP:
                this.move = new Bishop();
                break;
            case TYP_PAWN:
                this.move = new Pawn();
                break;
            case TYP_KNIGHT:
                this.move = new Knight();
                break;
            case TYP_ROOK:
                this.move = new Rook();
                break;
            case TYP_KING:
                this.move = new King();
                break;
            case TYP_QUEEN:
                this.move = new Queen();
                break;

        }
        this.movesCount = 0;


    }

    /**
     * Getter of the color of chess piece.
     *
     * @return chess piece's color .
     */
    public ChessColor getChessColor() {
        return chessColor;
    }

    /**
     * Setter of the color of chess piece.
     *
     * @return this.
     */
    public Piece setChessColor(ChessColor chessColor) {
        this.chessColor = chessColor;
        return this;
    }

    /**
     * Getter of the type of chess piece.
     *
     * @return chess piece's type .
     */
    public ChessType getChessType() {
        return chessType;
    }

    /**
     * Setter of the type of chess piece.
     *
     * @return this.
     */
    public Piece setChessType(ChessType chessType) {
        this.chessType = chessType;
        return this;
    }

    /**
     * list of all possible moves for a piece
     * @param p
     * @param gameBoard
     * @return
     */
    public List<ChessPosition> getMove(ChessPosition p, GameBoard gameBoard) {
        return move.getPossibleMoves(p, gameBoard);
    }
    public  boolean asMoved(){
        return this.movesCount>0 ;
    }

    /**
     * method to set the movecount
     * @param movesCount
     * @return
     */
    public Piece setMovesCount(int movesCount) {
        if(movesCount>=0){
        this.movesCount = movesCount;
        return this;
        }
        return this;
    }

    /**
     * method to get the movecount
     * @return
     */
    public int getMovesCount() {
        return movesCount;
    }
}
