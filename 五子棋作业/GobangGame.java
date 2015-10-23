import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.EnumSet;

 
public class GobangGame {
	
	
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "+") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					first=true;
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}
    /** 
     * 判断三连子,输入为用户最新下子位置，输出为若用户构成三连子的封堵位置,否则返回空
     */
    public int[] judgeThree(int posX,int posY){ //例举所有可能出现的连续三子的情况考虑，每一个if else为一种情况
    	String ico="●";
    	int i=posX-1,j=posX+1; //在落子点左右设置位置，方便考虑各种三连子情况
		int p=posY-1,v=posY+1;
		int i1=posX-2,j1=posX+2;
		int p1=posY-2,v1=posY+2;
		String[][] ch=chessboard.getBoard();;
		if(i1>-1){  //是否越界
			if(ch[i1][posY]==ico&&ch[i1+1][posY]==ico){   //一种三连子情况
				if(i1-1>-1&&ch[i1-1][posY]=="+"){  //一端可以封堵
					int[] result={i1-1,posY};
					return result;
				}	
				else if(ch[posX+1][posY]=="+"){   //由于前一端无法落子(越界或有子)，选择另一端
					int[] result={posX+1,posY};
					return result;
				}
				else 
					return null;  //无法封堵
			}	
	    }
		if(j1<22){
			if(ch[j1][posY]==ico&&ch[j1-1][posY]==ico){
				if(j1+1<22&&ch[j1+1][posY]=="+"){
					int[] result={j1+1,posY};
					return result;
				}	
				else if(ch[posX-1][posY]=="+"){
					int[] result={posX-1,posY};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(p1>-1){
			if(ch[posX][p1]==ico&&ch[posX][p1+1]==ico){
				if(p1-1>-1&&ch[posX][p1-1]=="+"){
					int[] result={posX,p1-1};
					return result;
				}	
				else if(ch[posX][posY+1]=="+"){
					int[] result={posX,posY+1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(v1<22){
			if(ch[posX][v1-1]==ico&&ch[posX][v1]==ico){
				if(v1+1<22&&ch[posX][v1+1]=="+"){
					int[] result={posX,v1+1};
					return result;
				}	
				else if(ch[posX][posY-1]=="+"){
					int[] result={posX,posY-1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(i1>-1&&p1>-1){
			if(ch[i1][p1]==ico&&ch[i1+1][p1+1]==ico){
				if(i1-1>-1&&p1-1>-1&&ch[i1-1][p1-1]=="+"){
					int[] result={i1-1,p1-1};
					return result;
				}	
				else if(ch[posX+1][posY+1]=="+"){
					int[] result={posX+1,posY+1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(j1<22&&p1>-1){
			if(ch[j1][p1]==ico&&ch[j1-1][p1+1]==ico){
				if(j1+1<22&&p1-1>-1&&ch[j1+1][p1-1]=="+"){
					int[] result={j1+1,p1-1};
					return result;
				}	
				else if(ch[posX-1][posY+1]=="+"){
					int[] result={posX-1,posY+1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(i1>-1&&v1<22){
			if(ch[i1][v1]==ico&&ch[i1+1][v1-1]==ico){
				if(i1-1>-1&&v1+1<22&&ch[i1-1][v1+1]=="+"){
					int[] result={i1-1,v1+1};
					return result;
				}	
				else if(ch[posX+1][posY-1]=="+"){
					int[] result={posX+1,posY-1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(j1<22&&v1<22){
			if(ch[j1][v1]==ico&&ch[j1-1][v1-1]==ico){
				if(j1+1<22&&v1+1<22&&ch[j1+1][v1+1]=="+"){
					int[] result={j1+1,v1+1};
					return result;
				}	
				else if(ch[posX-1][posY-1]=="+"){
					int[] result={posX-1,posY-1};
					return result;
				}
				else 
					return null;
			}	
	    }
		 if(i>-1&&j<22){
			 //System.out.println("33333");
		if(ch[i][posY]==ico&&ch[j][posY]==ico){
			if(i-1>-1&&ch[i-1][posY]=="+"){
				int[] result={i-1,posY};
				return result;
			}	
			else if(ch[j+1][posY]=="+"){
				int[] result={j+1,posY};
				return result;
			}
			else
			return null;
		}	
    }
		if(p>-1&&v<22){
			if(ch[posX][p]==ico&&ch[posX][v]==ico){
				if(p-1>-1&&ch[posX][p-1]=="+"){
					int[] result={posX,p-1};
					return result;
				}	
				else if(ch[posX][v+1]=="+"){
					int[] result={posX,v+1};
					return result;
				}
				else
					return null;
			}	
	    }
		if(p>-1&&i>-1&&v<22&&j<22){
			if(ch[i][p]==ico&&ch[j][v]==ico){
				if(p-1>-1&&i-1>-1&&ch[i-1][p-1]=="+"){
					int[] result={i-1,p-1};
					return result;
				}	
				else if(ch[j+1][v+1]=="+"){
					int[] result={j+1,v+1};
					return result;
				}
				else
					return null;
			}	
	    }
		if(p>-1&&i>-1&&v<22&&j<22){
			if(ch[i][v]==ico&&ch[j][p]==ico){
				if(i-1>-1&&v+1<22&&ch[i-1][v+1]=="+"){
					int[] result={i-1,v+1};
					return result;
				}	
				else if(ch[j+1][p-1]=="+"){
					int[] result={j+1,p-1};
					return result;
				}
				else 
					return null;
			}	
	    }
			return null;
    }
    /**
	 * 获取在posX和posY坐标落子后的连子长度,和判断输赢算法相似，改造下即可
	 */
    public int getLong(int posX,int posY,String ico){
    	int length;  //存储最长连子长度 
    	int i=posX-1,j=posX+1;
		int p=posY-1,v=posY+1;
		int i1=posX-1,j1=posX+1;
		int p1=posY-1,v1=posY+1;
		int i2=posX-1,j2=posX+1;
		int p2=posY-1,v2=posY+1;
		String[][] ch=new String[22][22];
		ch=chessboard.getBoard();
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(j==22)    //边界判断
				break;
			if(ch[j][posY]==ico)
				j++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(i==-1)    //边界判断
				break;
			if(ch[i][posY]==ico)
				i--;
			else
				break;
		}
		j--;
		i++;
		length=j-i+1;   //行连子个数
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(v==22)   //边界判断
				break;
			if(ch[posX][v]==ico)
				v++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(p==-1)    //边界判断
				break;
			if(ch[posX][p]==ico)
				p--;
			else
				break;
		}
		v--;
		p++;
		if((v-p+1)>length)     
			length=v-p+1;     //更新最长长度
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(j1==22||v1==22)  //边界判断
				break;
			if(ch[j1][v1]==ico){
				j1++;
				v1++;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(i1==-1||p1==-1)  //边界判断
				break;
			if(ch[i1][p1]==ico&&p1>-1&&i1>-1){
				i1--;
				p1--;
			}
			else
				break;
		}
		j1--;
		i1++;
		if((j1-i1+1)>length)   
			length=j1-i1+1;    //更新最长长度
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(j2==22||p2==-1)   //边界判断
				break;
			if(ch[j2][p2]==ico){
				j2++;
				p2--;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(i2==-1||v2==22)  //边界判断
				break;
			if(ch[i2][v2]==ico){
				i2--;
				v2++;
			}
			else
				break;
		}
		j2--;
		i2++;
		if((j2-i2+1)>length)   //更新最长长度
			length=j2-i2+1;
		return length;
    }
	/**
	 * 计算机随机下棋
	 */
    int oldLong1,oldLong2;  //记录用户和电脑的最长连子长度
    int maxPsx,maxPsy;   //记录用户得到最大连子长度的历史位置
    int oldPosX,oldPosY;//记录电脑上一次落子位置
    int maxPosX,maxPosY;//记录电脑得到最大连子长度的历史位置
    boolean first=true;  //判断是否第一次落子
	public int[] computerDo() {
		String[][] board = chessboard.getBoard();
		if(first){  //第一步下棋，初始化
			if(board[11][11]=="+"){
				//System.out.print("111");
				int[] result = {11, 11};
				first=false;
				oldPosX=11;
				oldPosY=11;
				maxPosX=11;
				maxPosY=11;
				oldLong1=1;
				oldLong2=1;
				return result;
			}
			else{
				int[] result = {10, 10};
				first=false;
				oldPosX=10;
				oldPosY=10;
				maxPosX=10;
				maxPosY=10;
				oldLong1=1;
				oldLong2=1;
				return result;
			}		
		}
		int L1=getLong(posX,posY,"●");  //获取用户输入一个数后的最新连子长度
		if(L1>oldLong1){   //若比历史长度长，则更新用户历史长度
			oldLong1=L1;  
			maxPsx=posX;
			maxPsy=posY;
		}
		int L2=getLong(oldPosX,oldPosY,"○");  //获取电脑上次落子后的最新连子长度
		if(L2>oldLong2) {   //若比历史长度长，则更新电脑历史长度和历史最大连子长度位置
			oldLong2=L2; 
			maxPosX=oldPosX;
			maxPosY=oldPosY;
		}
		if(oldLong2==4){    //若电脑连子长度为4，一端被用户封堵,下另一端获胜，以下if语句考虑各种可能情况
			if(maxPosX-1>-1&&maxPosX+4<22){
				if(board[maxPosX-1][maxPosY]=="●"&&board[maxPosX+1][maxPosY]=="○"&&board[maxPosX+2][maxPosY]=="○"&&board[maxPosX+3][maxPosY]=="○"&&board[maxPosX+4][maxPosY]=="+")
					return new int[]{maxPosX+4,maxPosY};
			}
			if(maxPosX+1<22&&maxPosX-4>-1){
				if(board[maxPosX+1][maxPosY]=="●"&&board[maxPosX-1][maxPosY]=="○"&&board[maxPosX-2][maxPosY]=="○"&&board[maxPosX-3][maxPosY]=="○"&&board[maxPosX-4][maxPosY]=="+")
					return new int[]{maxPosX-4,maxPosY};
			}
			if(maxPosY-1>-1&&maxPosY+4<22){
				if(board[maxPosX][maxPosY-1]=="●"&&board[maxPosX][maxPosY+1]=="○"&&board[maxPosX][maxPosY+2]=="○"&&board[maxPosX][maxPosY+3]=="○"&&board[maxPosX][maxPosY+4]=="+")
					return new int[]{maxPosX,maxPosY+4};
			}
			if(maxPosY+1<22&&maxPosY-4>-1){
				if(board[maxPosX][maxPosY+1]=="●"&&board[maxPosX][maxPosY-1]=="○"&&board[maxPosX][maxPosY-2]=="○"&&board[maxPosX][maxPosY-3]=="○"&&board[maxPosX][maxPosY-4]=="+")
					return new int[]{maxPosX,maxPosY-4};
			}
			if(maxPosY+1<22&&maxPosX+1<22&&maxPosY-4>-1&&maxPosX-4>-1){
				if(board[maxPosX+1][maxPosY+1]=="●"&&board[maxPosX-1][maxPosY-1]=="○"&&board[maxPosX-2][maxPosY-2]=="○"&&board[maxPosX-3][maxPosY-3]=="○"&&board[maxPosX-4][maxPosY-4]=="+")
					return new int[]{maxPosX-4,maxPosY-4};
			}
			if(maxPosY-1>-1&&maxPosX-1>-1&&maxPosY+4<22&&maxPosX+4<22){
				if(board[maxPosX-1][maxPosY-1]=="●"&&board[maxPosX+1][maxPosY+1]=="○"&&board[maxPosX+2][maxPosY+2]=="○"&&board[maxPosX+3][maxPosY+3]=="○"&&board[maxPosX+4][maxPosY+4]=="+")
					return new int[]{maxPosX+4,maxPosY+4};
			}
			if(maxPosY-1>-1&&maxPosX+1<22&&maxPosY+4<22&&maxPosX-4>-1){
				if(board[maxPosX+1][maxPosY-1]=="●"&&board[maxPosX-1][maxPosY+1]=="○"&&board[maxPosX-2][maxPosY+2]=="○"&&board[maxPosX-3][maxPosY+3]=="○"&&board[maxPosX-4][maxPosY+4]=="+")
					return new int[]{maxPosX-4,maxPosY+4};
			}
			if(maxPosX-1>-1&&maxPosY+1<22&&maxPosX+4<22&&maxPosY-4>-1){
				if(board[maxPosX-1][maxPosY+1]=="●"&&board[maxPosX+1][maxPosY-1]=="○"&&board[maxPosX+2][maxPosY-2]=="○"&&board[maxPosX+3][maxPosY-3]=="○"&&board[maxPosX+4][maxPosY-4]=="+")
					return new int[]{maxPosX+4,maxPosY-4};
			}
		}
		if(oldLong2==3||oldLong2==4){    //电脑三或四连子被用户阻断的情况,更新最长长度，以下if语句考虑各种可能情况
			if(maxPosX-1>-1&&maxPosX+3<22){
				if(board[maxPosX-1][maxPosY]=="●"&&board[maxPosX+1][maxPosY]=="○"&&board[maxPosX+2][maxPosY]=="○")
					oldLong2=1;
			}
			if(maxPosX-1>-1&&maxPosX+3<22&&maxPosY-3>-1&&maxPosY+1<22){
				if(board[maxPosX-1][maxPosY+1]=="●"&&board[maxPosX+1][maxPosY-1]=="○"&&board[maxPosX+2][maxPosY-2]=="○")
					oldLong2=1;
			}
			if(maxPosX-1>-1&&maxPosX+3<22&&maxPosY-3>-1&&maxPosY-1>-1){
				if(board[maxPosX-1][maxPosY-1]=="●"&&board[maxPosX+1][maxPosY+1]=="○"&&board[maxPosX+2][maxPosY+2]=="○")
					oldLong2=1;
			}
			if(maxPosX+1<22&&maxPosX-3>-1&&maxPosY+3<22&&maxPosY-1>-1){
				if(board[maxPosX+1][maxPosY-1]=="●"&&board[maxPosX-1][maxPosY+1]=="○"&&board[maxPosX-2][maxPosY+2]=="○")
					oldLong2=1;
			}
			if(maxPosX+1<22&&maxPosX-3>-1&&maxPosY-3>-1&&maxPosY+1<22){
				if(board[maxPosX+1][maxPosY+1]=="●"&&board[maxPosX-1][maxPosY-1]=="○"&&board[maxPosX-2][maxPosY-2]=="○")
					oldLong2=1;
			}
			if(maxPosX+1<22&&maxPosX-3>-1){
				if(board[maxPosX+1][maxPosY]=="●"&&board[maxPosX-1][maxPosY]=="○"&&board[maxPosX-2][maxPosY]=="○")
					oldLong2=1;
			}
			if(maxPosY-1>-1&&maxPosY+3<22){
				if(board[maxPosX][maxPosY-1]=="●"&&board[maxPosX][maxPosY+1]=="○"&&board[maxPosX][maxPosY+2]=="○")
					oldLong2=1;
			}
			if(maxPosY+1<22&&maxPosY-3>-1){
				if(board[maxPosX][maxPosY+1]=="●"&&board[maxPosX][maxPosY-1]=="○"&&board[maxPosX][maxPosY-2]=="○")
					oldLong2=1;
			}
			if(maxPosX+3<22){
				if(board[maxPosX+3][maxPosY]=="●"&&board[maxPosX+1][maxPosY]=="○"&&board[maxPosX+2][maxPosY]=="○")
					oldLong2=1;
			}
			if(maxPosX-3>-1){
				if(board[maxPosX-3][maxPosY]=="●"&&board[maxPosX-1][maxPosY]=="○"&&board[maxPosX-2][maxPosY]=="○")
					oldLong2=1;
			}
			if(maxPosX+3<22&&maxPosY+3<22){
				if(board[maxPosX+3][maxPosY+3]=="●"&&board[maxPosX+1][maxPosY+1]=="○"&&board[maxPosX+2][maxPosY+2]=="○")
					oldLong2=1;
			}
			if(maxPosX-3>-1&&maxPosY-3>-1){
				if(board[maxPosX-3][maxPosY-3]=="●"&&board[maxPosX-2][maxPosY-2]=="○"&&board[maxPosX-1][maxPosY-1]=="○")
					oldLong2=1;
			}
			if(maxPosX-3>-1&&maxPosY+3<22){
				if(board[maxPosX-3][maxPosY+3]=="●"&&board[maxPosX-1][maxPosY+1]=="○"&&board[maxPosX-2][maxPosY+2]=="○")
					oldLong2=1;
			}
			if(maxPosX+3<22&&maxPosY-3>-1){
				if(board[maxPosX+3][maxPosY-3]=="●"&&board[maxPosX+2][maxPosY-2]=="○"&&board[maxPosX+1][maxPosY-1]=="○")
					oldLong2=1;
			}
			if(maxPosY-3>-1){
				if(board[maxPosX][maxPosY-3]=="●"&&board[maxPosX][maxPosY-2]=="○"&&board[maxPosX][maxPosY-1]=="○")
					oldLong2=1;
			}
			if(maxPosY+3<22){
				if(board[maxPosX][maxPosY+3]=="●"&&board[maxPosX][maxPosY+2]=="○"&&board[maxPosX][maxPosY+1]=="○")
					oldLong2=1;
			}
			//System.out.print(oldLong2);
		}
		
		if(oldLong2<4){//以下8个if else语句为若电脑下一步不能赢，则防守"●●+●●"的情况
		if(posX+3<22&&posY-3>-1&&posX-1>-1&&posY+1<22){  //越界判断
			if(board[posX+3][posY-3]=="●"&&board[posX+2][posY-2]=="●"&&board[posX+1][posY-1]=="+"&&board[posX-1][posY+1]=="●"){  
				return new int[]{posX+1,posY-1};
			}
			}
			if(posX-3>-1&&posY+3<22&&posX+1<22&&posY-1>-1){  //越界判断
				if(board[posX-3][posY+3]=="●"&&board[posX-2][posY+2]=="●"&&board[posX-1][posY+1]=="+"&&board[posX+1][posY-1]=="●"){  
					return new int[]{posX-1,posY+1};
				}
				}
			if(posX+3<22&&posY+3<22&&posX-1>-1&&posY-1>-1){  //越界判断
				if(board[posX+3][posY+3]=="●"&&board[posX+2][posY+2]=="●"&&board[posX+1][posY+1]=="+"&&board[posX-1][posY-1]=="●"){  
					return new int[]{posX+1,posY+1};
				}
				}
			if(posX+1<22&&posY+1<22&&posX-3>-1&&posY-3>-1){  //越界判断
				if(board[posX-3][posY-3]=="●"&&board[posX-2][posY-2]=="●"&&board[posX-1][posY-1]=="+"&&board[posX+1][posY+1]=="●"){  
					return new int[]{posX-1,posY-1};
				}
				}
			if(posX+1<22&&posX-3>-1){  //越界判断
				if(board[posX-3][posY]=="●"&&board[posX-2][posY]=="●"&&board[posX-1][posY]=="+"&&board[posX+1][posY]=="●"){  
					return new int[]{posX-1,posY};
				}
				}
			if(posX+3<22&&posX-1>-1){  //越界判断
				if(board[posX+3][posY]=="●"&&board[posX+2][posY]=="●"&&board[posX+1][posY]=="+"&&board[posX-1][posY]=="●"){  
					return new int[]{posX+1,posY};
				}
				}
			if(posY+3<22&&posY-1>-1){  //越界判断
				if(board[posX][posY+3]=="●"&&board[posX][posY+2]=="●"&&board[posX][posY+1]=="+"&&board[posX][posY-1]=="●"){  
					return new int[]{posX,posY+1};
				}
				}
			if(posY+1<22&&posY-3>-1){  //越界判断
				if(board[posX][posY-3]=="●"&&board[posX][posY-2]=="●"&&board[posX][posY-1]=="+"&&board[posX][posY+1]=="●"){  
					return new int[]{posX,posY-1};
				}
				}
		}
		
		if(oldLong2<3){  //在电脑最大连子长度小于3的时候，需要防守"●●+●"这种情况，以下if else例举所有24种情况判断
			if(posX+4<22&&posY-4>-1&&posX-1>-1&&posY+1<22){  //越界判断
			if(board[posX+4][posY-4]=="+"&&board[posX+3][posY-3]=="●"&&board[posX+2][posY-2]=="●"&&board[posX+1][posY-1]=="+"&&board[posX-1][posY+1]=="+"){  
				return new int[]{posX+1,posY-1};
			}
			}
			if(posX-4>-1&&posY+4<22&&posX+1<22&&posY-1>-1){  //越界判断
				if(board[posX-4][posY+4]=="+"&&board[posX-3][posY+3]=="●"&&board[posX-2][posY+2]=="●"&&board[posX-1][posY+1]=="+"&&board[posX+1][posY-1]=="+"){  
					return new int[]{posX-1,posY+1};
				}
				}
			if(posX+4<22&&posY+4<22&&posX-1>-1&&posY-1>-1){  //越界判断
				if(board[posX+4][posY+4]=="+"&&board[posX+3][posY+3]=="●"&&board[posX+2][posY+2]=="●"&&board[posX+1][posY+1]=="+"&&board[posX-1][posY-1]=="+"){  
					return new int[]{posX+1,posY+1};
				}
				}
			if(posX+1<22&&posY+1<22&&posX-4>-1&&posY-4>-1){  //越界判断
				if(board[posX-4][posY-4]=="+"&&board[posX-3][posY-3]=="●"&&board[posX-2][posY-2]=="●"&&board[posX-1][posY-1]=="+"&&board[posX+1][posY+1]=="+"){  
					return new int[]{posX-1,posY-1};
				}
				}
			if(posX+1<22&&posX-4>-1){  //越界判断
				if(board[posX-4][posY]=="+"&&board[posX-3][posY]=="●"&&board[posX-2][posY]=="●"&&board[posX-1][posY]=="+"&&board[posX+1][posY]=="+"){  
					return new int[]{posX-1,posY};
				}
				}
			if(posX+4<22&&posX-1>-1){  //越界判断
				if(board[posX+4][posY]=="+"&&board[posX+3][posY]=="●"&&board[posX+2][posY]=="●"&&board[posX+1][posY]=="+"&&board[posX-1][posY]=="+"){  
					return new int[]{posX+1,posY};
				}
				}
			if(posY+4<22&&posY-1>-1){  //越界判断
				if(board[posX][posY+4]=="+"&&board[posX][posY+3]=="●"&&board[posX][posY+2]=="●"&&board[posX][posY+1]=="+"&&board[posX][posY-1]=="+"){  
					return new int[]{posX,posY+1};
				}
				}
			if(posY+1<22&&posY-4>-1){  //越界判断
				if(board[posX][posY-4]=="+"&&board[posX][posY-3]=="●"&&board[posX][posY-2]=="●"&&board[posX][posY-1]=="+"&&board[posX][posY+1]=="+"){  
					return new int[]{posX,posY-1};
				}
				}
			
			if(posX+3<22&&posY-3>-1&&posX-2>-1&&posY+2<22){  //越界判断
				if(board[posX+3][posY-3]=="+"&&board[posX+2][posY-2]=="●"&&board[posX+1][posY-1]=="+"&&board[posX-1][posY+1]=="●"&&board[posX-2][posY+2]=="+"){  
					return new int[]{posX+1,posY-1};
				}
				}
				if(posX-3>-1&&posY+3<22&&posX+2<22&&posY-2>-1){  //越界判断
					if(board[posX-3][posY+3]=="+"&&board[posX-2][posY+2]=="●"&&board[posX-1][posY+1]=="+"&&board[posX+1][posY-1]=="●"&&board[posX+2][posY-2]=="+"){  
						return new int[]{posX-1,posY+1};
					}
					}
				if(posX+2<22&&posY+2<22&&posX-3>-1&&posY-3>-1){  //越界判断
					if(board[posX+2][posY+2]=="+"&&board[posX+1][posY+1]=="●"&&board[posX-3][posY-3]=="+"&&board[posX-1][posY-1]=="+"&&board[posX-2][posY-2]=="●"){  
						return new int[]{posX-1,posY-1};
					}
					}
				if(posX+3<22&&posY+3<22&&posX-2>-1&&posY-2>-1){  //越界判断
					if(board[posX-2][posY-2]=="+"&&board[posX+2][posY+2]=="●"&&board[posX-1][posY-1]=="●"&&board[posX+1][posY+1]=="+"&&board[posX+3][posY+3]=="+"){  
						return new int[]{posX+1,posY+1};
					}
					}
				if(posX+2<22&&posX-3>-1){  //越界判断
					if(board[posX-3][posY]=="+"&&board[posX-2][posY]=="●"&&board[posX-1][posY]=="+"&&board[posX+1][posY]=="●"&&board[posX+2][posY]=="+"){  
						return new int[]{posX-1,posY};
					}
					}
				if(posX+3<22&&posX-2>-1){  //越界判断
					if(board[posX+3][posY]=="+"&&board[posX+2][posY]=="●"&&board[posX+1][posY]=="+"&&board[posX-1][posY]=="●"&&board[posX-2][posY]=="+"){  
						return new int[]{posX+1,posY};
					}
					}
				if(posY+3<22&&posY-2>-1){  //越界判断
					if(board[posX][posY+3]=="+"&&board[posX][posY+2]=="●"&&board[posX][posY-1]=="●"&&board[posX][posY+1]=="+"&&board[posX][posY-2]=="+"){  
						return new int[]{posX,posY+1};
					}
					}
				if(posY+2<22&&posY-3>-1){  //越界判断
					if(board[posX][posY-3]=="+"&&board[posX][posY-2]=="●"&&board[posX][posY+1]=="●"&&board[posX][posY-1]=="+"&&board[posX][posY+2]=="+"){  
						return new int[]{posX,posY-1};
					}
					}
				
				if(posX+4<22&&posY-4>-1&&posX-1>-1&&posY+1<22){  //越界判断
					if(board[posX+4][posY-4]=="+"&&board[posX+3][posY-3]=="●"&&board[posX+2][posY-2]=="+"&&board[posX+1][posY-1]=="●"&&board[posX-1][posY+1]=="+"){  
						return new int[]{posX+2,posY-2};
					}
					}
					if(posX-4>-1&&posY+4<22&&posX+1<22&&posY-1>-1){  //越界判断
						if(board[posX-4][posY+4]=="+"&&board[posX-3][posY+3]=="●"&&board[posX-2][posY+2]=="+"&&board[posX-1][posY+1]=="●"&&board[posX+1][posY-1]=="+"){  
							return new int[]{posX-2,posY+2};
						}
						}
					if(posX+4<22&&posY+4<22&&posX-1>-1&&posY-1>-1){  //越界判断
						if(board[posX+4][posY+4]=="+"&&board[posX+3][posY+3]=="●"&&board[posX+2][posY+2]=="+"&&board[posX+1][posY+1]=="●"&&board[posX-1][posY-1]=="+"){  
							return new int[]{posX+2,posY+2};
						}
						}
					if(posX+1<22&&posY+1<22&&posX-4>-1&&posY-4>-1){  //越界判断
						if(board[posX-4][posY-4]=="+"&&board[posX-3][posY-3]=="●"&&board[posX-2][posY-2]=="+"&&board[posX-1][posY-1]=="●"&&board[posX+1][posY+1]=="+"){  
							return new int[]{posX-2,posY-2};
						}
						}
					if(posX+1<22&&posX-4>-1){  //越界判断
						if(board[posX-4][posY]=="+"&&board[posX-3][posY]=="●"&&board[posX-2][posY]=="+"&&board[posX-1][posY]=="●"&&board[posX+1][posY]=="+"){  
							return new int[]{posX-2,posY};
						}
						}
					if(posX+4<22&&posX-1>-1){  //越界判断
						if(board[posX+4][posY]=="+"&&board[posX+3][posY]=="●"&&board[posX+2][posY]=="+"&&board[posX+1][posY]=="●"&&board[posX-1][posY]=="+"){  
							return new int[]{posX+2,posY};
						}
						}
					if(posY+4<22&&posY-1>-1){  //越界判断
						if(board[posX][posY+4]=="+"&&board[posX][posY+3]=="●"&&board[posX][posY+2]=="+"&&board[posX][posY+1]=="●"&&board[posX][posY-1]=="+"){  
							return new int[]{posX,posY+2};
						}
						}
					if(posY+1<22&&posY-4>-1){  //越界判断
						if(board[posX][posY-4]=="+"&&board[posX][posY-3]=="●"&&board[posX][posY-2]=="+"&&board[posX][posY-1]=="●"&&board[posX][posY+1]=="+"){  
							return new int[]{posX,posY-2};
						}
						}
		}
		
		if(oldLong2>=oldLong1||(oldLong2<oldLong1&&oldLong1<3)||judgeThree(posX,posY)==null){ //若电脑连子长度不小于用户或者小于用户但是用户最大连子长度没到3或者找不到封堵位置的时候，可以直接找得到最大连子长度的位置落子
			//System.out.print("333");
			int q1 = -1,q2=-1,q3=-1,q4=-1,q5=-1,q6=-1,q7=-1,q8=-1;   
		    int max=0;  //以下if else语句完成在落子周围8个位置判断哪个位置落子得到最长连子长度，直接返回这个位置
			if(board[maxPosX-1][maxPosY] == "+"&&(maxPosX-1)>-1){  //不越界且可填子
				 q1=getLong(maxPosX-1,maxPosY,"○");  //获取连子长度
				 if(q1>max)
					 max=q1;
				 //System.out.print(q1);
			}
			if(board[maxPosX-1][maxPosY-1] == "+"&&(maxPosX-1)>-1&&(maxPosY-1)>-1){
				 q2=getLong(maxPosX-1,maxPosY-1,"○");
				 if(q2>max)
					 max=q2;
			}
			if(board[maxPosX][maxPosY-1] == "+"&&(maxPosY-1)>-1){
				 q3=getLong(maxPosX,maxPosY-1,"○");
				 if(q3>max)
					 max=q3;
			}
			if(board[maxPosX+1][maxPosY-1] == "+"&&(maxPosX+1)<22&&(maxPosY-1)>-1){
				 q4=getLong(maxPosX+1,maxPosY-1,"○");
				 if(q4>max)
					 max=q4;
			}
			if(board[maxPosX+1][maxPosY] == "+"&&(maxPosX+1)<22){
				 q5=getLong(maxPosX+1,maxPosY,"○");
				 if(q5>max)
					 max=q5;
			}
			if(board[maxPosX+1][maxPosY+1] == "+"&&(maxPosX+1)<22&&(maxPosY+1)<22){
				 q6=getLong(maxPosX+1,maxPosY+1,"○");
				 if(q6>max)
					 max=q6;
			}
			if(board[maxPosX][maxPosY+1] == "+"&&(maxPosY+1)<22){
				 q7=getLong(maxPosX,maxPosY+1,"○");
				 if(q7>max)
					 max=q7;
			}
			if(board[maxPosX-1][maxPosY+1] == "+"&&(maxPosY-1)>-1&&(maxPosY+1)<22){
				 q8=getLong(maxPosX-1,maxPosY+1,"○");
				 if(q8>max)
					 max=q8;
			}
			if(max==q1){
				maxPosX--;
				int[] result={maxPosX,maxPosY};  //更新获取到最大连子长度的位置
				//System.out.print(maxPosX+"omg"+maxPosY);
				oldPosX=maxPosX;   //记录上一次落子位置
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q2){
				maxPosX--;
				maxPosY--;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q3){
				maxPosY--;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q4){
				maxPosX++;
				maxPosY--;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q5){
				maxPosX++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q6){
				maxPosX++;
				maxPosY++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q7){
				maxPosY++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;	
				return result;
			}	
			if(max==q8){
				maxPosX--;
				maxPosY++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
		}	
		//System.out.print("222");
			return judgeThree(posX,posY);   //不属于上述情况的话必然是需要拦截用户
				
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
	public boolean isWon(int posX, int posY, String ico) {
		int i=posX-1,j=posX+1;
		int p=posY-1,v=posY+1;
		int i1=posX-1,j1=posX+1;
		int p1=posY-1,v1=posY+1;
		int i2=posX-1,j2=posX+1;
		int p2=posY-1,v2=posY+1;
		String[][] ch=new String[22][22];
		ch=chessboard.getBoard();
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(j==22)    //边界判断
				break;
			if(ch[j][posY]==ico)  //位置移动
				j++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(i==-1)    //边界判断
				break;
			if(ch[i][posY]==ico)
				i--;
			else
				break;
		}
		j--;
		i++;
		if(j-i+1>=5)   //对行判断是否5连子
			return true;
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(v==22)   //边界判断
				break;
			if(ch[posX][v]==ico)
				v++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(p==-1)    //边界判断
				break;
			if(ch[posX][p]==ico)
				p--;
			else
				break;
		}
		v--;
		p++;
		if(v-p+1>=5)   //对列判断是否5连子
			return true;
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(j1==22||v1==22)  //边界判断
				break;
			if(ch[j1][v1]==ico){
				j1++;
				v1++;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(i1==-1||p1==-1)  //边界判断
				break;
			if(ch[i1][p1]==ico&&p1>-1&&i1>-1){
				i1--;
				p1--;
			}
			else
				break;
		}
		j1--;
		i1++;
		if(j1-i1+1>=5)   //对左上到右下的对角线判断是否5连子
			return true;
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(j2==22||p2==-1)   //边界判断
				break;
			if(ch[j2][p2]==ico){
				j2++;
				p2--;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //找不断的最远位置
			if(i2==-1||v2==22)  //边界判断
				break;
			if(ch[i2][v2]==ico){
				i2--;
				v2++;
			}
			else
				break;
		}
		j2--;
		i2++;
		if(j2-i2+1>=5)   //对左下到右上的对角线判断是否5连子
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}

