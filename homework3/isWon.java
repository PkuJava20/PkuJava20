public boolean isWon(int posX, int posY, String ico) {
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
					iBingo = ico.equals(chessboard.getBoard()[posX-i][posY]);
				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posX+j>=chessboard.BOARD_SIZE)
					jBingo = false;
				else
					jBingo =ico.equals(chessboard.getBoard()[posX+j][posY]);
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
					iBingo = ico.equals(chessboard.getBoard()[posX][posY-i]);

				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posY+j>=chessboard.BOARD_SIZE)
					jBingo = false;
				else
					jBingo = ico.equals(chessboard.getBoard()[posX][posY+j]);

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
					iBingo = ico.equals(chessboard.getBoard()[posX-i][posY-i]);
				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posX+j>=chessboard.BOARD_SIZE||posY+j>=chessboard.BOARD_SIZE)
					jBingo = false;
				else
					jBingo = ico.equals(chessboard.getBoard()[posX+j][posY+j]);
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
				if(posX-i<0||posY+i>=chessboard.BOARD_SIZE)
					iBingo = false;
				else
					iBingo = ico.equals(chessboard.getBoard()[posX-i][posY+i]);
				if(!iBingo)
					i--;
			}
			if(jBingo)
			{
				j++;
				if(posY-j<0||posX+j>=chessboard.BOARD_SIZE)
					jBingo = false;
				else
					jBingo = ico.equals(chessboard.getBoard()[posX+j][posY-j]);
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