package chess;

import board.Board;
import board.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private Board board;
    private Color currentPlayer;
    private Integer turn;
    private boolean check; // Por padrão, o Java já inicializa como false
    private boolean checkMate;

    private List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
    private List<ChessPiece> capturedPieces = new ArrayList<>();

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

    public boolean isCheck() {
        return check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);
        ChessPiece capturedPiece = makeMove(source, target);

        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        } else {
            nextTurn();
        }

        return capturedPiece;
    }


    private boolean testCheck(Color color){
        Position position = getKing(color).getChessPosition().toPosition();
        List<ChessPiece> opponentPieces = piecesOnTheBoard.stream().filter(x -> x.getColor() == opponent(color)).collect(Collectors.toList());

        for (ChessPiece piece : opponentPieces){
            boolean[][] matrix = piece.possibleMoves();

            if(matrix[position.getRow()][position.getColumn()]){
                return true;
            }
        }

        return false;
    }

    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }

        List<ChessPiece> pieces = piecesOnTheBoard.stream().filter(x -> x.getColor() == color).collect(Collectors.toList());
        for (ChessPiece piece : pieces){
            boolean[][] matrix = piece.possibleMoves();

            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix[0].length; j++){
                    if(matrix[i][j]){
                        Position source = piece.getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        ChessPiece capturedPiece = makeMove(source, target);
                        boolean checkMate = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if(!checkMate){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private ChessPiece getKing(Color color){
        for (ChessPiece piece : piecesOnTheBoard){
            if(piece instanceof King && piece.getColor() == color){
                return piece;
            }
        }

        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position source = sourcePosition.toPosition();
        validateSourcePosition(source);
        return board.piece(source).possibleMoves();
    }

    private ChessPiece makeMove(Position source, Position target){

        ChessPiece capturedPiece = ((ChessPiece) board.removePiece(target)); // Downcasting
        board.placePiece(board.removePiece(source), target);

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    private void undoMove(Position source, Position target, ChessPiece capturedPiece){
        ChessPiece piece = (ChessPiece) board.removePiece(target);
        board.placePiece(piece, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
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
        piecesOnTheBoard.add(piece);
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void initialSetup(){

        placeNewPiece(new Rook(board, Color.BLACK), 'a', 8);
        placeNewPiece(new Rook(board, Color.BLACK), 'h', 8);
        placeNewPiece(new King(board, Color.BLACK), 'e', 8);

        placeNewPiece(new Rook(board, Color.WHITE), 'a', 1);
        placeNewPiece(new Rook(board, Color.WHITE), 'h', 1);
        placeNewPiece(new King(board, Color.WHITE), 'e', 1);

     }
}
