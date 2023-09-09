package application;


import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.chessMatch;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		chessMatch chessMatch = new chessMatch();
		
		while(true) {
			UI.printBoard(chessMatch.getPieces()) ;
			System.out.println("\nSource: ");
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println("\nTarget: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
		
		
		
	}

}
