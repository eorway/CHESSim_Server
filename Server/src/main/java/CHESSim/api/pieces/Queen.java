package CHESSim.api.pieces;

import CHESSim.core.Game;
import CHESSim.core.Coordinate;

public class Queen extends ChessPiece{

	public Queen(Color color,Coordinate coord)
	{
		super(color,coord);
		this.character='Q';
	}
	
	
	public boolean checkMove(Coordinate to,Game board)
	{
		return Tower.isMoveValid(coord, to, board)||Bishop.isMoveValid(coord, to, board);
	}
}
