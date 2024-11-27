package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while(true){

            try{
                UI.clearScreen();
                UI.printMatch(chessMatch, capturedPieces);
                System.out.println();
                System.out.print("Source position: ");
                ChessPosition sourcePosition = UI.readChessPosition(sc);
                boolean[][] possibleMoves = chessMatch.possibleMoves(sourcePosition);
                UI.printBoard(chessMatch.getPiece(), possibleMoves);
                System.out.print("Target position: ");
                ChessPosition targetPosition = UI.readChessPosition(sc);
                ChessPiece capturedPiece = chessMatch.performChessMove(sourcePosition, targetPosition);
                capturedPieces.add(capturedPiece);
                System.out.println();
            } catch (ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine(); // Faz com que o programa espere o usuário apertar a tecla "Enter"
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

    }
}