package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        return (getBoard().positionExists(position) && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position)));
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0, 0);

        // Top L Left
        auxPosition.setValues(position.getRow() - 2, position.getColumn() - 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 2);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Top L Right
        auxPosition.setValues(position.getRow() - 2, position.getColumn() + 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 2);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom L Left
        auxPosition.setValues(position.getRow() + 2, position.getColumn() - 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 2);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom L Right
        auxPosition.setValues(position.getRow() + 2, position.getColumn() + 1);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 2);
        if(canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        return matrix;
    }

    @Override
    public String toString(){
        return "k";
    }
}
