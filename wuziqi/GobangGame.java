import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	//��������״̬
	private int[][][][] states = new int[17][17][2][8];
	//��¼�Ѿ��¹�����
	private point[]   record=new point[225];
	//��¼�Ѿ������ӵĸ���
	private int len=0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	
	//��ʼ������״̬
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
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "+") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			//��¼����
			record[len].x=posX;
			record[len].y=posY;
			record[len].col=chessman;
			len++;
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// �����ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],chessman);
				//��¼����
				record[len].x=computerPosArr[0];
				record[len].y=computerPosArr[1];
				record[len].col=chessman;
				len++;
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			//System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * ������������
	 */
	/*
	public int[] computerDo() {
		
		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "ʮ") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX, posY };
		return result;
	}
    */
	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) {
		System.out.println(posX+":"+posY+":"+ico);
		int i,j,col,k;
        if(ico.equals(Chessman.BLACK.getChessman()))  col=0;     //������col=0��ʾ��������col=1��ʾ��
        else col=1;        
        i=posX+1;
        j=posY+1;
        states[i][j][col][0]=states[i-1][j-1][col][0]+1;  //��б
        states[i][j][col][1]=states[i-1][j][col][1]+1;      //����
        states[i][j][col][2]=states[i-1][j+1][col][2]+1;  //��б
        states[i][j][col][3]=states[i][j-1][col][3]+1;  
        states[i][j][col][4]=states[i][j+1][col][4]+1;  //��б
        states[i][j][col][5]=states[i+1][j-1][col][5]+1;      //����
        states[i][j][col][6]=states[i+1][j][col][6]+1;  //��б
        states[i][j][col][7]=states[i+1][j+1][col][7]+1;  
        shuaxin(i,j, ico);
        int max=states[i][j][col][0]+states[i][j][col][7-0]-1;
        for(int t=1;t<4;t++){
        if(states[i][j][col][t]+states[i][j][col][7-t]-1>max)  max= states[i][j][col][t]+states[i][j][col][7-t]-1;      
        }
        if(max>=5)    return true;
        else    return false;
	}
//�ж��±���û��Խ�粢��ֵΪ��
	public boolean bianjievalid(int i,int j){
		String[][] board = chessboard.getBoard();
		if(i>=0&&i<25&&j>=0&&j<25&&board[i][j]=="+")   return true;
		return false;
	}
	
//ȡ���Ԥ��ֵ
	public int maxValue(int t, int k, String ico) {
	    //System.out.println("MAXVALUE:"+t+":"+k+":"+ico);
		int i,j,col;
        if(ico.equals(Chessman.BLACK.getChessman()))  col=0;     //������col=0��ʾ��������col=1��ʾ��
        else col=1;        
        i=t+1;
        j=k+1;
        int max1=0;
        states[i][j][col][0]=states[i-1][j-1][col][0]+1;  //��б
        states[i][j][col][1]=states[i-1][j][col][1]+1;      //����
        states[i][j][col][2]=states[i-1][j+1][col][2]+1;  //��б
        states[i][j][col][3]=states[i][j-1][col][3]+1;  
        states[i][j][col][4]=states[i][j+1][col][4]+1;  //��б
        states[i][j][col][5]=states[i+1][j-1][col][5]+1;      //����
        states[i][j][col][6]=states[i+1][j][col][6]+1;  //��б
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

 //��Χ8�����������
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
 
 //��־�߹����±�
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
 //ˢ��״̬����
 public void shuaxin(int k1,int t,String ico){
	 int i=k1;
	 int j=t;
	 int x=k1-1;
	 int y=t-1;
	 String[][] board = chessboard.getBoard();
	 int col;
	 if(ico.equals(Chessman.BLACK.getChessman()))  col=0;     //������col=0��ʾ��������col=1��ʾ��
     else col=1;  
	 int k=1;
	// System.out.println("SHUAN XIN");
	 while( setvalid(x-k,y-k)&&board[x-k][y-k].equals(board[x][y])){  //����
		 states[i-k][j-k][col][7]= states[i-k+1][j-k+1][col][7]+1;
		// System.out.println(0);
		 k++;
		// System.out.println(k);
	 }  
	// System.out.println(k-1);
	 k=1;
	 while(setvalid(x-k,y)&&board[x-k][y]==board[x][y]){  //����
		 states[i-k][j][col][6]= states[i-k+1][j][col][6]+1;
		// System.out.println(1);
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x-k,y+k)&&board[x-k][y+k]==board[x][y]){  //����
		 states[i-k][j+k][col][5]= states[i-k+1][j+k-1][col][5]+1;
		// System.out.println(2);
		 k++;
	 }  
	 //System.out.println(k-1);
	  k=1;
	 while(setvalid(x,y-k)&&board[x][y-k]==board[x][y]){  //��
		 states[i][j-k][col][4]= states[i][j-k+1][col][4]+1;
		// System.out.println(3);
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x,y+k)&&board[x][y+k]==board[x][y]){  //��
		 states[i][j+k][col][3]= states[i][j+k-1][col][3]+1;	 
		 k++;
	 }
	 //System.out.println(k-1);
	 k=1;
	 while(setvalid(x+k,y-k)&&board[x+k][y-k]==board[x][y]){  //����
		 states[i+k][j-k][col][2]= states[i+k-1][j-k+1][col][2]+1;
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x+k,y)&&board[x+k][y]==board[x][y]){  //����
		 states[i+k][j][col][1]= states[i+k-1][j][col][1]+1;
		 k++;
	 }  
	// System.out.println(k-1);
	  k=1;
	 while(setvalid(x+k,y+k)&&board[x+k][y+k]==board[x][y]){  //����
		 states[i+k][j+k][col][0]= states[i+k-1][j+k-1][col][0]+1;
		 k++;
	 }  
	// System.out.println(k-1);
	 //System.out.println("SHUAN XIN");
 }
//  �˻�����
public int[] computerDo(){
		int i,j;//�����Ѿ��¹��ĺڰ���
		int t,k;//��Ϊ�м���
		int px = 0,py=0,cx=0,cy=0;//��־��ѹ�����
		//int max=0;
		int max2=0;
		int max1=0;//max1�أ�max2��
		int flagi,flagj;
		//int []tmp=new int[3];
		//��
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
		//��
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
