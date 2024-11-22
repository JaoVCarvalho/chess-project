package chess;

import boardgame.Position;

public class ChessPosition {

    private Character column;
    private Integer row;

    public ChessPosition(Character column, Integer row) {
        if(column < 'a' || column > 'h' || row < 1 || row > 8){
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
        }

        this.column = column;
        this.row = row;
    }

    public Character getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    protected Position toPosition(){
        return new Position(8 - row, column - 'a');
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char) ('a' + position.getCollumn()), 8 - position.getRow());
    }

    @Override
    public String toString(){
        // "" é um macete para forçar a concatenação dos atributos
        // Outra maneira seria utilizando o String.valueOf(column + row)
        return "" + column + row;
    }
}