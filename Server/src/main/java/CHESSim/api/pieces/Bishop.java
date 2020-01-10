package CHESSim.api.pieces;

import CHESSim.core.ChessBoard;
import CHESSim.core.Coordinate;

public class Bishop extends ChessPiece{

	
	public Bishop(Color color,Coordinate coord)
	{
		super(color,coord);
		this.character = 'B';
	}
	
	public static boolean isMoveValid(Coordinate from,Coordinate to,ChessBoard table)
	{
		
		ChessPiece[][] board = table.getBoard();
		int vectorx = to.getX()-from.getX();
		int vectory = to.getY()-from.getY();
		
		int xDirection = 0;
		int yDirection = 0;
		boolean result = false;
		
		if(vectorx == 0|| vectory==0)
		{
			return false;
		}
		xDirection = vectorx/Math.abs(vectorx);
		yDirection = vectory/Math.abs(vectory);
		result = Math.abs(vectorx/vectory) ==1;
			
		if(result)
		{
			//ist eine Figur im Weg?
			for(int i = 1;i<Math.abs(vectorx);++i)
			{
				if(board[from.getX()+i*xDirection][from.getY()+i*yDirection]!=null)
				{
					return false;
				}
			}
		}
		
		return result;
	}
	
	
	public boolean isMoveValid(Coordinate to,ChessBoard table)
	{
		
		return isMoveValid(coord,to,table);
	}
	
	
	
	
	
}
