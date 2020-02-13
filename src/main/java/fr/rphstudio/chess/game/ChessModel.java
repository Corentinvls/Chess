package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;


import java.util.*;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;


/**
 * Class used to retrieve the chess pieces's position.
 *
 * @author Team KING
 */
public class ChessModel implements IChess {

    /**
     * Private field containing chessboard's coordinates.
     */
    private GameBoard gameBoard;
    private List<HashMap<ChessPosition, Piece>> allState;
    private List<HashMap<Long, Long>> allTime;
    HashMap<Long, Long> firstTime;

    /**
     * Private field containing the only chessboard.
     */
    private static final IChess instance = new ChessModel();

    /**
     * ChessModel's Constructor .
     */
    private ChessModel() {
        this.gameBoard = new GameBoard();
        this.allState = new ArrayList<>();
        this.allState.add(Utils.getStateBoard(gameBoard));
        this.allTime = new ArrayList<>();
        Utils.saveTime(this.gameBoard, this.allTime);

    }

    /**
     * chessboard's Instance.
     */
    public static IChess getInstance() {
        return instance;
    }

    /**
     * Method used to reset chessboard.
     */
    @Override
    public void reinit() {
        this.gameBoard = new GameBoard();
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
     * Method used to know the moves a chess piece.
     *
     * @param p position's chess piece.
     * @return the list of possible movements of a chess piece.
     */
    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
//get the piece
        Piece piece = gameBoard.getPiece(p);
//throw the out of bounds moves
        List<ChessPosition> list = piece.getMove(p, this.gameBoard);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (Utils.isOutOfBound(list.get(i))) {
                list.remove(list.get(i));
            }
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            if (!Utils.isEmpty(list.get(i), gameBoard) && !Utils.isEnemy(p, list.get(i), gameBoard)) {
                list.remove(list.get(i));
            }
        }
        // save KING
        for (int i = list.size() - 1; i >= 0; i--) {
            gameBoard.setTest(true);
            Piece currentPiece = gameBoard.getPiece(p);
            Piece pieceTemp = null;
            if (i <= list.size() - 1) {
                ChessPosition pTemp = list.get(i);

                if (!Utils.isEmpty(pTemp, gameBoard)) {
                    pieceTemp = gameBoard.getPiece(pTemp);
                }
                if (!Utils.isOutOfBound(pTemp)) {
                    movePiece(p, pTemp);
                }
                if (!Utils.isEmpty(pTemp, gameBoard)) {
                    gameBoard.getPiece(pTemp).setMovesCount(gameBoard.getPiece(pTemp).getMovesCount() - 1);
                    //TODO
                    if (gameBoard.getPiece(pTemp).getChessType() == ChessType.TYP_QUEEN)
                        System.out.println("Zone test" + gameBoard.getPiece(pTemp).getMovesCount());

                    if (getKingState(gameBoard.getPiece(pTemp).getChessColor()) == ChessKingState.KING_THREATEN) {
                        list.remove(list.get(i));
                    }
                    gameBoard.setPiece(pTemp, null);
                    gameBoard.setPiece(p, currentPiece);
                    if (pieceTemp != null) {
                        gameBoard.setPiece(pTemp, pieceTemp);
                    }
                }
//bonus grand roque
                ChessPosition positionL3 = new ChessPosition(p.x - 3, p.y);
                ChessPosition positionL2 = new ChessPosition(p.x - 2, p.y);
                ChessPosition positionL1 = new ChessPosition(p.x - 1, p.y);
                int indexThirdTile = Utils.getIndexOf(Utils.enemyMovement(gameBoard.getPiece(p).getChessColor(), gameBoard), positionL3);
                int indexFirstTileGR = Utils.getIndexOf(Utils.enemyMovement(gameBoard.getPiece(p).getChessColor(), gameBoard), positionL1);
                int indexToRemoveGR = Utils.getIndexOf(list, positionL2);
                if (gameBoard.getPiece(p).getChessType() == ChessType.TYP_KING && indexThirdTile != -1 && indexToRemoveGR != -1) {
                    list.remove(indexToRemoveGR);
                } else if (gameBoard.getPiece(p).getChessType() == ChessType.TYP_KING && indexFirstTileGR != -1 && indexToRemoveGR != -1) {
                    list.remove(indexToRemoveGR);
                } else if (gameBoard.getPiece(p).getChessType() == ChessType.TYP_KING && getKingState(gameBoard.getPiece(p).getChessColor()) == ChessKingState.KING_THREATEN && indexToRemoveGR != -1) {
                    list.remove(indexToRemoveGR);
                }
                //bonus petit roque
                ChessPosition positionR2 = new ChessPosition(p.x + 2, p.y);
                ChessPosition positionR1 = new ChessPosition(p.x + 1, p.y);
                int indexFirstTile = Utils.getIndexOf(Utils.enemyMovement(gameBoard.getPiece(p).getChessColor(), gameBoard), positionR1);
                int indexToRemovePR = Utils.getIndexOf(list, positionR2);
                if (gameBoard.getPiece(p).getChessType() == ChessType.TYP_KING && indexFirstTile != -1 && indexToRemovePR != -1) {
                    list.remove(indexToRemovePR);
                } else if (gameBoard.getPiece(p).getChessType() == ChessType.TYP_KING && getKingState(gameBoard.getPiece(p).getChessColor()) == ChessKingState.KING_THREATEN && indexToRemovePR != -1) {
                    list.remove(indexToRemovePR);
                }


                gameBoard.setTest(false);
            }
        }
        return list;
    }

    /**
     * Method used to define the moves a chess piece.
     *
     * @param p0 position's chess piece before.
     * @param p1 position's chess piece after.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

        //TODO
        if (gameBoard.getPiece(p0).getChessType() == ChessType.TYP_QUEEN)
            System.out.println("nb mouv avant move" + gameBoard.getPiece(p0).getMovesCount());
        gameBoard.setPiece(p1, gameBoard.getPiece(p0));

        //transforme pion en dame
        if (gameBoard.getPiece(p1).getChessType() == ChessType.TYP_PAWN && (p1.y == 0 || p1.y == 7)) {
            gameBoard.setPiece(p1, new Piece(gameBoard.getPiece(p1).getChessColor(), IChess.ChessType.TYP_QUEEN));

            if (!gameBoard.isTest()) {

                if (gameBoard.getPiece(p0).getChessColor() == ChessColor.CLR_BLACK) {
                    List<ChessType> list = new ArrayList<>(gameBoard.getListTemoinBlack());
                    for (int k = list.size() - 1; k >= 0; k--) {
                        if (list.get(k) == ChessType.TYP_PAWN) {
                            list.remove(list.get(k));
                            list.add(ChessType.TYP_QUEEN);
                            break;
                        }
                    }
                    gameBoard.setListTemoinBlack(list);
                }

                if (gameBoard.getPiece(p0).getChessColor() == ChessColor.CLR_WHITE) {
                    List<ChessType> list = new ArrayList<>(gameBoard.getListTemoinWhite());
                    for (int k = list.size() - 1; k >= 0; k--) {
                        if (list.get(k) == ChessType.TYP_PAWN) {
                            list.remove(list.get(k));
                            list.add(ChessType.TYP_QUEEN);
                            break;
                        }
                    }
                    gameBoard.setListTemoinWhite(list);
                }
            }
        }
        //section roque
        if (gameBoard.getPiece(p0).getChessType() == ChessType.TYP_KING) {

            ArrayList<IChess.ChessPosition> listRookPos = Utils.getRookPosAlly(p0, gameBoard);

            for (int i = 0; i <= listRookPos.size() - 1; i++) {
                IChess.ChessPosition tempPos = listRookPos.get(i);
                //petit roque
                if (tempPos.x == 7 && p1.x == p0.x + 2) {
                    ChessPosition target = new ChessPosition(p1.x - 1, p1.y);
                    gameBoard.getPiece(tempPos).setMovesCount(gameBoard.getPiece(tempPos).getMovesCount() + 1);
                    gameBoard.setPiece(target, gameBoard.getPiece(tempPos));
                    gameBoard.setPiece(tempPos, null);
                    if (gameBoard.isTest()) {
                        gameBoard.setPiece(tempPos, gameBoard.getPiece(target));
                        gameBoard.setPiece(target, null);
                        gameBoard.getPiece(tempPos).setMovesCount(gameBoard.getPiece(tempPos).getMovesCount() - 1);

                    }

                }
                //grand roque
                if (tempPos.x == 0 && p1.x == p0.x - 2) {
                    ChessPosition target = new ChessPosition(p1.x + 1, p1.y);
                    gameBoard.getPiece(tempPos).setMovesCount(gameBoard.getPiece(tempPos).getMovesCount() + 1);
                    gameBoard.setPiece(target, gameBoard.getPiece(tempPos));
                    gameBoard.setPiece(tempPos, null);
                    if (gameBoard.isTest()) {
                        gameBoard.setPiece(tempPos, gameBoard.getPiece(target));
                        gameBoard.setPiece(target, null);
                        gameBoard.getPiece(tempPos).setMovesCount(gameBoard.getPiece(tempPos).getMovesCount() - 1);

                    }
                }
            }

        }
        gameBoard.getPiece(p1).setMovesCount(gameBoard.getPiece(p1).getMovesCount() + 1);
        gameBoard.setPiece(p0, null);

        //TODO
        if (gameBoard.getPiece(p1).getChessType() == ChessType.TYP_QUEEN)
            System.out.println("nb mouv apres move" + gameBoard.getPiece(p1).getMovesCount());
        //enregistre board
        if (!gameBoard.isTest()) {
            Utils.saveBoard(gameBoard, allState);
            Utils.saveTime(gameBoard, allTime);
        }

    }

    /**
     * Method used to know state's king.
     *
     * @param color color of the king.
     * @return if king safe or not.
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {


        ChessPosition kingPos = Utils.getKingPosition(gameBoard, color);
        List<ChessPosition> list = Utils.enemyMovement(color, gameBoard);

        for (ChessPosition p : list) {
            if (kingPos.equals(p)) {
                return ChessKingState.KING_THREATEN;
            }
        }
        return ChessKingState.KING_SAFE;
    }

    /**
     * Method used to undo the last move.
     *
     * @return bool
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {

        List<ChessType> listTemoin = new ArrayList<>(gameBoard.getListTemoinWhite());
        if (color == ChessColor.CLR_BLACK)
            listTemoin = new ArrayList<>(gameBoard.getListTemoinBlack());
        List<ChessType> listFinal = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPosition position = new ChessPosition(i, j);
                if (!Utils.isEmpty(position, gameBoard) && color == gameBoard.getPiece(position).getChessColor()) {
                    listFinal.add(gameBoard.getPiece(position).getChessType());
                }
            }
        }
        for (int k = listFinal.size() - 1; k >= 0; k--) {
            listTemoin.remove(listFinal.get(k));
        }
        return listTemoin;
    }

    /**
     * Method used to undo the last move.
     *
     * @return bool
     */
    @Override
    public boolean undoLastMove() {
        if (allState.size() > 1 || allTime.size() > 1) {
            if (allState.size() > 1) {
                allState.remove(allState.size() - 1);
                HashMap<IChess.ChessPosition, Piece> state = allState.get(allState.size() - 1);
                state.forEach((key, value) -> gameBoard.setPiece(key, value));
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        IChess.ChessPosition position = new IChess.ChessPosition(i, j);
                        if (!Utils.isEmpty(position, gameBoard)) {
                            gameBoard.getPiece(position).setMovesCount(gameBoard.getPiece(position).getMovesCount() - 1);
                            if (gameBoard.getPiece(position).getChessType() == ChessType.TYP_QUEEN)
                                System.out.println("nb mouv apres undo " + gameBoard.getPiece(position).getMovesCount());
                        }
                    }
                }
            }
                if (allTime.size() > 1) {
                    allTime.remove(allTime.size() - 1);
                    HashMap<Long, Long> time = allTime.get(allTime.size() - 1);
                    time.forEach((key, value) -> Utils.setTime(gameBoard, key, value));
                    if (allTime.size() == 1) {
                        gameBoard.startNewTimer();
                    }
                    // le probleme est la sert toi du isWhiteplaying
                    gameBoard.setStartTime(gameBoard.getStartTime() + getCurrentTime() - gameBoard.getTimeB());
                }


            return true;
        }
        return false;
    }


    /**
     * Returns time of current turn
     *
     * @return Time in milliseconds
     */
    public long getCurrentTime() {

        return System.currentTimeMillis() - gameBoard.getStartTime();
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

        long timeW = 0;
        long timeB = 0;
        if (color == ChessColor.CLR_WHITE && isPlaying) {
            gameBoard.setWhitePlaying(true);
            gameBoard.setTimeW(getCurrentTime() - gameBoard.getTimeB());
            return getCurrentTime() - gameBoard.getTimeB();
        } else if (color == ChessColor.CLR_BLACK && isPlaying) {
            gameBoard.setWhitePlaying(false);
            gameBoard.setTimeB(getCurrentTime() - gameBoard.getTimeW());
            return getCurrentTime() - gameBoard.getTimeW();
        } else if (color == ChessColor.CLR_WHITE) {
            timeW = gameBoard.getTimeW();
            return timeW;
        } else if (color == ChessColor.CLR_BLACK) {
            timeB = gameBoard.getTimeB();
            return timeB;
        }
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
