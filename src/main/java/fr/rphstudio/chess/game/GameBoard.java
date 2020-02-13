package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to created the chessboard.
 *
 * @author Team KING
 */
public class GameBoard {

    /**
     * Private field containing the chessboard.
     */
    private Piece[][] gameBoard;
    private List<IChess.ChessType> listTemoinWhite;
    private List<IChess.ChessType> listTemoinBlack;
    private List<GameBoard> allState;
    private boolean test;


    /**
     * Constructor's GameBoard for define size's chessboard.
     */
    public GameBoard() {
        this.gameBoard = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = null;
            }
        }
        initPieces();
        this.listTemoinWhite = new ArrayList<>();
        this.listTemoinBlack = new ArrayList<>();
        fillLists();
        this.test = false;


    }

    /**
     * Method used to initialise position of chess pieces.
     *
     * @return void
     */
    private void initPieces() {

        for (int i = 0; i < 8; i++) {
            IChess.ChessColor color = IChess.ChessColor.CLR_WHITE;
            //gestion couleur
            if (i < 2) {
                color = IChess.ChessColor.CLR_BLACK;
            } else if (i > 5) {
                color = IChess.ChessColor.CLR_WHITE;
            } else {
                continue;
            }
            for (int j = 0; j < 8; j++) {
                IChess.ChessType type = IChess.ChessType.TYP_PAWN;
                if (i != 1 && i != 6) {
                    switch (j) {
                        case 0:
                            type = IChess.ChessType.TYP_ROOK;

                        case 7:
                            type = IChess.ChessType.TYP_ROOK;
                            break;
                        case 1:
                            type = IChess.ChessType.TYP_KNIGHT;

                        case 6:
                            type = IChess.ChessType.TYP_KNIGHT;
                            break;
                        case 2:
                            type = IChess.ChessType.TYP_BISHOP;

                        case 5:
                            type = IChess.ChessType.TYP_BISHOP;
                            break;
                        case 4:
                            type = IChess.ChessType.TYP_KING;
                            break;

                        case 3:
                            type = IChess.ChessType.TYP_QUEEN;
                            break;

                    }

                }
                gameBoard[i][j] = new Piece(color, type);
            }
        }


    }

    private void fillLists() {
        listTemoinWhite.add(IChess.ChessType.TYP_KING);
        listTemoinWhite.add(IChess.ChessType.TYP_QUEEN);
        listTemoinBlack.add(IChess.ChessType.TYP_KING);
        listTemoinBlack.add(IChess.ChessType.TYP_QUEEN);
        for (int i = 0; i < 2; i++) {
            listTemoinWhite.add(IChess.ChessType.TYP_BISHOP);
            listTemoinBlack.add(IChess.ChessType.TYP_BISHOP);
        }
        for (int i = 0; i < 2; i++) {
            listTemoinWhite.add(IChess.ChessType.TYP_KNIGHT);
            listTemoinBlack.add(IChess.ChessType.TYP_KNIGHT);
        }
        for (int i = 0; i < 2; i++) {
            listTemoinWhite.add(IChess.ChessType.TYP_ROOK);
            listTemoinBlack.add(IChess.ChessType.TYP_ROOK);

        }
        for (int i = 0; i < 8; i++) {

            listTemoinWhite.add(IChess.ChessType.TYP_PAWN);
            listTemoinBlack.add(IChess.ChessType.TYP_PAWN);
        }
    }

    /**
     * Method used to get type's chess piece at this position.
     *
     * @param p position's chess piece.
     * @return type's chess piece.
     */
    public Piece getPiece(IChess.ChessPosition p) {

        if (!Utils.isOutOfBound(p))
            return gameBoard[p.y][p.x];
        else
            return null;

    }

    public GameBoard setPiece(IChess.ChessPosition p, Piece piece) {
        this.gameBoard[p.y][p.x] = piece;
        return this;
    }


    public List<IChess.ChessType> getListTemoinWhite() {
        return listTemoinWhite;
    }

    public GameBoard setListTemoinWhite(List<IChess.ChessType> listTemoinWhite) {
        this.listTemoinWhite = listTemoinWhite;
        return this;
    }

    public List<IChess.ChessType> getListTemoinBlack() {
        return listTemoinBlack;
    }

    public GameBoard setListTemoinBlack(List<IChess.ChessType> listTemoinBlack) {
        this.listTemoinBlack = listTemoinBlack;
        return this;
    }

    public boolean isTest() {
        return test;
    }

    public GameBoard setTest(boolean test) {
        this.test = test;
        return this;
    }
}
