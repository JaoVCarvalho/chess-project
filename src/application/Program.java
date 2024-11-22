package application;

import chess.ChessMatch;
import chess.ChessPosition;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);

        while(true){

            UI.printBoard(chessMatch.getPiece());
            System.out.println();
            System.out.print("Source position: ");
            ChessPosition sourcePosition = UI.readChessPosition(sc);
            System.out.print("Target position: ");
            ChessPosition targetPosition = UI.readChessPosition(sc);
            chessMatch.performChessMove(sourcePosition, targetPosition);
            System.out.println();
        }

    }
}