package Controller;



import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import Module.ChessBoard;
import Module.ChessType;
import Module.Player;
import Module.Config;
import View.MainFrame;

public class GameManager {

	private Player my = new Player(ChessType.WHITE);
	private Player computer = new Player(ChessType.BLACK);
	public ChessType turn = ChessType.BLACK;
	private static GameManager instance = null;
	private MainFrame frame;
	private boolean isOver = false;
	private final int WIN_COUNT = 5;
	public GameManager()
	{
		frame = new MainFrame();
	}
	
	public static GameManager getInstance()
	{
		if(instance == null)
			instance = new GameManager();
		return instance;
	}
	
	public void init()
	{
		turn = ChessType.BLACK;
		frame.initUI();
	}
	public void ChangeTurn()
	{
		if(turn == ChessType.BLACK)
			turn = ChessType.WHITE;
		else
			turn = ChessType.BLACK;
	}
	
	public void start()  {
		ChessBoard.getInstance().initBoard();
		init();
	}
	public void goNext(int posX,int posY)
	{
		boolean isWon = this.isWon(posX,posY,turn);
		if(!isWon)
		{
			if(turn ==ChessType.BLACK)
			{
				ChangeTurn();
				int[] position = this.computerDo();
				ChessBoard.getInstance().setBoard(position[0], position[1], ChessType.WHITE);
				this.frame.boardPanel.repaint();
				this.goNext(position[0], position[1]);
			}
			else
			{
				ChangeTurn();
			}
		}
		else
		{
			int result =0;
			if(turn ==ChessType.BLACK)
				result=JOptionPane.showConfirmDialog(null,"恭喜，你赢了！再来一局？", "消息提示",JOptionPane.YES_NO_OPTION);
				//JOptionPane.showMessageDialog(null, "恭喜，你赢了！", "消息提示", JOptionPane.INFORMATION_MESSAGE);
			else
				result=JOptionPane.showConfirmDialog(null,"很遗憾，你输了！再来一局？", "消息提示",JOptionPane.YES_NO_OPTION);
				//JOptionPane.showMessageDialog(null, "很遗憾，你输了！", "消息提示", JOptionPane.INFORMATION_MESSAGE);
			if(result ==JOptionPane.YES_OPTION)
				GameManager.getInstance().start();
		}
	}
	public int[] AnalyzeChessBoard()
	{
		int[][][] anaBoardBlack= new int[Config.Board_Size][Config.Board_Size][8] ;
		int[][][] anaBoardWhite= new int[Config.Board_Size][Config.Board_Size][8] ;
	    int deltax[] = {0, 1, 1, 1, 0, -1, -1, -1};  
	    int deltay[] = {-1, -1, 0, 1, 1, 1, 0, -1};  
	    int posx,posy;
	    int temp=0;
	    for(int i=0;i<Config.Board_Size;i++)
	    {
	    	for(int j=0;j<Config.Board_Size;j++)
	    	{
	    		for(int k =0;k<8;k++)
	    		{
	    			posx=i;
	    			posy=j;
	    			temp =0;
	    			for(int count=0;count<5;count++)
	    			{
	    				posx += deltax[k];  
	    				posy += deltay[k];  
	    				if ( posx >=Config.Board_Size || posx < 0 || posy >=Config.Board_Size || posy < 0 )  
	    					break;
	    				if(ChessBoard.getInstance().getBoard()[i][j].equals(ChessType.BLANK)){
                        	if( ChessBoard.getInstance().getBoard()[posx][posy].equals(ChessType.BLACK))
                        	    temp++;  
                            else  
                              break;  
                        }
	    			}
	    			anaBoardBlack[i][j][k]=temp;
	    		}
	    	}
	    } 
	    for(int i=0;i<Config.Board_Size;i++)
	    {
	    	for(int j=0;j<Config.Board_Size;j++)
	    	{
	    		for(int k =0;k<8;k++)
	    		{
	    			posx=i;
	    			posy=j;
	    			temp =0;
	    			for(int count=0;count<5;count++)
	    			{
	    				posx += deltax[k];  
	    				posy += deltay[k];  
	    				if ( posx >=Config.Board_Size || posx < 0 || posy >=Config.Board_Size || posy < 0 )  
	    					break;
	    				if(ChessBoard.getInstance().getBoard()[i][j].equals(ChessType.BLANK)){
                        	if( ChessBoard.getInstance().getBoard()[posx][posy].equals(ChessType.WHITE))
                        	    temp++;  
                            else  
                              break;  
                        }
	    			}
	    			anaBoardWhite[i][j][k]=temp;
	    		}
	    	}
	    }
	    int [][] valueMatrixBlack = new int[Config.Board_Size][Config.Board_Size];
	    int [][] valueMatrixWhite = new int[Config.Board_Size][Config.Board_Size];
	    int winvalue;
	    for ( int i = 0; i < Config.Board_Size; i++ ){ 
	        for ( int j = 0; j < Config.Board_Size; j++ ) 
	        {  
	        	winvalue = 0;  
	                for (int k = 0; k < 4; k++ )  // direction  
	                {  
	                    if ( anaBoardBlack[i][j][k] + anaBoardBlack[i][j][k+4] >= 4 )  
	                    	winvalue += 10000;  
	                    else if ( anaBoardBlack[i][j][k] + anaBoardBlack[i][j][k+4] == 3 )  
	                    	winvalue += 1000;   
	                    else if ( anaBoardBlack[i][j][k] + anaBoardBlack[i][j][k+4] == 2 )  
	                    	winvalue += 100;  
	                    else if ( anaBoardBlack[i][j][k] + anaBoardBlack[i][j][k+4] == 1 )  
	                    	winvalue += 10;  
	                }  
	                valueMatrixBlack[i][j] = winvalue;  // black  
	          }
	    }
	    for ( int i = 0; i < Config.Board_Size; i++ ){ 
	        for ( int j = 0; j < Config.Board_Size; j++ ) 
	        {  
	        	winvalue = 0;  
	                for (int k = 0; k < 4; k++ )  // direction  
	                {  
	                    if ( anaBoardWhite[i][j][k] + anaBoardWhite[i][j][k+4] >= 4 )  
	                    	winvalue += 10000;  
	                    else if ( anaBoardWhite[i][j][k] + anaBoardWhite[i][j][k+4] == 3 )  
	                    	winvalue += 1000;   
	                    else if ( anaBoardWhite[i][j][k] + anaBoardWhite[i][j][k+4] == 2 )  
	                    	winvalue += 100;  
	                    else if ( anaBoardWhite[i][j][k] + anaBoardWhite[i][j][k+4] == 1 )  
	                    	winvalue += 10;  
	                }  
	                valueMatrixWhite[i][j] = winvalue;  // black  
	          }
	    }
	    int maxvalue=0;
		int x=0,y=0;
		 for ( int i = 0; i < Config.Board_Size; i++ ){  // col  
		        for ( int j = 0; j < Config.Board_Size; j++ )  // row  
		        {  
		        	if(valueMatrixBlack[i][j]>maxvalue)
		        	{
		        		maxvalue =valueMatrixBlack[i][j];
		        		x=i;
		        		y=j;
		        	}
		 		}
		 }
		 for ( int i = 0; i < Config.Board_Size; i++ ){  // col  
		        for ( int j = 0; j < Config.Board_Size; j++ )  // row  
		        {  
		        	if(valueMatrixWhite[i][j]>maxvalue)
		        	{
		        		maxvalue =valueMatrixWhite[i][j];
		        		x=i;
		        		y=j;
		        	}
		 		}
		 }

		int[] result = { x,y };
		return result;
	}
	public int[] computerDo() {
		int[] result  =AnalyzeChessBoard();
		return result;
	}

	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, ChessType ico) {
		int i=0,j=0;
		boolean iBingo = true;
		boolean jBingo = true;
		while(i+j<WIN_COUNT-1)
		{
			if(iBingo)
			{
				i++;
				if(posX-i<0)
					iBingo = false;
				else
					iBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX-i][posY]);
				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posX+j>=Config.Board_Size)
					jBingo = false;
				else
					jBingo =ico.equals(ChessBoard.getInstance().getBoard()[posX+j][posY]);
				if(!jBingo)
					j--;
			}
			if((!iBingo)&&(!jBingo))
				break;
		}
		if(i+j==WIN_COUNT-1)
			return true;
		else
		{
			i=0;j=0;
			iBingo =true; jBingo = true;
		}
		while(i+j<WIN_COUNT-1)
		{
			if(iBingo)
			{
				i++;
				if(posY-i<0)
					iBingo = false;
				else
					iBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX][posY-i]);

				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posY+j>=Config.Board_Size)
					jBingo = false;
				else
					jBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX][posY+j]);

				if(!jBingo)
					j--;
			}
			if((!iBingo)&&(!jBingo))
				break;
		}
		if(i+j==WIN_COUNT-1)
			return true;
		else
		{
			i=0;j=0;
			iBingo =true; jBingo = true;
		}
		while(i+j<WIN_COUNT-1)
		{
			if(iBingo)
			{
				i++;
				if(posX-i<1||posY-i<1)
					iBingo = false;
				else
					iBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX-i][posY-i]);
				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posX+j>=Config.Board_Size||posY+j>=Config.Board_Size)
					jBingo = false;
				else
					jBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX+j][posY+j]);
				if(!jBingo)
					j--;
			}
			if((!iBingo)&&(!jBingo))
				break;
		}
		if(i+j==WIN_COUNT-1)
			return true;
		else
		{
			i=0;j=0;
			iBingo =true; jBingo = true;
		}
		while(i+j<WIN_COUNT-1)
		{
			if(iBingo)
			{
				i++;
				if(posX-i<0||posY+i>=Config.Board_Size)
					iBingo = false;
				else
					iBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX-i][posY+i]);
				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posY-j<0||posX+j>=Config.Board_Size)
					jBingo = false;
				else
					jBingo = ico.equals(ChessBoard.getInstance().getBoard()[posX+j][posY-j]);
				if(!jBingo)
					j--;
			}
			if((!iBingo)&&(!jBingo))
				break;
		}
		if(i+j==WIN_COUNT-1)
			return true;
		return false;
	}
	public static void main(String[] args) throws Exception {

		GameManager.getInstance().init(); 
		GameManager.getInstance().start();
	}
}
