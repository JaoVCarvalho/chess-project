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

        while(!chessMatch.isCheckMate()){

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

                if(capturedPiece != null){
                    capturedPieces.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/k/R/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while (!type.equals("B") && !type.equals("K") && !type.equals("R") & !type.equals("Q")) {
                        System.out.print("Invalid value! Enter piece for promotion (B/k/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }

                chessMatch.updateCheckAndCheckMateStatus();

                System.out.println();
            } catch (ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine(); // Faz com que o programa espere o usuário apertar a tecla "Enter"
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        UI.clearScreen();
        UI.printBoard(chessMatch.getPiece());
        System.out.println();
        System.out.println("CHECKMATE!");
        System.out.println("Winner: " + chessMatch.getCurrentPlayer());
    }
}