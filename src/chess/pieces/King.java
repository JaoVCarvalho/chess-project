package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean canMove(Position position){
        return (getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position)));
    }

    private boolean testRookCastling(Position position){
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece instanceof Rook && piece.getColor() ==  getColor() && piece.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);

        // Top
        auxPosition.setValues(position.getRow() - 1, position.getColumn());
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Top Left Diagonal
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Top Right Diagonal
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom
        auxPosition.setValues(position.getRow() + 1, position.getColumn());
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom Left Diagonal
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom Right Diagonal
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Right
        auxPosition.setValues(position.getRow(), position.getColumn() + 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Left
        auxPosition.setValues(position.getRow(), position.getColumn() - 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Special Move Castling
        if(getMoveCount() == 0 && !chessMatch.isCheck()){

            // kingside castling
            if(testRookCastling(new Position(position.getRow(), position.getColumn() + 3))){
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null ){
                    matrix[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            // queenside castling
            if(testRookCastling(new Position(position.getRow(), position.getColumn() - 4))){
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);

                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    matrix[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }


        return matrix;
    }

    @Override
    public String toString(){
        return "K";
    }
}
