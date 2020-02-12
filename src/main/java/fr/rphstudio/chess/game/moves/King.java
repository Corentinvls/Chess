package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.game.Utils;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for mouvement's  King.
 *
 * @author Team KING
 */
public class King implements IMove {
    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard) {

        ArrayList<IChess.ChessPosition> list = new ArrayList<>();

        IChess.ChessPosition position = new IChess.ChessPosition(p.x - 1, p.y - 1);
        IChess.ChessPosition position1 = new IChess.ChessPosition(p.x - 1, p.y);
        IChess.ChessPosition position2 = new IChess.ChessPosition(p.x - 1, p.y + 1);

        IChess.ChessPosition position3 = new IChess.ChessPosition(p.x, p.y - 1);
        IChess.ChessPosition position4 = new IChess.ChessPosition(p.x, p.y);
        IChess.ChessPosition position5 = new IChess.ChessPosition(p.x, p.y + 1);

        IChess.ChessPosition position6 = new IChess.ChessPosition(p.x + 1, p.y - 1);
        IChess.ChessPosition position7 = new IChess.ChessPosition(p.x + 1, p.y);
        IChess.ChessPosition position8 = new IChess.ChessPosition(p.x + 1, p.y + 1);


        list.add(position);
        list.add(position1);
        list.add(position2);
        list.add(position3);
        list.add(position4);
        list.add(position5);
        list.add(position6);
        list.add(position7);
        list.add(position8);
        IChess.ChessPosition positionL1 = new IChess.ChessPosition(p.x - 1, p.y);
        IChess.ChessPosition positionL2 = new IChess.ChessPosition(p.x - 2, p.y);
        IChess.ChessPosition positionL3 = new IChess.ChessPosition(p.x - 3, p.y);

        IChess.ChessPosition positionR1 = new IChess.ChessPosition(p.x + 1, p.y);
        IChess.ChessPosition positionR2 = new IChess.ChessPosition(p.x + 2, p.y);

        if (!gameBoard.getPiece(p).asMoved()) {
            ArrayList<IChess.ChessPosition> listRookPos = Utils.getRookPosAlly(p, gameBoard);

            for (int i = 0; i <= listRookPos.size() - 1; i++) {
                IChess.ChessPosition tempPos = listRookPos.get(i);

                if (!gameBoard.getPiece(tempPos).asMoved()) {

                    if (tempPos.x == 0 && Utils.isEmpty(positionL1, gameBoard) && Utils.isEmpty(positionL2, gameBoard)
                            && Utils.isEmpty(positionL3, gameBoard)) {
                        list.add(positionL2);
                    }
                    if (tempPos.x == 7 && Utils.isEmpty(positionR1, gameBoard) && Utils.isEmpty(positionR2, gameBoard)){
                        list.add(positionR2);
                    }


                }

            }

        }


        return list;

    }
}

