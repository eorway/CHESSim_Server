package CHESSim.core;


import CHESSim.api.pieces.Bishop;
import CHESSim.api.pieces.ChessPiece;
import CHESSim.api.pieces.ChessPiece.Color;
import CHESSim.api.pieces.King;
import CHESSim.api.pieces.Knight;
import CHESSim.api.pieces.Pawn;
import CHESSim.api.pieces.Queen;
import CHESSim.api.pieces.Tower;

public class ChessBoard {
    ChessPiece[][] board = new ChessPiece[8][8];
    
    Color turn = Color.WHITE;
    String history ="";
    ChessGUI gui;
    King whiteKing;
    King blackKing;
    
    //Das Feld, auf das ein Bauer von weiß schlagen kann 
    Coordinate enpassanteWhite=null;
    //Das Feld, auf das ein Bauer von Schwarz schlagen kann
    Coordinate enpassanteBlack=null; 
    
    public ChessBoard () {
        reset();
    }
    
    public ChessBoard(ChessGUI gui) {
    	
    	this.gui = gui;
    	reset();
    	history += ";";
    	draw();
    }
    

    public void reset() {
    	
    	for(int i = 0;i<board.length;i++)
    	{
    		for(int j = 0;j<board[i].length;j++)
    		{
    			board[i][j] = null;
    		}
    	}
    	
    	
        // Initialize all pawns
        for (short x = 0; x < board.length; ++x) {
        	//derung! auf 1 muss Wei stehen
            board[x][1] = new Pawn(ChessPiece.Color.WHITE, new Coordinate(x, 1));
            board[x][6] = new Pawn(ChessPiece.Color.BLACK, new Coordinate(x, 6));
        }
        
        //initialize Knights
        board[1][0] = new Knight(ChessPiece.Color.WHITE,new Coordinate(1,0));
        board[6][0] = new Knight(ChessPiece.Color.WHITE,new Coordinate(6,0));
        board[1][7] = new Knight(ChessPiece.Color.BLACK,new Coordinate(1,7));
        board[6][7] = new Knight(ChessPiece.Color.BLACK,new Coordinate(6,7));
        
        //initialize bishops
        board[2][0] = new Bishop(ChessPiece.Color.WHITE,new Coordinate(2,0));
        board[5][0] = new Bishop(ChessPiece.Color.WHITE,new Coordinate(5,0));
        board[2][7] = new Bishop(ChessPiece.Color.BLACK,new Coordinate(2,7));
        board[5][7] = new Bishop(ChessPiece.Color.BLACK,new Coordinate(5,7));
        
        //initialize Towers
        board[0][0] = new Tower(ChessPiece.Color.WHITE,new Coordinate(0,0));
        board[7][0] = new Tower(ChessPiece.Color.WHITE,new Coordinate(7,0));
        board[0][7] = new Tower(ChessPiece.Color.BLACK,new Coordinate(0,7));
        board[7][7] = new Tower(ChessPiece.Color.BLACK,new Coordinate(7,7));
        
        //initialize Kings
        whiteKing = new King(ChessPiece.Color.WHITE,new Coordinate(4,0));
        board[4][0] = whiteKing;
        blackKing = new King(ChessPiece.Color.BLACK,new Coordinate(4,7));		
        board[4][7] = blackKing;
        
        //initialize Queens
        board[3][0] = new Queen(ChessPiece.Color.WHITE,new Coordinate(3,0));
        board[3][7] = new Queen(ChessPiece.Color.BLACK,new Coordinate(3,7));
        draw();
    }

    
    //TODO Bauern auf Grundlinie
    public boolean move(String fromStr, String toStr) {
        Coordinate from    = new Coordinate(fromStr);
        Coordinate to      = new Coordinate(toStr);
        boolean    capture = false;

        if(!from.isValid() || !to.isValid()) {
            System.err.println("Error: Coordinates are invalid");
            return false;
        }

        ChessPiece movingPiece = board[from.x][from.y];

        // check if there is a piece
        if (movingPiece == null) {
            System.err.println("Error: On these coordinates is no Piece.");
            return false;
        }

        //check if its your turn
        if(movingPiece.getColor() != turn)
        {
        	gui.showWarning("Error: It is not your turn");
        	return false;
        }       
        
        
        // if there is already a piece on the coordinates the moving piece would
        // like to go, it tries to capture it.
        if (board[to.x][to.y] != null)
            capture = true;

        // Wenn man wieder am Zug ist darf der Gegener im nchsten Zug nicht immernoch aufs gesetzte Feld schlagen, es msste ein neues Feld
        // gesetzt werden
        if(turn == Color.BLACK)
        {
        	enpassanteWhite = null;
        }
        else
        {
        	enpassanteBlack = null;
        }
        	
        // here we call the isMoveValid() method to determine if the move is valid
        // according to the logic of the specific piece.
        // Enpassantecoords will be set here
        if (movingPiece.isMoveValid(to, this)) {
            

            if (capture)
            {
            	if(board[to.x][to.y].getColor() != movingPiece.getColor())
            	{
            		 
            		board[to.x][to.y] = null;
            	}
            	else
            	{
            		gui.showWarning("Diese Figur kann nicht geschlagen werden, sie ist von der selben Farbe");
            		return false;
            	}
            }
            
            
            movingPiece.move(to);  
                        
            board[to.x][to.y] = board[from.x][from.y];
            board[from.x][from.y] = null;
            
            if(turn == Color.WHITE)
            {
            	turn = Color.BLACK;
            	if(to.equals(enpassanteWhite))
                {
                	board[to.x][4] = null;
                }
            	if(to.getY() == 7 && movingPiece.getCharacter() == 'P')
            	{
            		pawnChange(to);
            	}
            }
            else
            {
            	
            	turn = Color.WHITE;
            	if(to.equals(enpassanteBlack))
                {
                	board[to.x][3] = null;
                }
            	if(to.getY() == 0 && movingPiece.getCharacter() == 'P')
            	{
            		pawnChange(to);
            	}
            }
            
            
            
            history+=movingPiece.getCharacter()+from.toChessString()+to.toChessString()+";";
            this.draw();
            
            return true;
        }
        else {
        	// Wenn man wieder am Zug ist darf der Gegener im nhsten Zug nicht immernoch aufs gesetzte Feld schlagen, es mste ein neues Feld
            // gesetzt werden hier nochmal falls irrtmlicherweise versucht wurde mit einem Bauern 2 vor zu gehen
            if(turn == Color.BLACK)
            {
            	enpassanteBlack = null;
            }
            else
            {
            	enpassanteWhite = null;
            }
           //gui.showWarning("Error: This move is invalid");
            return false;
        }
    }

