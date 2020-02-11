package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used to retrieve the position's chess pieces.
 *
 * @author Team KING
 */
public class ChessModel implements IChess {

    /**
     * Private field containing coordinates of chessboard.
     */
    private GameBoard gameBoard;

    /**
     * Private field containing the only chessboard.
     */
    private static final IChess instance = new ChessModel();

    /**
     * Constructor's ChessModel.
     */
    private ChessModel() {
        this.gameBoard = new GameBoard();

    }

    /**
     * Instance of the chessboard.
     */
    public static IChess getInstance() {
        return instance;
    }

    /**
     * Method used to reset chessboard.
     *
     * @return void
     */
    @Override
    public void reinit() {

    }

    /**
     * Method used to search the piece type at a position.
     *
     * @param p position of the chess piece.
     * @return type's chess piece.
     */
    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Utils.checkPosition(p);
        Piece piece = gameBoard.getPiece(p);
        if (piece != null) {
            return piece.getChessType();
        } else {
            throw new EmptyCellException();
        }
    }

    /**
     * Method used to search the piece color at a position.
     *
     * @param p position's chess piece.
     * @return color's chess piece.
     */
    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Utils.checkPosition(p);
        Piece piece = gameBoard.getPiece(p);
        if (piece != null) {
            return piece.getChessColor();
        } else {
            throw new EmptyCellException();
        }
    }

    /**
     * Method used to knom a number's chess piece remaining.
     *
     * @param color color's piece.
     * @return the number of remaining chess pieces.
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {
        int nb = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getPieces(i, j) != null) {
                    if (getPieces(i, j).getChessColor() == color)
                        nb++;
                }
            }
        }
        return nb;
    }

    /**
     * Method used to knom the moves a chess piece.
     *
     * @param p position's chess piece.
     * @return the list of possible movements of a chess piece.
     */
    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {

        Piece piece = gameBoard.getPiece(p);

        List<ChessPosition> list = piece.getMove(p, this.gameBoard);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (Utils.isOutofBound(list.get(i))) {
                list.remove(list.get(i));
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!Utils.isEmpty(list.get(i),gameBoard) && !Utils.isEnemy(p,list.get(i),gameBoard)) {
                list.remove(list.get(i));
            }
        }

        return list;
    }

    /**
     * Method used to define the moves a chess piece.
     *
     * @param p0 position's chess piece before.
     * @param p1 position's chess piece after.
     * @return void
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        gameBoard.getPiece(p0).setMovesCount(gameBoard.getPiece(p0).getMovesCount() + 1);
        gameBoard.setPiece(p1, gameBoard.getPiece(p0));

        if (gameBoard.getPiece(p1).getChessType() == ChessType.TYP_PAWN && (p1.y == 0 || p1.y == 7)) {
            gameBoard.setPiece(p1, new Piece(gameBoard.getPiece(p1).getChessColor(), IChess.ChessType.TYP_QUEEN));
        }
        gameBoard.setPiece(p0, null);


    }

    /**
     * Method used to know state's king.
     *
     * @param color color of the king.
     * @return if king safe or not.
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_SAFE;
    }

    /**
     * Method used to undo the last move.
     *
     * @return bool
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return new ArrayList<>();
    }

    /**
     * Method used to undo the last move.
     *
     * @return bool
     */
    @Override
    public boolean undoLastMove() {
        return false;
    }

    /**
     * Method used to know time's turn player.
     *
     * @param color     is the color's player.
     * @param isPlaying if the player is playing.
     * @return the timer.
     */
    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }


    /**
     * Method used to check the position's chess piece.
     *
     * @param x X-position of the chess piece.
     * @param y Y-position of the chess piece.
     * @return chess piece at this position.
     */
    public Piece getPieces(int x, int y) {
        ChessPosition chessPosition = new ChessPosition(x, y);
        return gameBoard.getPiece(chessPosition);
    }
}
