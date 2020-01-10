package CHESSim.api.pieces;

import CHESSim.core.ChessBoard;
import CHESSim.core.Coordinate;

public class Tower extends ChessPiece {

	
	public Tower(Color color,Coordinate coord)
	{
		super(color,coord);
		this.character = 'R';
	}
	
	public static boolean isMoveValid(Coordinate from, Coordinate to,ChessBoard table)
	{
		ChessPiece[][] board = table.getBoard();
		int xMove = to.getX()-from.getX();
		
		int yMove = to.getY()-from.getY();
		int length=Math.abs(yMove+xMove);
		//bewegungsrichtungfen
		boolean result= xMove==0^yMove ==0;
		
		if(result)
		{
			ChessPiece checkPiece=null;
			for(int i=1;i<length;++i)
			{
				
				if(yMove != 0)
					{
						checkPiece = board[from.getX()][from.getY()+(yMove/Math.abs(yMove)*i)];
					}
				else
				{
					checkPiece = board[from.getX()+(xMove/Math.abs(xMove)*i)][from.getY()];
				}
				if(checkPiece != null)
				{
					return false;
				}
			}
		}
		return result;
		
	}
	
	@Override
	public	boolean isMoveValid(Coordinate to, ChessBoard table) {
		
		
		return isMoveValid(coord, to,table);
	}

}
