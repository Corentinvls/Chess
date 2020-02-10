package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.Arrays;

public class GameBoard {

    private Piece[][] gameBoard;

    public GameBoard() {
        this.gameBoard = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = null;
            }
        }
        initPieces();

    }

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
                        case 3:
                            type = IChess.ChessType.TYP_KING;
                            break;

                        case 4:
                            type = IChess.ChessType.TYP_QUEEN;
                            break;

                    }

                }
                gameBoard[i][j] = new Piece(color, type);
            }
        }


    }

    public Piece getPiece(IChess.ChessPosition p) {
        return gameBoard[p.y][p.x];
    }

}