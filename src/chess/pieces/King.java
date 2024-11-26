package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        return (getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position)));
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

        return matrix;
    }
}
