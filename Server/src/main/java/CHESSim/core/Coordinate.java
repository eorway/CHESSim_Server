package CHESSim.core;

public class Coordinate {

	int x;
	int y;
	


	public Coordinate(int x ,int y)
	{
		this.x = x;
		this.y = y;
		
	}
	
	
	public int getX() {
		return x;
	}




	public int getY() {
		return y;
	}



	/**
	 * 
	 * @param Schachposition erst Buchstabe, dann Zahl bsp. f3
	 */
	public Coordinate(String chessCoordinate)
	{
		char xCoordinateChar = chessCoordinate.toLowerCase().charAt(0);
		String yCoordinateString = chessCoordinate.substring(1);
		
		
		
		x=(int)xCoordinateChar-97;
		
		y=Integer.parseInt(yCoordinateString)-1;
		
	}
	
	
	public boolean isValid()
	{
		return x>=0 && x<8 && y>=0 && y<8;
	}
	
	
	public String toString()
	{
		return "Koordinate x: " +x+ " y: "+y; 
	}
	
	
	
	public String toChessString()
	{
		String coordString = new String(new char[]{(char)(x+97),(char)(y+49)});
		return coordString;
	}
	
	public boolean equals(Coordinate comparison)
	{
		if(comparison == null)
		{
			return false;
			
		}
		return comparison.x == x && comparison.y == y;
	}
	
	
	
}
