package fr.rphstudio.chess.game;

public class GameBoard {



    public GameBoard() {

        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                this[i][j]=null;
            }
        }
        this.initPieces();
    }
}
    private void initPieces() {

    }