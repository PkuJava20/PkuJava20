package Module;

public class Player {

	private ChessType chessType;
	
	public Player(ChessType chessType)
	{
		this.chessType = chessType;
	}
	public ChessType getChessType(){
		return chessType;
	}
}