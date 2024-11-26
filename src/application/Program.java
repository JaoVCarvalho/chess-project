package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);

        while(true){

            try{
                UI.clearScreen();
                UI.printBoard(chessMatch.getPiece());
                System.out.println();
                System.out.print("Source position: ");
                ChessPosition sourcePosition = UI.readChessPosition(sc);
                boolean[][] possibleMoves = chessMatch.possibleMoves(sourcePosition);
                UI.printBoard(chessMatch.getPiece(), possibleMoves);
                System.out.print("Target position: ");
                ChessPosition targetPosition = UI.readChessPosition(sc);
                chessMatch.performChessMove(sourcePosition, targetPosition);
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