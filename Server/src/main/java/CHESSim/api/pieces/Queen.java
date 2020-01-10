package CHESSim.api.pieces;

import CHESSim.core.ChessBoard;
import CHESSim.core.Coordinate;

public class Queen extends ChessPiece{

	public Queen(Color color,Coordinate coord)
	{
		super(color,coord);
		this.character='Q';
	}
	
	
	public boolean isMoveValid(Coordinate to,ChessBoard board)
	{
		return Tower.isMoveValid(coord, to, board)||Bishop.isMoveValid(coord, to, board);
	}
}
