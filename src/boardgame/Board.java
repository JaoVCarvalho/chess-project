package boardgame;

public class Board {

    private Integer rows;
    private Integer columns;
    private  Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Piece piece(Integer rows, Integer columns){
        if(!positionExists(rows, columns)){
            throw new BoardException("Position is not on the board");
        }

        return pieces[rows][columns];
    }

    public Piece piece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position is not on the board");
        }

        return pieces[position.getRow()][position.getCollumn()];
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("There is already a piece in the position " + position);
        }

        pieces[position.getRow()][position.getCollumn()] = piece;
        piece.position = position;
    }

    // Sobrecarga do positionExists
    private Boolean positionExists(Integer row, Integer column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public Boolean positionExists(Position position){
        return positionExists(position.getRow(),position.getCollumn());
    }

    public Boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position is not on the board");
        }

        return piece(position) != null;
    }
}
