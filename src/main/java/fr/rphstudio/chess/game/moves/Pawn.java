package fr.rphstudio.chess.game.moves;


import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.game.Utils;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for Pawn's movements  .
 *
 * @author Team KING
 */
public class Pawn implements IMove {

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard) {
        ArrayList<IChess.ChessPosition> list = new ArrayList<>();
        int var = 1;

        if (gameBoard.getPiece(p).getChessColor() == IChess.ChessColor.CLR_WHITE) {
            var *= -1;
        }

        IChess.ChessPosition position = new IChess.ChessPosition(p.x, p.y + var);
        if (Utils.isEmpty(position, gameBoard)) {
            list.add(position);
        }

        IChess.ChessPosition positionAtk = new IChess.ChessPosition(p.x - 1, p.y + var);
        IChess.ChessPosition positionAtk2 = new IChess.ChessPosition(p.x + 1, p.y + var);

        if (!Utils.isEmpty(positionAtk, gameBoard) && Utils.isEnemy(p, positionAtk, gameBoard)) {
            list.add(positionAtk);
        }
        if (!Utils.isEmpty(positionAtk2, gameBoard) && Utils.isEnemy(p, positionAtk2, gameBoard)) {
            list.add(positionAtk2);
        }

        //prise en passant
        //pion blanc vas a gauche
        if (p.y == 3) {
            if (gameBoard.getPiece(p).getChessColor() == IChess.ChessColor.CLR_WHITE && gameBoard.getPiece(p).getChessType() == IChess.ChessType.TYP_PAWN) {
                IChess.ChessPosition po1 = new IChess.ChessPosition(p.x - 1, p.y);
                IChess.ChessPosition poDead1 = new IChess.ChessPosition(p.x , p.y+1);

                if (!Utils.isEmpty(po1, gameBoard) && gameBoard.getPiece(po1).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(po1).getChessType() == IChess.ChessType.TYP_PAWN) {
                    list.add(positionAtk);
                    if(!Utils.isEmpty(poDead1, gameBoard) && gameBoard.getPiece(poDead1).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(po1).getChessType() == IChess.ChessType.TYP_PAWN) {
                        gameBoard.setPiece(poDead1 , null);
                    }
                }
            }
        }
        //pion blanc vas a droite
        if (p.y == 3) {
            if (gameBoard.getPiece(p).getChessColor() == IChess.ChessColor.CLR_WHITE && gameBoard.getPiece(p).getChessType() == IChess.ChessType.TYP_PAWN) {
                IChess.ChessPosition po2 = new IChess.ChessPosition(p.x + 1, p.y);
                IChess.ChessPosition poDead2 = new IChess.ChessPosition(p.x , p.y+1);

                if (!Utils.isEmpty(po2, gameBoard) && gameBoard.getPiece(po2).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(po2).getChessType() == IChess.ChessType.TYP_PAWN) {
                    list.add(positionAtk2);
                    if(!Utils.isEmpty(poDead2, gameBoard) && gameBoard.getPiece(poDead2).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(poDead2).getChessType() == IChess.ChessType.TYP_PAWN) {
                        gameBoard.setPiece(poDead2 , null);
                    }
                }
            }
        }
        //pion noir vas a gauche
        if (p.y == 5) {
            if (gameBoard.getPiece(p).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(p).getChessType() == IChess.ChessType.TYP_PAWN) {
                IChess.ChessPosition po1 = new IChess.ChessPosition(p.x - 1, p.y);
                IChess.ChessPosition poDead1 = new IChess.ChessPosition(p.x , p.y+1);

                if (!Utils.isEmpty(po1, gameBoard) && gameBoard.getPiece(po1).getChessColor() == IChess.ChessColor.CLR_WHITE && gameBoard.getPiece(po1).getChessType() == IChess.ChessType.TYP_PAWN) {
                    list.add(positionAtk2);
                    if(!Utils.isEmpty(poDead1, gameBoard) && gameBoard.getPiece(poDead1).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(po1).getChessType() == IChess.ChessType.TYP_PAWN) {
                        gameBoard.setPiece(poDead1 , null);
                    }
                }
            }
        }
        //pion noir vas a droite
        if (p.y == 5) {
            if (gameBoard.getPiece(p).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(p).getChessType() == IChess.ChessType.TYP_PAWN) {
                IChess.ChessPosition po2 = new IChess.ChessPosition(p.x + 1, p.y);
                IChess.ChessPosition poDead2 = new IChess.ChessPosition(p.x , p.y+1);

                if (!Utils.isEmpty(po2, gameBoard) && gameBoard.getPiece(po2).getChessColor() == IChess.ChessColor.CLR_WHITE && gameBoard.getPiece(po2).getChessType() == IChess.ChessType.TYP_PAWN) {
                    list.add(positionAtk);
                    if(!Utils.isEmpty(poDead2, gameBoard) && gameBoard.getPiece(poDead2).getChessColor() == IChess.ChessColor.CLR_BLACK && gameBoard.getPiece(poDead2).getChessType() == IChess.ChessType.TYP_PAWN) {
                        gameBoard.setPiece(poDead2 , null);
                    }

                }
            }
        }

            if (!gameBoard.getPiece(p).asMoved()) {
                var *= 2;
                IChess.ChessPosition position2 = new IChess.ChessPosition(p.x, p.y + var);
                if (Utils.isEmpty(position, gameBoard)) {
                    list.add(position2);
                }

            }


            return list;
        }


    }
