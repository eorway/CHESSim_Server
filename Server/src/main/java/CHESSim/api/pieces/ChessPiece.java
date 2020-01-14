package CHESSim.api.pieces;

import CHESSim.core.Game;
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

	public abstract boolean checkMove(Coordinate coord, Game table);
	
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
