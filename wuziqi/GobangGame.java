import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	//定义棋盘状态
	private int[][][][] states = new int[17][17][2][8];
	//记录已经下过的棋
	private point[]   record=new point[225];
	//记录已经下了子的个数
	private int len=0;
	// 定义棋盘
	private Chessboard chessboard;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	
	//初始化期盼状态
	public void statesinit(){
		for(int i=0;i<17;i++)
			for(int j=0;j<17;j++)
			for(int ico=0;ico<2;ico++)
			for(int k=0;k<8;k++){
			   states[i][j][ico][k]=0;
			  
			}
		for(int i=0;i<225;i++){
			record[i]=new point();
		}
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
			//记录黑子
			record[len].x=posX;
			record[len].y=posY;
			record[len].col=chessman;
			len++;
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],chessman);
				//记录白字
				record[len].x=computerPosArr[0];
				record[len].y=computerPosArr[1];
				record[len].col=chessman;
				len++;
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
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			//System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
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
	 * 计算机随机下棋
	 */
	/*
	public int[] computerDo() {
		
		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;
	}
    */
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
		System.out.println(posX+":"+posY+":"+ico);
		int i,j,col,k;
        if(ico.equals(Chessman.BLACK.getChessman()))  col=0;     //黑子用col=0表示，白字用col=1表示；
        else col=1;        
        i=posX+1;
        j=posY+1;
        states[i][j][col][0]=states[i-1][j-1][col][0]+1;  //左斜
        states[i][j][col][1]=states[i-1][j][col][1]+1;      //上下
        states[i][j][col][2]=states[i-1][j+1][col][2]+1;  //右斜
        states[i][j][col][3]=states[i][j-1][col][3]+1;  
        states[i][j][col][4]=states[i][j+1][col][4]+1;  //左斜
        states[i][j][col][5]=states[i+1][j-1][col][5]+1;      //上下
        states[i][j][col][6]=states[i+1][j][col][6]+1;  //右斜
        states[i][j][col][7]=states[i+1][j+1][col][7]+1;  
        shuaxin(i,j, ico);
        int max=states[i][j][col][0]+states[i][j][col][7-0]-1;
        for(int t=1;t<4;t++){
        if(states[i][j][col][t]+states[i][j][col][7-t]-1>max)  max= states[i][j][col][t]+states[i][j][col][7-t]-1;      
        }
        if(max>=5)    return true;
        else    return false;
	}
//判断下标有没有越界并且值为空
	public boolean bianjievalid(int i,int j){
		String[][] board = chessboard.getBoard();
		if(i>=0&&i<25&&j>=0&&j<25&&board[i][j]=="+")   return true;
		return false;
	}
	
