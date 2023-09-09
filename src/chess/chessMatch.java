package chess;

import boardGame.Board;
import boardGame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class chessMatch {
	private Board board;
	
	public chessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getRows()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 7));
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 7));
		
	}
	
}
