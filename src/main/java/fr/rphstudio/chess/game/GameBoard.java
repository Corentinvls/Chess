package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.Arrays;

public class GameBoard {

private Piece[][]gameBoard ;

    public GameBoard() {
        this.gameBoard = new Piece[8][8];

        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                gameBoard[i][j]=null;
            }
        }
       initPieces();

    }

    private void initPieces() {
        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(i==0 && (j==0 || j==7)){
                    gameBoard[i][j]=new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK);
                }
                else if(i==7 && (j==0 || j==7)){
                    gameBoard[i][j]=new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK);
                }
                else if(i==0 && (j==1 || j==6)){
                    gameBoard[i][j]=new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT);
                }
                else if(i==7 && (j==1 || j==6)){
                    gameBoard[i][j]=new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT);
                }
            }
        }


    }

    public Piece getPiece(IChess.ChessPosition p){
        return gameBoard[p.y][p.x];
    }

}