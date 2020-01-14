package CHESSim.api.pieces;

import CHESSim.core.Game;
import CHESSim.core.Coordinate;

public class King extends ChessPiece{

	public King(Color color, Coordinate coord)
	{
		super(color,coord);
		this.character = 'K';
	}
	
	public boolean checkMove(Coordinate to,Game table)
	{
		boolean result = false;
		
		//zwischenfelder prfen hier obsolet weil nur ein Feld ziehbar
		//TODO darf nicht  
		//TODO Rochade
		if(Math.abs(to.getX()-coord.getX()) <= 1 && Math.abs(to.getY()-coord.getY()) <= 1)
		{
			return true;
		}
		if(checkRochade(to,table))
		{
			if(to.getX()-coord.getX()>0)
			{
				table.getBoard()[7][coord.getY()].move(new Coordinate(coord.getX(),5));
				ChessPiece temp =table.getBoard()[7][coord.getY()];
				table.getBoard()[7][coord.getY()] = null;
				table.getBoard()[5][coord.getY()] = temp; 
			}
			else
			{
				table.getBoard()[0][coord.getY()].move(new Coordinate(coord.getX(),3));
				ChessPiece temp =table.getBoard()[0][coord.getY()];
				table.getBoard()[0][coord.getY()] = null;
				table.getBoard()[3][coord.getY()] = temp;
			}
			return true;
		}
		
		return result;
	}
	
	public boolean checkRochade(Coordinate to, Game table)
	{
		boolean result = false;
		//TODO history, bedrohtes Feld
		
		if(coord.getX() == 4 && (coord.getY() == 0 || coord.getY() == 7))
			{
				if((to.getX()-coord.getX()) == 2 && Tower.isMoveValid(new Coordinate(7,coord.getY()), new Coordinate(coord.getX()+1,to.getY()), table))
				{
					
					result = true;
				}
				else if((to.getX()-coord.getX()) == -2 && Tower.isMoveValid(new Coordinate(0,coord.getY()), new Coordinate(coord.getX()-1,to.getY()), table))
				{
					result = true;
				}
			}
		
		return result;
	}
}
