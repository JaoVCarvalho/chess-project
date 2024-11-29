package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getRows()];
        Position auxPosition = new Position(0, 0);

        // Top
        auxPosition.setValues(position.getRow() - 1, position.getColumn());
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setRow(auxPosition.getRow() - 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Bottom
        auxPosition.setValues(position.getRow() + 1, position.getColumn());
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setRow(auxPosition.getRow() + 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Left
        auxPosition.setValues(position.getRow(), position.getColumn() - 1);
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setColumn(auxPosition.getColumn() - 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // Right
        auxPosition.setValues(position.getRow(), position.getColumn() + 1);
        while(getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setColumn(auxPosition.getColumn() + 1);
        }

        if(getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

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

    public String toString(){
        return "Q";
    }
}
