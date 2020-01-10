package CHESSim.core;

import CHESSim.api.pieces.ChessPiece;
import CHESSim.api.pieces.ChessPiece.Color;

public interface ChessGUI {

	// board is passed with white starts being placed from [0][0] to [1][7] and black starts from [6][0] to [7][7]
	public void refresh(ChessPiece[][] board,Color turn);
	
	public void setCheck(Color color);
	
	public void showWarning(String warning);
	
}
