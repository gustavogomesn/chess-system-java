package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class chessMatch {
	private Board board;
	//true == WHITE, false == BLACK
	private boolean currentPlayer = true;
	private int turn = 1;

	public chessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		if(currentPlayer == true) {
			return Color.WHITE;
		}
		return Color.BLACK;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getRows()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
		
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if(getCurrentPlayer() != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours.");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the choosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("You can's move to this position.");
		}
	}

	private void nextTurn() {
		turn++;
		currentPlayer = !currentPlayer;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('a', 8, new Rook(board, Color.WHITE));
		placeNewPiece('h', 8, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.WHITE));

		placeNewPiece('a', 1, new Rook(board, Color.BLACK));
		placeNewPiece('h', 1, new Rook(board, Color.BLACK));
		placeNewPiece('d', 1, new King(board, Color.BLACK));

	}
	
	private Piece makeMove(Position source, Position target) {
		
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}

}
