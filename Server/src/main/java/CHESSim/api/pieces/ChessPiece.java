package CHESSim.api.pieces;

import CHESSim.core.ChessBoard;
import CHESSim.core.Coordinate;

public abstract class ChessPiece {
	
	public static enum Color{WHITE,BLACK};
	
	Color color;
	Coordinate coord;
	char character;
	
	public ChessPiece(Color color,Coordinate coord)
	{
		this.color = color;
		this.coord = coord;
	}
	
	public void move(Coordinate newCoord)
	{
		coord=newCoord;
	}
	
	public Color getColor() {
		return color;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public char getCharacter() {
		return character;
	}

	public abstract boolean isMoveValid(Coordinate coord, ChessBoard table);
	
	public boolean equals(ChessPiece comparison)
	{
		boolean result = false;
		if(comparison.getColor() == this.getColor() && comparison.getClass() == this.getClass() && comparison.getCoord().equals(this.getCoord()))
		{
			result = true;
		}
		
		
		return result;
	}
	
}
