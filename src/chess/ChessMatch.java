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

    public void initialSetup(){
        board.placePiece(new Position(0,0), new Rook(board, Color.BLACK));
        board.placePiece(new Position(0,7), new Rook(board, Color.BLACK));
        board.placePiece(new Position(0,4), new King(board, Color.BLACK));
        board.placePiece(new Position(7,0), new Rook(board, Color.WHITE));
        board.placePiece(new Position(7,7), new Rook(board, Color.WHITE));
        board.placePiece(new Position(7,4), new King(board, Color.WHITE));
    }
}
