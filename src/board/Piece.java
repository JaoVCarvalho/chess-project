package board;

public class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;

        // Por padrão, o Java já iniciaria a variável position como null
        this.position = null;
    }

    protected Board getBoard() {
        return board;
    }
}
