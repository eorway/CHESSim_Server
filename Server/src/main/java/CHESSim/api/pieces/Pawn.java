package CHESSim.api.pieces;

import CHESSim.core.ChessBoard;
import CHESSim.core.Coordinate;

public class Pawn extends ChessPiece{

	
	
	
	/**
	 * Vorsicht! die Koordinate wird hier nicht auf Stimmigkeit ueberprueft
	 * @param coord
	 * @param color
	 */
	public Pawn(Color color,Coordinate coord)
	{
		super(color,coord);
		this.character = 'P';
	}
	
	
	
	public boolean isMoveValid(Coordinate to, ChessBoard table)
	{
		boolean result = false;
		int xMove = to.getX()-coord.getX();
		int yMove = to.getY()-coord.getY();
		int direction;
		ChessPiece[][] board = table.getBoard();
		
		if(color == ChessPiece.Color.BLACK)
		{
			direction = -1;
		}
		else
		{
			direction = 1;
		}
		
		
		if(yMove == direction)
		{
			if(board[to.getX()][to.getY()] == null && xMove == 0)
			{	
				result = true;
			}
			if( Math.abs(xMove) ==1)
			{
				if(board[to.getX()][to.getY()]!=null )
				{
				
					result = true;
				}
				//keine Figur auf dem schrgen Feld, darf ich enpassante Schlagen
				else if(color == Color.BLACK && to.equals(table.getEnpassanteBlack()))
				{
					result = true;
				}
				else if(color == Color.WHITE && to.equals(table.getEnpassanteWhite()))
				{
					result = true;
				}
			}
		}
		
		if(yMove == 2*direction)
		{
			if((coord.getY()==1||coord.getY()==6)&&board[coord.getX()][coord.getY()+direction]==null)
			{
				result = true;
				if(color == Color.BLACK)
				{
					table.setEnpassanteWhite(new Coordinate(coord.getX(),to.getY()-direction));
				}
				else
				{
					table.setEnpassanteBlack(new Coordinate(coord.getX(),to.getY()-direction));
				}
			}
		}
		
		
		
		return result;
	}
	
	public void move(Coordinate to)
	{
		
		coord=to;
	}
	
}
