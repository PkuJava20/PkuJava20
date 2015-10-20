/**
	 * 计算机随机下棋
	 */
	public int[][] AnalyzeChessBoard()
	{
		int[][][] anaBoard= new int[Chessboard.BOARD_SIZE][Chessboard.BOARD_SIZE][8] ;
	    int deltax[] = {0, 1, 1, 1, 0, -1, -1, -1};  
	    int deltay[] = {-1, -1, 0, 1, 1, 1, 0, -1};  
	    int posx,posy;
	    int temp=0;
	    for(int i=0;i<Chessboard.BOARD_SIZE;i++)
	    {
	    	for(int j=0;j<Chessboard.BOARD_SIZE;j++)
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
	    				if ( posx >=Chessboard.BOARD_SIZE || posx < 0 || posy >=Chessboard.BOARD_SIZE || posy < 0 )  
	    					break;
	    				if(chessboard.getBoard()[i][j].equals(Chessman.BLANK.getChessman())){
                        	if( chessboard.getBoard()[posx][posy].equals(Chessman.BLACK.getChessman()))
                        	    temp++;  
                            else  
                              break;  
                        }
	    			}
	    			anaBoard[i][j][k]=temp;
	    		}
	    	}
	    }
	    int [][] valueMatrix = new int[Chessboard.BOARD_SIZE][Chessboard.BOARD_SIZE];
	    int winvalue;
	    for ( int i = 0; i < Chessboard.BOARD_SIZE; i++ ){ 
	        for ( int j = 0; j < Chessboard.BOARD_SIZE; j++ ) 
	        {  
	        	winvalue = 0;  
	                for (int k = 0; k < 4; k++ )  // direction  
	                {  
	                    if ( anaBoard[i][j][k] + anaBoard[i][j][k+4] >= 4 )  
	                    	winvalue += 10000;  
	                    else if ( anaBoard[i][j][k] + anaBoard[i][j][k+4] == 3 )  
	                    	winvalue += 1000;   
	                    else if ( anaBoard[i][j][k] + anaBoard[i][j][k+4] == 2 )  
	                    	winvalue += 100;  
	                    else if ( anaBoard[i][j][k] + anaBoard[i][j][k+4] == 1 )  
	                    	winvalue += 10;  
	                }  
	                valueMatrix[i][j] = winvalue;  // black  
	          }
	    }
		//for()
		return valueMatrix;
	}
	public int[] computerDo() {
		
		//int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		//int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		//String[][] board = chessboard.getBoard();
		//while (board[posX][posY] != "十") {
		//	posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		//	posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		//}
		//int[] result = { posX, posY };
		//return result;
		int x=0,y=0;
		int[][] valueMatrix =AnalyzeChessBoard();
		int maxvalue=0;
		 for ( int i = 0; i < Chessboard.BOARD_SIZE; i++ ){  // col  
		        for ( int j = 0; j < Chessboard.BOARD_SIZE; j++ )  // row  
		        {  
		        	if(valueMatrix[i][j]>maxvalue)
		        	{
		        		maxvalue =valueMatrix[i][j];
		        		x=i;
		        		y=j;
		        	}
		 		}
		 }
		int[] result = { x,y };
		System.out.printf("%d   %d ",x,y);
		System.out.println();
		return result;
	}