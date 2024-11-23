package board;

public abstract class Piece {

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

    public abstract boolean[][] possibleMoves();

    // Hook Method, Concrete Method
    // Pertence a um padrão de projetos conhecido como "Template Method Pattern"
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] matrix = possibleMoves();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
