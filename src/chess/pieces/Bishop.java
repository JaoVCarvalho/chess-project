package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {

        Position auxPosition = new Position(position.getRow(), position.getColumn());
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // Top Left Diagonal
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() - 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Top Right Diagonal
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() + 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom Left Diagonal
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() - 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom Right Diagonal
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);

        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() + 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        return matrix;
    }

    @Override
    public String toString(){
        return "B";
    }
}
