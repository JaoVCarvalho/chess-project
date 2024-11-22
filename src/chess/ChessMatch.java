package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch() {

        // ChessMatch controla o tempo de vida do objeto board (Relação de Composição)
        this.board = new Board(8,8);
        initialSetup();
    }

    public ChessPiece[][] getPiece(){
        ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getColumns(); j++){
                matrix[i][j] = (ChessPiece) board.piece(i,j); // downcasting
            }
        }

        return matrix;
    }

    private void placeNewPiece(ChessPiece piece, Character column, Integer row){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){

        placeNewPiece(new Rook(board, Color.BLACK), 'a', 8);
        board.placePiece(new Rook(board, Color.BLACK), new Position(0,7));
        board.placePiece(new King(board, Color.BLACK), new Position(0,4));

        board.placePiece(new Rook(board, Color.WHITE), new Position(7,0));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7,7));
        board.placePiece(new King(board, Color.WHITE), new Position(7,4));
    }
}
