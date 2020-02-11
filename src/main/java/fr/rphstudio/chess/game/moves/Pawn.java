package fr.rphstudio.chess.game.moves;


import fr.rphstudio.chess.game.GameBoard;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for Pawn's movements  .
 *
 * @author Team KING
 */
public class Pawn implements IMove {
    private boolean moved;

    public Pawn() {
        this.moved = false;
    }

    public boolean isMoved() {
        return moved;
    }

    public Pawn setMoved(boolean moved) {
        this.moved = moved;
        return this;
    }

    @Override
    public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition p, GameBoard gameBoard) {
        ArrayList<IChess.ChessPosition> list = new ArrayList<>();
        int var = 1;
        if(gameBoard.getPiece(p).getChessColor() == IChess.ChessColor.CLR_WHITE){
            var*=-1;
        }

        IChess.ChessPosition position = new IChess.ChessPosition(p.x, p.y + var);
        list.add(position);

        if(!isMoved()){
            var *= 2;
            position = new IChess.ChessPosition(p.x,p.y+var);
            list.add(position);
        }


       return list;
    }

}
