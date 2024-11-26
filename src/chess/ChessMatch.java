package chess;

import board.Board;
import board.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;
    private Color currentPlayer;
    private Integer turn;

    public ChessMatch() {

        // ChessMatch controla o tempo de vida do objeto board (Relação de Composição)
        this.board = new Board(8,8);
        this.currentPlayer = Color.WHITE;
        this.turn = 1;
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

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Integer getTurn() {
        return turn;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        ChessPiece capturedPiece = makeMove(source, target);
        nextTurn();

        return capturedPiece;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position source = sourcePosition.toPosition();
        validateSourcePosition(source);
        return board.piece(source).possibleMoves();
    }



    private ChessPiece makeMove(Position source, Position target){

        ChessPiece capturedPiece = ((ChessPiece) board.removePiece(target)); // Downcasting
        board.placePiece(board.removePiece(source), target);

        return capturedPiece;
    }

    private void validateSourcePosition(Position source) {
        if(!board.thereIsAPiece(source)){
            throw new ChessException("There isn't a piece in the source position");
        }

        if(currentPlayer != ((ChessPiece) board.piece(source)).getColor()){
            throw new ChessException("The chosen piece is not yours");
        }

        if(!board.piece(source).isThereAnyPossibleMove()){
            throw new ChessException("There isn't possible move for chosen piece");
        }
    }
    private void validateTargetPosition(Position source, Position target) {
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void placeNewPiece(ChessPiece piece, Character column, Integer row){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void initialSetup(){

        placeNewPiece(new Rook(board, Color.BLACK), 'a', 8);
        board.placePiece(new Rook(board, Color.BLACK), new Position(0,7));
        board.placePiece(new King(board, Color.BLACK), new Position(0,4));

        board.placePiece(new Rook(board, Color.WHITE), new Position(7,0));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7,7));
        board.placePiece(new King(board, Color.WHITE), new Position(7,4));
    }
}
