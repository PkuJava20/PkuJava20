package Module;

public class ChessBoard {
	
	private static ChessBoard instance=null;
	private ChessType[][] board ;

	private int lastRow =-1;
	private int lastColumn = -1;
	public ChessBoard()
	{
		 board = new ChessType[Config.Board_Size][Config.Board_Size];
		 initBoard();
	}
	public static ChessBoard getInstance()
	{
		if (instance ==null)
			instance = new ChessBoard();
		return instance;
	}
	public void initBoard()
	{
		for(int i=0;i<Config.Board_Size;i++)
		{
			for(int j=0;j<Config.Board_Size;j++)
				board[i][j]=ChessType.BLANK;
		}
	}
	public void setBoard(int row,int column,ChessType type)
	{
		board[row][column] = type;
		lastRow = row;
		lastColumn = column;
	}
	public ChessType[][] getBoard()
	{
		return board;
	}
	public boolean isValid(int row,int column)
	{
		if(board[row][column]!=ChessType.BLANK)
			return false;
		return true;
	}
}
