package CHESSim.api.pieces;

import CHESSim.core.ChessBoard;
import CHESSim.core.Coordinate;

public class Knight extends ChessPiece{

	public Knight(Color color,Coordinate coord)
	{
		super(color,coord);
		this.character = 'N';
	}
	
	public boolean isMoveValid(Coordinate to,ChessBoard table)
	{
		
		
		int xMoveAbsolute = Math.abs(to.getX()-coord.getX());
		int yMoveAbsolute = Math.abs(to.getY()-coord.getY());
		
		return ((xMoveAbsolute ==2 &&yMoveAbsolute == 1)||(yMoveAbsolute==2&&xMoveAbsolute==1));//Felder auf die der Springer theoretisch springen kann
// sollte im Board ft werden
//				&& 
//				(board[to.x][to.y] ==null||board[to.x][to.y].color!=color);
	}
}