//取最大预算值
	public int maxValue(int t, int k, String ico) {
	    //System.out.println("MAXVALUE:"+t+":"+k+":"+ico);
		int i,j,col;
        if(ico.equals(Chessman.BLACK.getChessman()))  col=0;     //黑子用col=0表示，白字用col=1表示；
        else col=1;        
        i=t+1;
        j=k+1;
        int max1=0;
        states[i][j][col][0]=states[i-1][j-1][col][0]+1;  //左斜
        states[i][j][col][1]=states[i-1][j][col][1]+1;      //上下
        states[i][j][col][2]=states[i-1][j+1][col][2]+1;  //右斜
        states[i][j][col][3]=states[i][j-1][col][3]+1;  
        states[i][j][col][4]=states[i][j+1][col][4]+1;  //左斜
        states[i][j][col][5]=states[i+1][j-1][col][5]+1;      //上下
        states[i][j][col][6]=states[i+1][j][col][6]+1;  //右斜
        states[i][j][col][7]=states[i+1][j+1][col][7]+1;  
       // for(int f=1;f<4;f++){
        	 max1=states[i][j][col][0]+states[i][j][col][7-0]-1;
            for(int t1=1;t1<4;t1++){
            if(states[i][j][col][t1]+states[i][j][col][7-t1]-1>max1)  max1= states[i][j][col][t1]+states[i][j][col][7-t1]-1;      
         //  System.out.println(max1);
            }
       // }
       for(int i1=0;i1<8;i1++)
        		states[i][j][col][i1]=0;	
        return max1;
	}

 //周围8个点的最佳情况
 public int[] zhouwei(int x,int y,String col){
	 int px=0,py=0;
	 int max=0;
	 if(bianjievalid(x-1,y-1)&&(maxValue(x-1, y-1, col)>max)){max= maxValue(x-1, y-1, col);px=x-1;py=y-1;} 
	 if(bianjievalid(x-1,y)&&(maxValue(x-1, y, col)>max)){max=maxValue(x-1, y, col);px=x-1;py=y;} 
	 if(bianjievalid(x-1,y+1)&&(maxValue(x-1, y+1, col)>max)){max=maxValue(x-1, y+1, col);px=x-1;py=y+1;} 
	 if(bianjievalid(x,y-1)&&(maxValue(x, y-1, col)>max)){max=maxValue(x, y-1, col);px=x;py=y-1;} 
	 if(bianjievalid(x,y+1)&&( maxValue(x, y+1, col)>max)){max=maxValue(x, y+1, col);px=x;py=y+1;} 
	 if(bianjievalid(x+1,y-1)&&( maxValue(x+1, y-1, col)>max)){max=maxValue(x+1, y-1, col);px=x+1;py=y-1;} 
	 if(bianjievalid(x+1,y)&&(maxValue(x+1, y, col)>max)){max=maxValue(x+1, y, col);px=x+1;py=y;} 
	 if(bianjievalid(x+1,y+1)&&(maxValue(x+1, y+1, col)>max)){max=maxValue(x+1, y+1, col);px=x+1;py=y+1;} 
	 int []result={px,py,max};
	 return result;
 }
 
 //标志走过的下标
 public boolean zouguo(int i,int j){
	 int x=i-1;
	 int y=j-1;
	 String[][] board = chessboard.getBoard();
	 if(x>=0&&x<15&&y>=0&&y<15&&!board[x][y].equals("+"))  return true;
	 return false;
 }
 public boolean setvalid(int i,int j){
	 if(i>=0&&i<15&&j>=0&&j<15)   return true;
	 return false;
 }
 //刷新状态数组
 public void shuaxin(int k1,int t,String ico){
	 int i=k1;
	 int j=t;
	 int x=k1-1;
	 int y=t-1;
	 String[][] board = chessboard.getBoard();
	 int col;
	 if(ico.equals(Chessman.BLACK.getChessman()))  col=0;     //黑子用col=0表示，白字用col=1表示；
     else col=1;  
	 int k=1;
	// System.out.println("SHUAN XIN");
	 while( setvalid(x-k,y-k)&&board[x-k][y-k].equals(board[x][y])){  //左上
		 states[i-k][j-k][col][7]= states[i-k+1][j-k+1][col][7]+1;
		// System.out.println(0);
		 k++;
		// System.out.println(k);
	 }  
	// System.out.println(k-1);
	 k=1;
	 while(setvalid(x-k,y)&&board[x-k][y]==board[x][y]){  //正上
		 states[i-k][j][col][6]= states[i-k+1][j][col][6]+1;
		// System.out.println(1);
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x-k,y+k)&&board[x-k][y+k]==board[x][y]){  //右上
		 states[i-k][j+k][col][5]= states[i-k+1][j+k-1][col][5]+1;
		// System.out.println(2);
		 k++;
	 }  
	 //System.out.println(k-1);
	  k=1;
	 while(setvalid(x,y-k)&&board[x][y-k]==board[x][y]){  //左
		 states[i][j-k][col][4]= states[i][j-k+1][col][4]+1;
		// System.out.println(3);
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x,y+k)&&board[x][y+k]==board[x][y]){  //右
		 states[i][j+k][col][3]= states[i][j+k-1][col][3]+1;	 
		 k++;
	 }
	 //System.out.println(k-1);
	 k=1;
	 while(setvalid(x+k,y-k)&&board[x+k][y-k]==board[x][y]){  //左下
		 states[i+k][j-k][col][2]= states[i+k-1][j-k+1][col][2]+1;
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x+k,y)&&board[x+k][y]==board[x][y]){  //正下
		 states[i+k][j][col][1]= states[i+k-1][j][col][1]+1;
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x+k,y+k)&&board[x+k][y+k]==board[x][y]){  //右下
		 states[i+k][j+k][col][0]= states[i+k-1][j+k-1][col][0]+1;
		 k++;
	 }  
	// System.out.println(k-1);
	 //System.out.println("SHUAN XIN");
 }
//  人机博弈
public int[] computerDo(){
		int i,j;//遍历已经下过的黑白子
		int t,k;//作为中间量
		int px = 0,py=0,cx=0,cy=0;//标志最佳攻防点
		//int max=0;
		int max2=0;
		int max1=0;//max1守，max2攻
		int flagi,flagj;
		//int []tmp=new int[3];
		//守
		for(i=0;i<len;i+=2){
			t=record[i].x;
			k=record[i].y;
			int []tmp=zhouwei(t,k,Chessman.BLACK.getChessman());			
			if(tmp[2]>=max1){
				px=tmp[0];
				py=tmp[1];
				max1=tmp[2];
			}
		}
		//攻
		for(j=1;j<len;j+=2){
			t=record[j].x;
			k=record[j].y;
			int []tmp=zhouwei(t,k,Chessman.WHITE.getChessman());
			if(tmp[2]>=max2){
				cx=tmp[0];
				cy=tmp[1];
				max2=tmp[2];
			}
		}
		if(max1>max2){
			flagi=px;
			flagj=py;
		}
		else{
			flagi=cx;
			flagj=cy;
		}
		int[] result = { flagi, flagj };
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
		GobangGame gb = new GobangGame(new Chessboard());
		gb.statesinit();
		gb.start();
	}
}
