package application;

import chess.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] pieces){
        // pieces.length (atributo) retorna o número de linhas matriz
        for (int i = 0; i < pieces.length; i++){
            System.out.print(8 - i + " ");
            // pieces[0].length (atributo) retorna o número de colunas da matriz
            for (int j = 0; j < pieces[0].length; j++){
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }

        System.out.println("  a b c d e f g h");

    }

    public static void printPiece(ChessPiece piece){
        if (piece != null){
            System.out.print(piece);
        } else {
            System.out.print("-");
        }
        System.out.print(" ");
    }
}