    public void captureEnPassante(Color color)
    {
    	if(color == Color.BLACK)
    	{
    		board[enpassanteBlack.x][enpassanteBlack.y-1] = null;
    	}
    	else
    	{
    		board[enpassanteWhite.x][enpassanteWhite.y+1] = null;
    	}
    }
    
    public void draw() {
    	if(gui != null)
    	{
    		ChessPiece[][] cloned = new ChessPiece[8][8];
    		
    		for(int i = 0;i <board.length ;i++)
    		{
    			for(int j = 0;j<board[i].length;j++)
    			{
    				cloned[i][j] = board[i][j];
    			}
    		}
    		
    		gui.refresh(cloned, turn);
    	}
    	else{System.out.println("GUI missing");}
    	
    	
    }


    
    public void setEnpassanteWhite(Coordinate enpassanteWhite) {
		this.enpassanteWhite = enpassanteWhite;
		
		
		
	}

	public void setEnpassanteBlack(Coordinate enpassanteBlack) {
		this.enpassanteBlack = enpassanteBlack;
	}

	public ChessPiece[][] getBoard() {
		return board;
	}

	public String getHistory() {
		return history;
	}

	public Coordinate getEnpassanteWhite() {
		return enpassanteWhite;
	}

	public Coordinate getEnpassanteBlack() {
		return enpassanteBlack;
	}
	
	/**
	 * checks if King of color is in Danger
	 * @param color
	 * @return
	 */
	public boolean checkDanger(Color color)
	{
		Boolean result = false;
		
		Coordinate kingField;
		if(color == Color.WHITE)
		{
			kingField = whiteKing.getCoord();
		}
		else
		{
			kingField = blackKing.getCoord();
		}
		
	
		for(ChessPiece[] currentRow:board)
		{
			for(ChessPiece x:currentRow )
			{
				if(x != null)
				{
					if(x.getColor() != color && x.isMoveValid(kingField, this))
					{
						result = true;						
					}
				}
			}
		}
		
		return false;
	}
	
	public void pawnChange(Coordinate coord)
	{
		
	}
	
}